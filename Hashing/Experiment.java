import java.io.FileWriter;
import java.io.IOException;

public class Experiment {

  public static void main(String[] args) {
    createHashTable(10_000);
  }

  // create hash table and return offsets in array
  private static void createHashTable(int n) {
    int m = 3 * n;

    // Generate table twice the size of n
    HashTable ht = new HashTable(m);

    // Insert n Vehicles and save offsets
    int[] offsets = new int[n];
    for (int ix = 0; ix < n; ix++) {
      // generate vehicle and get license plate
      Vehicle vehicle = generateVehicle();

      // perform insert and save offset
      int offset = ht.insert(vehicle);

      // add offset to list
      offsets[ix] = offset;
    }

    writeArrayToFile(offsets, "offsets.py", "offsets");

    // calculate mean offset
    double mean = 0;
    for (int ix = 0; ix < n; ix++) {
      mean += offsets[ix];
    }
    mean = mean / n;

    // print mean offset
    System.out.println("Mean offset: " + mean);

    // count the number of ints in the offsets array that are not 0
    int collisions = 0;
    for (int ix = 0; ix < n; ix++) {
      if (offsets[ix] != 0) {
        collisions++;
      }
    }

    // print number of collisions
    System.out.println("Collisions: " + collisions);
  }

  // Generate Vehicles
  private static Vehicle generateVehicle() {
    String letters = "";
    String numbers = "";

    for (int ix = 0; ix < 3; ix++) {
      letters += (char) (Math.random() * 26 + 65); // letter
      numbers += (int) (Math.random() * 10); // number
    }

    return new Vehicle(letters + numbers);
  }

  // write an array to a file
  public static void writeArrayToFile(
    int[] offsets,
    String filename,
    String arrayName
  ) {
    try {
      FileWriter writer = new FileWriter(filename);
      StringBuilder sb = new StringBuilder();
      sb.append(arrayName + " = [");
      for (int ix = 0; ix < offsets.length; ix++) {
        sb.append(offsets[ix]);
        if (ix < offsets.length - 1) {
          sb.append(", ");
        }
      }
      sb.append("]");
      writer.write(sb.toString());
      writer.close();
    } catch (IOException e) {
      System.err.println("Error writing to file: " + e.getMessage());
    }
  }
}
