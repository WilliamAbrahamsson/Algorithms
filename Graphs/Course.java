import java.util.ArrayList;

public class Course {

  // all these could be the same but it dosent matter :)
  private int id; // vertex id in the graph
  private int identifier;
  private String group;
  private int number;
  private ArrayList<Course> requiredCourses;

  public Course(int identifier, String group, int number) {
    this.identifier = identifier;
    this.group = group;
    this.number = number;
    this.requiredCourses = new ArrayList<Course>();
  }

  // getters and setters.

  // get the vertex id.
  public int getId() {
    return this.id;
  }

  // set the vertex id.
  public void setId(int id) {
    this.id = id;
  }

  public int getIdentifier() {
    return this.identifier;
  }

  public String getGroup() {
    return this.group;
  }

  public int getNumber() {
    return this.number;
  }

  // get the required courses.
  public ArrayList<Course> getRequiredCourses() {
    return this.requiredCourses;
  }

  // add a required course.
  public void addRequiredCourse(Course course) {
    this.requiredCourses.add(course);
  }

  // formatted tostring.
  public String toString() {
    return String.format("%d%s%03d", this.identifier, this.group, this.number);
  }
}
