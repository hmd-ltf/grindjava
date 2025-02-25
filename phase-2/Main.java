import java.io.Console;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class Main {

  static ContactManager contactManager = new ContactManager();
  static Console console = System.console();
  static String fileName = "./file.txt";

  public static void main(String[] args) {
    contactManager.loadContactsFromFile(fileName);
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
  }

  public static void performAction(char userInput) {
    switch (userInput) {
      case 'A' -> createContact(console);
      case 'B' -> removeContact(console);
      case 'C' -> searchContacts(console);
      case 'D' -> printAllConacts(console);
      case '0' -> exitApplication();
      default -> System.out.println("Not an option.");
    }
  }

  public static void createContact(Console console) {
    String contactName = console.readLine("Enter contact name: ");
    String contactNumber = console.readLine("Enter contact number: ");

    Contact contact = new Contact(contactName, contactNumber);
    contactManager.addContact(contact);
  }

  public static void removeContact(Console console) {
    String contactDetails;
    boolean removeByName = false;
    int userOption = console.readLine("""
         A) Remove contacts by name
         B) Remove contacts by contact
        """).charAt(0);
    if (userOption == 'A') {
      removeByName = true;
    }

    contactDetails = console.readLine("Enter contact " + (removeByName ? "name: " : ": "));

    if (removeByName) {
      contactManager.removeContactByName(contactDetails);
    } else {
      contactManager.removeContactByContact(contactDetails);
    }

  }

  public static void searchContacts(Console console) {
    String contactDetails;
    boolean filterByName = false;
    int userOption = console.readLine("""
         A) Search contacts by name
         B) Search contacts by contact
        """).charAt(0);
    if (userOption == 'A') {
      filterByName = true;
    }

    contactDetails = console.readLine("Enter contact " + (filterByName ? "name: " : ": "));

    if (filterByName) {
      console.printf("Contacts are\n" + contactManager.filterContactByName(contactDetails));
    } else {
      console.printf("Contacts are\n" + contactManager.filterContactByContact(contactDetails));
    }

  }

  public static void printAllConacts(Console console) {
    console.printf("Contact are\n" + contactManager);
  }

  public static void exitApplication() {
    System.out.println("Exiting Application");
    contactManager.saveContactsInFile(fileName);
  }
}
