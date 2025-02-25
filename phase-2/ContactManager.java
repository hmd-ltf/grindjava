import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ContactManager {

  private List<Contact> contacts = new ArrayList<>();

  public void loadContactsFromFile(String fileName) {
    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
      int totalContacts = ois.readInt();
      while (totalContacts > 0) {
        Contact contact = (Contact) ois.readObject();
        addContact(contact);
        totalContacts--;
      }
    }

    catch (IOException | ClassNotFoundException e) {
      System.out.println("Unable to read file due to " + e);
    }
  }

  public void saveContactsInFile(String fileName) {
    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
      oos.writeInt(contacts.size());

      for (Contact contact : contacts) {
        oos.writeObject(contact);
      }
    }

    catch (IOException e) {
      System.out.println("Unable to write to file due to " + e);
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

  public void removeContactByName(String name) {
    contacts = contacts.stream().filter(contact -> contact.name().equals(name)).collect(Collectors.toList());
  }

  public void removeContactByContact(String contact) {
    contacts = contacts.stream().filter(c -> c.name().equals(contact)).collect(Collectors.toList());
  }

  public List<Contact> filterContactByName(String name) {
    return contacts.stream().filter(contact -> contact.name().contains(name)).collect(Collectors.toList());
  }

  public List<Contact> filterContactByContact(String contact) {
    return contacts.stream().filter(c -> c.name().contains(contact)).collect(Collectors.toList());
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
