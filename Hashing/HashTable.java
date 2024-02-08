public class HashTable<T> {

  // Hash Table class
  public int sz;
  public T[] table;
  public int conflicts;

  // constructor creates table of size n
  public HashTable(int n) {
    // set size to specified
    sz = n;

    // initialize table
    table = (T[]) new Object[sz];
  }

  // get conflicts
  public void getConflicts() {
    System.out.println("\nConflicts: " + conflicts + "\n");
  }

  // table representation
  public void tableRep() {
    for (int ix = 0; ix < sz; ix++) {
      System.out.println("Bucket " + ix + ": " + table[ix]);
    }
  }

  // Insert function also returns the offset
  public int insert(T key) {
    // Get hash value from hashcode
    int bucket = key.hashCode() % sz;

    // if bucket is empty, insert key
    if (table[bucket] == null) {
      table[bucket] = key;
      return 0;
    }
    // Bucket occupied
    else {
      // Increase global conflict counter
      conflicts++;

      // Local offset variable
      int offs = 0;

      // Until empty bucket is found
      while (table[bucket] != null) {
        // Increment with quadratic probing
        offs++;
        bucket = (bucket + offs * offs) % sz;
      }

      // Insert key
      table[bucket] = key;

      // Return offset
      return offs;
    }
  }

  // Delete function
  public void delete(T key) {
    // Get hash value from hashcode
    int bucket = key.hashCode() % sz;

    // If bucket contains key, remove it
    if (table[bucket] != null && table[bucket].equals(key)) {
      table[bucket] = null;
    }
    // Bucket does not contain key, search with quadratic probing
    else {
      int offs = 0;
      while (table[bucket] != null && !table[bucket].equals(key)) {
        offs++;
        bucket = (bucket + offs * offs) % sz;
      }
      if (table[bucket] != null && table[bucket].equals(key)) {
        table[bucket] = null;
      }
    }
  }

  // Search function
  public T search(T key) {
    // Get hash value from hashcode
    int bucket = key.hashCode() % sz;

    // If bucket contains key, return it
    if (table[bucket] != null && table[bucket].equals(key)) {
      return table[bucket];
    }
    // Bucket does not contain key, search with quadratic probing
    else {
      int offs = 0;
      while (table[bucket] != null && !table[bucket].equals(key)) {
        offs++;
        bucket = (bucket + offs * offs) % sz;
      }
      if (table[bucket] != null && table[bucket].equals(key)) {
        return table[bucket];
      } else {
        return null;
      }
    }
  }
}
