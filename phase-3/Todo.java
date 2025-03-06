import java.sql.Timestamp;

public record Todo(String name, String details, int periority, Timestamp deadline) {
}
