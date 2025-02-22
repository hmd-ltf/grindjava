import java.io.Console;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class Main {

  static ContactManager contactManager = new ContactManager();

  public static void main(String[] args) {
    Console console = System.console();
    char userInput;

    do {
      userInput = console.readLine("""
          Add Contacts: A
          Remove Contacts: B
          Search Contacts: C
          List Contacts: D
          Quit: 0
          What do U want to do?
          """).charAt(0);

      performAction(userInput);

    } while (userInput != '0');

    System.out.println(contactManager);
  }

  public static void performAction(char userInput) {
    switch (userInput) {
      case 'A' -> createContact();
      case 'B' -> System.out.println("Remove contact");
      case 'C' -> System.out.println("Search contact");
      case 'D' -> System.out.println("List contact");
      case '0' -> System.out.println("Exiting Application");
      default -> System.out.println("Default");
    }
  }

  public static void createContact() {
    Console console = System.console();
    String contactName = console.readLine("Enter contact name: ");
    String contactNumber = console.readLine("Enter contact number: ");

    Contact contact = new Contact(contactName, contactNumber);
    contactManager.addContact(contact);

    try (ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("file.txt"))) {
      os.writeObject(contact);
    } catch (IOException e) {
      System.out.println("Unable to code due to exception "+ e);
    }

  }

}
