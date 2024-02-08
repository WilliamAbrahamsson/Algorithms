import com.LCRS;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {

  public static void main(String[] args) throws IOException {
    // Get grandparent directory path :)
    String cwd = System.getProperty("user.dir");
    File parentDir = new File(cwd).getParentFile().getParentFile();

    // User terminal input
    Scanner scanner = new Scanner(System.in);

    System.out.print("Path (/): ");
    String path = "/" + scanner.nextLine();

    System.out.print("Filename: ");
    String filename = scanner.nextLine();

    // Build the tree
    LCRS root = LCRS.buildTree(parentDir);
    LCRS node = root.addNode(parentDir + path, filename);

    // Print the tree with indent for subdirectories.
    printTree(node, "");

    // scanner menu, do you want to add more files?
    System.out.println("Do you want to add more files? (y/n)");
    String answer = scanner.nextLine();

    while (answer.equals("y")) {
      System.out.print("Path (/): ");
      path = "/" + scanner.nextLine();

      System.out.print("Filename: ");
      filename = scanner.nextLine();

      node = root.addNode(parentDir + path, filename);

      // Print the tree with indent for subdirectories.
      printTree(node, "");

      System.out.println("Do you want to add more files? (y/n)");
      answer = scanner.nextLine();
    }
  }

  // Print the tree with indent for subdirectories.
  private static void printTree(LCRS node, String indent) {
    System.out.println(indent + node.file.getName());

    LCRS child = node.leftMostChild;
    while (child != null) {
      printTree(child, indent + "   ");
      child = child.rightSibling;
    }
  }
}
