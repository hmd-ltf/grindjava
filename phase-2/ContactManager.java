import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

public class ContactManager {


  private List<Contact> contacts = new ArrayList<>();

  public ContactManager () {
    try (ObjectInputStream ir = new ObjectInputStream(new FileInputStream("file.txt"))) {
      Contact contact = (Contact) ir.readObject();
      addContact(contact);
    }

    catch (IOException | ClassNotFoundException e) {
      System.out.println("Unable to read file due to " + e);
    }
  }

  public List<Contact> getContacts() {
    return contacts;
  }

  public void setContacts(List<Contact> contacts) {
    this.contacts = contacts;
  }

  public void addContact(Contact contact) {
    if (this.contacts == null) {
      this.contacts = new ArrayList<>();
    }

    this.contacts.add(contact);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();

    for (Contact contact : getContacts()) {
      sb.append("- ").append(contact).append("\n");
    }

    return sb.toString();
  }

}
