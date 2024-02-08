import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Server {

  public static void main(String[] args) {
    if (args.length != 2) {
      System.exit(1);
    }

    int port = Integer.parseInt(args[0]);
    String publicPath = args[1];

    try (ServerSocket serverSocket = new ServerSocket(port)) {
      System.out.println("Server running on port " + port);

      while (true) {
        Socket clientSocket = serverSocket.accept();
        new Thread(() -> handleRequest(clientSocket, publicPath, port)).start();
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private static void handleRequest(
    Socket clientSocket,
    String publicPath,
    int port
  ) {
    try (
      BufferedReader in = new BufferedReader(
        new InputStreamReader(clientSocket.getInputStream())
      );
      OutputStream out = clientSocket.getOutputStream()
    ) {
      try {
        StringBuilder requestBuilder = new StringBuilder();
        String line;
        while ((line = in.readLine()) != null && !line.isEmpty()) {
          requestBuilder.append(line).append("\r\n");
        }
        String request = requestBuilder.toString();

        if (request.startsWith("GET")) {
          String requestedFile = getRequestFile(request);

          System.out.println("Assigned a new client to a separate thread. \n");
          serveFile(out, publicPath + requestedFile, port, clientSocket);
        }

        // Close the client socket
        clientSocket.close();
      } catch (Exception e) {
        // An unexpected exception occurred, send a 500 Internal Server Error response
        send500InternalServerError(out, clientSocket);
      }
      /* try {
        // Simulate an exception (for testing purposes)
        throw new RuntimeException(
          "Intentional exception for testing 500 error"
        );
      } catch (Exception e) {
        // An unexpected exception occurred, send a 500 Internal Server Error response
        send500InternalServerError(out, clientSocket);
      } */
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private static void send500InternalServerError(
    OutputStream out,
    Socket clientSocket
  ) {
    try {
      String hostAddress = clientSocket.getInetAddress().getHostAddress();
      int hostPort = clientSocket.getLocalPort();

      System.out.println("Internal Server Error!");
      System.out.println(
        "Client: " +
        hostAddress +
        ":" +
        hostPort +
        ", Version: HTTP/1.0" +
        ", Response: 500 Internal Server Error"
      );

      String response = "HTTP/1.1 500 Internal Server Error\r\n\r\n";
      out.write(response.getBytes());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private static String getRequestFile(String request) {
    // Extract the requested file from the GET request
    String[] parts = request.split(" ");
    String requestedFile = parts.length > 1 ? parts[1] : "";

    // If the requested file is empty (root URL), set it to "/web/index.html"
    return requestedFile.isEmpty() || requestedFile.equals("/")
      ? "/index.html"
      : requestedFile;
  }

  private static void serveFile(
    OutputStream out,
    String filePath,
    int port,
    Socket clientSocket
  ) {
    try {
      // Normalize file path to avoid paths like "/a/../b"
      filePath = Paths.get(filePath).normalize().toString();

      Path file = Paths.get(filePath);
      if (Files.isDirectory(file)) {
        // If the request is for a directory, try serving index.html from that directory
        file = Paths.get(filePath, "index.html");
      }

      String hostAddress = clientSocket.getInetAddress().getHostAddress();
      int hostPort = clientSocket.getLocalPort();

      if (
        Files.exists(file) && !Files.isDirectory(file) && Files.isReadable(file)
      ) {
        byte[] content = Files.readAllBytes(file);

        // Your existing redirection check (if applicable)
        if (shouldRedirect(filePath)) {
          String redirectLocation = getRedirectLocation(filePath);
          send302Redirect(out, clientSocket, redirectLocation);
          return;
        }

        // Send HTTP headers
        String headers =
          "HTTP/1.1 200 OK\r\nContent-Type: " +
          getContentType(file.toString()) +
          "\r\n\r\n";
        out.write(headers.getBytes());

        // Print information about the new client and request
        System.out.println(
          "Host: " +
          "localhost:" +
          port +
          ", Method: GET" +
          ", Path: " +
          filePath +
          ", version: HTTP/1.1"
        );
        System.out.println("Server request file exists!");

        // Send file content
        out.write(content);
      } else {
        send404NotFound(out, clientSocket);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private static void send404NotFound(OutputStream out, Socket clientSocket) {
    try {
      String hostAddress = clientSocket.getInetAddress().getHostAddress();
      int hostPort = clientSocket.getLocalPort();

      System.out.println("Server request file does not exist!");
      System.out.println(
        "Client: " +
        hostAddress +
        ":" +
        hostPort +
        ", Version: HTTP/1.0" +
        ", Response: 404 Not Found"
      );

      String response = "HTTP/1.1 404 Not Found\r\n\r\n";
      out.write(response.getBytes());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private static boolean shouldRedirect(String filePath) {
    return filePath.endsWith("/a/b.html");
  }

  private static String getRedirectLocation(String filePath) {
    return "/a/b/b.html";
  }

  private static void send302Redirect(
    OutputStream out,
    Socket clientSocket,
    String redirectLocation
  ) {
    try {
      String hostAddress = clientSocket.getInetAddress().getHostAddress();
      int hostPort = clientSocket.getLocalPort();

      System.out.println("Redirecting to: " + redirectLocation);
      System.out.println(
        "Client: " +
        hostAddress +
        ":" +
        hostPort +
        ", Version: HTTP/1.0" +
        ", Response: 302 Found"
      );

      String response =
        "HTTP/1.1 302 Found\r\nLocation: " + redirectLocation + "\r\n\r\n";
      out.write(response.getBytes());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private static String getContentType(String filePath) {
    if (filePath.endsWith(".html")) {
      return "text/html";
    } else if (filePath.endsWith(".png")) {
      return "image/png";
    } else {
      return "application/octet-stream";
    }
  }
}
