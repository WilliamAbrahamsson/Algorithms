import java.io.IOException;
import java.net.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

public class TFTPServer {

  public static final int TFTPPORT = 69;
  public static final int BUFSIZE = 516;

  public static final String READDIR = "../files/";

  private static final int OP_RRQ = 1;
  private static final int OP_WRQ = 2;
  private static final int OP_DAT = 3;
  private static final int OP_ACK = 4;
  private static final int OP_ERR = 5;

  public static void main(String[] args) {
    if (args.length > 0) {
      System.err.printf(
        "usage: java %s\n",
        TFTPServer.class.getCanonicalName()
      );
      System.exit(1);
    }
    try {
      TFTPServer server = new TFTPServer();
      // starting the server.
      server.start();
    } catch (SocketException e) {
      e.printStackTrace();
    }
  }

  private void start() throws SocketException {
    byte[] buf = new byte[BUFSIZE];

    // create main socket.
    DatagramSocket socket = new DatagramSocket(null);

    // create local bind point.
    SocketAddress localBindPoint = new InetSocketAddress(TFTPPORT);
    socket.bind(localBindPoint);

    System.out.printf("Listening at port %d for new requests\n", TFTPPORT);

    // loop to handle client requests.
    while (true) {
      final InetSocketAddress clientAddress = receiveFrom(socket, buf);

      // if clientAddress is null, an error occurred in receiveFrom().
      if (clientAddress == null) continue;

      final StringBuilder requestedFile = new StringBuilder();

      // get type of request
      final int reqtype = getRequestType(buf, requestedFile);

      // one thread per client request.
      new Thread(() -> {
        try {
          // socket to handle client request
          DatagramSocket sendSocket = new DatagramSocket(0);

          // connect to client
          sendSocket.connect(clientAddress);

          System.out.printf(
            "%s request for %s from %s using port %d\n",
            (reqtype == OP_RRQ) ? "Read" : "Write",
            requestedFile.toString(),
            clientAddress.getAddress(),
            clientAddress.getPort()
          );

          requestedFile.insert(0, READDIR);
          HandleRQ(sendSocket, requestedFile.toString(), reqtype);

          sendSocket.close();
        } catch (SocketException e) {
          e.printStackTrace();
        }
      })
        .start();
    }
  }

  private InetSocketAddress receiveFrom(DatagramSocket socket, byte[] buf) {
    try {
      DatagramPacket receivePacket = new DatagramPacket(buf, buf.length);
      socket.receive(receivePacket);
      return new InetSocketAddress(
        receivePacket.getAddress(),
        receivePacket.getPort()
      );
    } catch (SocketTimeoutException e) {
      // handle timeout.
      System.err.println("Socket timeout: " + e.getMessage());
    } catch (IOException e) {
      // handle other receive errors.
      System.err.println("Error receiving packet: " + e.getMessage());
    }
    return null;
  }

  // get request type.
  private int getRequestType(byte[] buf, StringBuilder requestedFile) {
    // buffer too short for valid request.
    if (buf.length < 2) {
      return -1;
    }

    // extract opcode from first 2 bytes.
    int opcode = (buf[0] << 8) | (buf[1] & 0xFF);

    // if not write or read.
    if (opcode != OP_RRQ && opcode != OP_WRQ) {
      return -1;
    }

    // find string representing the requested file.
    int i = 2;
    while (i < buf.length && buf[i] != 0) {
      requestedFile.append((char) buf[i]);
      i++;
    }

    // check if loop terminated prematurely or requested file is empty
    if (i >= buf.length || requestedFile.length() == 0) {
      return -1;
    }

    i++;

    // request type == opcode.
    return opcode;
  }

  private void HandleRQ(
    DatagramSocket sendSocket,
    String requestedFile,
    int requestType
  ) {
    try {
      Path filePath = Paths.get(requestedFile);

      // check if read request RRQ.
      if (requestType == OP_RRQ) {
        if (Files.exists(filePath) && Files.isReadable(filePath)) {
          byte[] fileData = Files.readAllBytes(filePath);
          int blockNumber = 1;

          // add data in segments of 512 bytes.
          int offset = 0;
          while (offset < fileData.length) {
            int end = Math.min(offset + 512, fileData.length);
            byte[] dataBlock = Arrays.copyOfRange(fileData, offset, end);

            // construct and send the DATA packet, receive ACK from client.
            send_DATA(sendSocket, blockNumber, dataBlock);

            // 5 seconds timeout
            sendSocket.setSoTimeout(5000);

            receive_ACK(sendSocket);

            // move to the next block.
            blockNumber++;
            offset += 512;
          }
        } else {
          // file not found or not readable, send an error packet
          send_ERR(sendSocket, 1, "File not found or not readable");
        }
      }
    } catch (IOException e) {
      e.printStackTrace();

      // handle file I/O error.
      send_ERR(sendSocket, 2, "Error accessing file: " + e.getMessage());
    } catch (Exception e) {
      e.printStackTrace();

      // send an error packet if an exception occurs
      send_ERR(sendSocket, 0, "An error occurred");
    }
  }

  // send the data packet to the client.
  private void send_DATA(DatagramSocket socket, int blockNumber, byte[] data) {
    try {
      int dataSize = data.length;

      byte[] packetData = new byte[dataSize + 4];

      // set the opcode for DATA.
      packetData[0] = (byte) ((OP_DAT >> 8) & 0xFF);
      packetData[1] = (byte) (OP_DAT & 0xFF);

      // set the block number.
      packetData[2] = (byte) ((blockNumber >> 8) & 0xFF);
      packetData[3] = (byte) (blockNumber & 0xFF);

      System.arraycopy(data, 0, packetData, 4, dataSize);

      // create a DatagramPacket with the packetData.
      DatagramPacket dataPacket = new DatagramPacket(
        packetData,
        packetData.length,
        socket.getInetAddress(),
        socket.getPort()
      );

      // send the DATA packet through the socket.
      socket.send(dataPacket);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  // receive ack packet from client after sending requested data packet.
  private void receive_ACK(DatagramSocket socket) {
    try {
      byte[] ackBuffer = new byte[BUFSIZE];
      DatagramPacket ackPacket = new DatagramPacket(
        ackBuffer,
        ackBuffer.length
      );

      // receive the ACK packet.
      socket.receive(ackPacket);

      // extract the opcode from the ACK packet.
      int opcode = ((ackBuffer[0] & 0xFF) << 8) | (ackBuffer[1] & 0xFF);

      // check if the opcode matches OP_ACK.
      if (opcode == OP_ACK) {
        // extract the block number from the ACK packet.
        int blockNumber = ((ackBuffer[2] & 0xFF) << 8) | (ackBuffer[3] & 0xFF);

        // received ACK.
        System.out.println("Received ACK for block number: " + blockNumber);
      } else {
        // invalid opcode.
        System.out.println("Received packet with invalid opcode: " + opcode);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  // error packets.
  private void send_ERR(DatagramSocket socket, int errorCode, String errorMsg) {
    try {
      byte[] errPacket = new byte[4 + errorMsg.length() + 1];

      // set the opcode
      errPacket[0] = (byte) ((OP_ERR >> 8) & 0xFF);
      errPacket[1] = (byte) (OP_ERR & 0xFF);

      // set the errorCode
      errPacket[2] = (byte) ((errorCode >> 8) & 0xFF);
      errPacket[3] = (byte) (errorCode & 0xFF);

      // copy the message into the packet.
      byte[] errorMsgBytes = errorMsg.getBytes();
      System.arraycopy(errorMsgBytes, 0, errPacket, 4, errorMsgBytes.length);

      // add null terminator.
      errPacket[4 + errorMsgBytes.length] = 0;

      // create a DatagramPacket with the packet.
      DatagramPacket errDatagramPacket = new DatagramPacket(
        errPacket,
        errPacket.length,
        socket.getInetAddress(),
        socket.getPort()
      );

      // send the packet through the socket
      socket.send(errDatagramPacket);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
