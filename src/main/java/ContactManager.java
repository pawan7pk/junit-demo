import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ContactManager {

    Map<String, Contact> contactsMap = new ConcurrentHashMap<>();

    public void addContact(String firstName, String lastName, String phoneNo) {
        Contact contact = new Contact(firstName, lastName, phoneNo);
        validateContact(contact);
        checkIfContactAlreadyExist(contact);
        contactsMap.put(generateKey(contact), contact);
    }

    public Collection<Contact> getAllContacts() {
        return contactsMap.values();
    }

    private void validateContact(Contact contact) {
        contact.validateFirstName();
        contact.validateLastName();
        contact.validatePhoneNumber();
    }

    private void checkIfContactAlreadyExist(Contact contact) {
        if (contactsMap.containsKey(generateKey(contact)))
            throw new RuntimeException("Contact Already Exists");
    }


    private String generateKey(Contact contact) {
        return String.format("%s-%s", contact.getFirstName(), contact.getLastName());
    }
}
