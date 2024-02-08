import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

// note: this implementation reads in real time from the data.txt file.
// if you want to change the data, you must change the data.txt file.
public class CoursePath {

  ArrayList<Course> courses = loadCourses();

  public CoursePath(String course_name) {
    loadCourses();
    buildGraph();
    displayCoursePath(course_name);
  }

  // print out the course path.
  public void displayCoursePath(String course_name) {
    ArrayList<String> path = findCoursePath(course_name);
    System.out.print(
      "In order to take the course: " + course_name + ", you must take: "
    );

    for (int ix = 0; ix < path.size() - 1; ix++) {
      System.out.print(path.get(ix) + " -> ");
    }
    if (!path.isEmpty()) {
      System.out.print(path.get(path.size() - 1));
    } else {
      System.out.print("None");
    }
    System.out.println();
  }

  public ArrayList<String> findCoursePath(String course_name) {
    ArrayList<Integer> topOrder = getTopologicalOrder();
    DirectedGraph G = buildGraph();

    // get the vertex id from the course name.
    int end_vertex = getCourse(course_name).getId();

    // Traverse the graph backwards from end_vertex using the topological order.
    ArrayList<String> path = new ArrayList<>();
    int index = topOrder.indexOf(end_vertex);

    if (index >= 0) {
      for (int ix = index - 1; ix >= 0; ix--) {
        int u = topOrder.get(ix);
        // for each vertex v adjacent to u.
        for (int v : G.getAdjecentVertices(u)) {
          if (v == end_vertex) {
            // add to the path.
            path.add(0, getCourse(u).toString());
            end_vertex = u;
            break;
          }
        }
      }
    }

    // return the path from start_vertex to end_vertex (if it exists).
    return path;
  }

  public ArrayList<Integer> getTopologicalOrder() {
    DirectedGraph G = buildGraph();
    ArrayList<Integer> inDegree = new ArrayList<>(G.vertex_count);
    ArrayList<Integer> topOrder = new ArrayList<>();

    // init in degree of all vertices to 0.
    for (int ix = 0; ix < G.vertex_count; ix++) {
      inDegree.add(0);
    }

    // calculate in degree of each vertex.
    for (int ix = 0; ix < G.vertex_count; ix++) {
      // get all adjacent vertices of the current vertex.
      for (int jx = 0; jx < G.getAdjecentVertices(ix).size(); jx++) {
        int v = G.getAdjecentVertices(ix).get(jx);
        // increase in degree by 1.
        inDegree.set(v, inDegree.get(v) + 1);
      }
    }

    // create a queue to store vertices with in degree 0.
    ArrayList<Integer> queue = new ArrayList<>();
    for (int ix = 0; ix < G.vertex_count; ix++) {
      if (inDegree.get(ix) == 0) {
        queue.add(ix);
      }
    }

    // process vertices in the queue and find the topological order.
    while (!queue.isEmpty()) {
      int u = queue.remove(0);
      topOrder.add(u);

      // decrease in degree by 1 for all adjacent vertices.
      for (int v : G.getAdjecentVertices(u)) {
        inDegree.set(v, inDegree.get(v) - 1);
        if (inDegree.get(v) == 0) {
          queue.add(v);
        }
      }
    }

    return topOrder;
  }

  public DirectedGraph buildGraph() {
    // initialize new directed graph with size() - 1 vertices because of offset by 1.
    DirectedGraph graph = new DirectedGraph(courses.size());

    // iterate through all courses
    for (int ix = 0; ix < courses.size(); ix++) {
      Course course = courses.get(ix);

      // required courses
      for (int jx = 0; jx < course.getRequiredCourses().size(); jx++) {
        Course requiredCourse = course.getRequiredCourses().get(jx);
        graph.add_edge(requiredCourse.getId(), course.getId());
      }

      graph.writeGraph(graph.toString(), "directed_data.py", "directed_data");
    }
    return graph;
  }

  // loads the courses and creates class object that also has an id that represents a vertex in the graph.
  public ArrayList<Course> loadCourses() {
    ArrayList<Course> courses = new ArrayList<Course>();
    HashMap<String, Course> courseMap = new HashMap<String, Course>();

    try {
      // read data from file.
      BufferedReader reader = new BufferedReader(new FileReader("data.txt"));
      String line = reader.readLine();
      int idCounter = 0;

      // if there is a line to read.
      while (line != null) {
        // initialize course.
        int courseIdentifier = Integer.parseInt(line.substring(0, 1));
        String courseGroup = line.substring(1, 3);
        int courseNumber = Integer.parseInt(line.substring(3, 6));
        Course course = new Course(courseIdentifier, courseGroup, courseNumber);
        String courseString = course.toString();

        // if course is new, add it to the list.
        if (!courseMap.containsKey(courseString)) {
          course.setId(idCounter++);
          courseMap.put(courseString, course);
          courses.add(course);
        } else {
          course = courseMap.get(courseString);
        }

        // initialize required course.
        int requiredIdentifier = Integer.parseInt(line.substring(7, 8));
        String requiredGroup = line.substring(8, 10);
        int requiredNumber = Integer.parseInt(line.substring(10, 13));
        Course requiredCourse = new Course(
          requiredIdentifier,
          requiredGroup,
          requiredNumber
        );
        String requiredCourseString = requiredCourse.toString();

        // if required course is new, add it to the list.
        if (courseMap.containsKey(requiredCourseString)) {
          requiredCourse = courseMap.get(requiredCourseString);
        } else {
          requiredCourse.setId(idCounter++);
          courseMap.put(requiredCourseString, requiredCourse);
          courses.add(requiredCourse);
        }

        // add required course to course.
        course.addRequiredCourse(requiredCourse);

        line = reader.readLine();
      }
      reader.close();
    } catch (IOException e) {
      System.out.println("Error reading file: " + e.getMessage());
    }
    return courses;
  }

  // get all courses.
  public ArrayList<Course> getCourses() {
    return courses;
  }

  // get course by toString.
  public Course getCourse(String courseString) {
    for (Course course : courses) {
      if (course.toString().equals(courseString)) {
        return course;
      }
    }
    return null;
  }

  // get course by id (vertex).
  public Course getCourse(int id) {
    for (Course course : courses) {
      if (course.getId() == id) {
        return course;
      }
    }
    return null;
  }
}
