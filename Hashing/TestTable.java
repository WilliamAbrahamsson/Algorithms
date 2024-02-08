public class TestTable {

  public static void main(String[] args) {
    HashTable<Vehicle> vehicleTable = new HashTable<>(10);

    Vehicle v1 = new Vehicle("ABC123");
    Vehicle v2 = new Vehicle("ABC123");

    Vehicle v3 = new Vehicle("XYZ789");
    Vehicle v4 = new Vehicle("LOL007");
    Vehicle v5 = new Vehicle("GHI789");
    Vehicle v6 = new Vehicle("SAS000");

    // Insert vehicles into the hash table
    vehicleTable.insert(v1);
    vehicleTable.insert(v2);
    vehicleTable.insert(v3);
    vehicleTable.insert(v4);
    vehicleTable.insert(v5);
    vehicleTable.insert(v6);

    // Print the hash table and number of conflicts
    vehicleTable.tableRep();
    vehicleTable.getConflicts();

    // Search for a vehicle
    String licensePlate = "ABC123";
    Vehicle result = vehicleTable.search(v2);
    if (result != null) {
      System.out.println(
        "Vehicle with license plate " + licensePlate + " found"
      );
    } else {
      System.out.println(
        "Vehicle with license plate " + licensePlate + " not found"
      );
    }

    // Delete a vehicle
    licensePlate = "XYZ789";
    vehicleTable.delete(v1);
    vehicleTable.delete(v2);
    vehicleTable.delete(v3);

    // Print the hash table and number of conflicts again
    vehicleTable.tableRep();
    vehicleTable.getConflicts();
  }
}
