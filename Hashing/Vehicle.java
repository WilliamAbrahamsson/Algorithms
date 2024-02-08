public class Vehicle {

  public String licensePlate;

  public Vehicle(String licensePlate) {
    this.licensePlate = licensePlate;
  }

  // Hash function overrides Object's hashCode()
  public int hashCode() {
    int hash = 0;
    for (int ix = 0; ix < licensePlate.length(); ix++) {
      // check if its a letter
      if (Character.isLetter(licensePlate.charAt(ix))) {
        hash = hash * 26 + (licensePlate.charAt(ix));
      }
      // Number
      else {
        hash = hash * 10 + (licensePlate.charAt(ix));
      }
    }
    return hash;
  }
}
