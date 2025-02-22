import java.io.Serializable;

public record Contact(String name, String contact) implements Serializable {

  private static final long serialVersionUID = 1L;

}
