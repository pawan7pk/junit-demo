import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ContactManagerTest {

    private ContactManager contactManager;

    @BeforeAll
    public static void setUpAll() {
        System.out.println("Will print before all Tests");
    }

    @BeforeEach
    public void setUpContactManger() {
        System.out.println("Instantiate Contact Manger");
        contactManager = new ContactManager();
    }

    @Test
    @DisplayName("Create New Contact")
    public void createNewContact() {
        contactManager.addContact("Pawan", "Khandelwal", "9660000601");
        assertFalse(contactManager.getAllContacts().isEmpty());
        assertEquals(1, contactManager.getAllContacts().size());
    }

    @Test
    @DisplayName("Should Throw Exception When First name is null")
    public void shouldThrowExceptionWhenFirstNameIsNull() {
        Assertions.assertThrows(RuntimeException.class, () -> {
            contactManager.addContact(null, "Khandelwal", "9090009099");
        });
    }

    @Test
    @DisplayName("Should Throw Exception When Last Name is Null")
    public void shouldThrowRuntimeExceptionWhenLastNameIsNull() {
        Assertions.assertThrows(RuntimeException.class, () -> {
            contactManager.addContact("Pawan", null, "9003030303");
        });
    }

    @Test
    @DisplayName("Should Throw Exception When Phone Number is Null")
    public void shouldThrowRuntimeExceptionWhenPhoneNumberIsNull() {
        Assertions.assertThrows(RuntimeException.class, () -> {
            contactManager.addContact("Pawan", "Khandelwal", null);
        });
    }

    @Test
    @DisplayName("Should Create Contact")
    @EnabledOnOs(value = OS.MAC)  // will run only on MAC
    public void shouldCreateContactOnMAC() {
        contactManager.addContact("John", "Doe", "0123456789");
        assertFalse(contactManager.getAllContacts().isEmpty());
        assertEquals(1, contactManager.getAllContacts().size());
    }

    @Nested
    class RepeatedTests {
        @DisplayName("Repeat Contact Creation Test 5 Times")
        @RepeatedTest(value = 5, name = "Repeating Contact Creation Test {currentRepetition} of {totalRepetitions}")
        public void shouldTestContactCreationRepeatedly() {
            contactManager.addContact("John", "Doe", "0123456789");
            assertFalse(contactManager.getAllContacts().isEmpty());
            assertEquals(1, contactManager.getAllContacts().size());
        }
    }

    @DisplayName("Method Source Case - Phone Number should match the required Format")
    @ParameterizedTest
    @MethodSource("phoneNumberList")
    public void shouldTestPhoneNumberFormatUsingMethodSource(String phoneNumber) {
        contactManager.addContact("John", "Doe", phoneNumber);
        assertFalse(contactManager.getAllContacts().isEmpty());
        assertEquals(1, contactManager.getAllContacts().size());
    }

    private static List<String> phoneNumberList() {
        return Arrays.asList("9000000000", "8000000000", "7000000000");
    }
    @Test
    @DisplayName("Test Should Be Disabled")
    @Disabled
    public void shouldBeDisabled() {
        throw new RuntimeException("Test Should Not be executed");
    }

    @AfterEach
    public void tearDown() {
        System.out.println("Should Execute After Each Test");
    }

    @AfterAll
    public static void tearDownAll() {
        System.out.println("Should be executed at the end of the Test");
    }
}