import java.sql.Timestamp;

public class TodoBuilder {
  private String name;
  private String details;
  private int priority;
  private Timestamp deadline;

  public TodoBuilder withName(String name) {
    this.name = name;
    return this;
  }

  public TodoBuilder withDetails(String details) {
    this.details = details;
    return this;
  }

  public TodoBuilder withPriority(int priority) {
    this.priority = priority;
    return this;
  }

  public TodoBuilder withDeadline(Timestamp deadline) {
    this.deadline = deadline;
    return this;
  }

  public Todo build() {
    if (this.name ==null || this.name.isBlank()) {
      throw new IllegalStateException("Name cannot be empty");
    }

    if (this.priority < 0) {
      throw new IllegalStateException("Priority cannot be negative");
    }

    return new Todo(name, details, priority, deadline);
  }
}
