package tests.contacts;

import model.ContactData;
import org.junit.jupiter.api.Test;
import tests.TestBase;

public class ContactRemovalTests extends TestBase {

    @Test
    public void canRemoveContact() {
        if (!app.contacts().isContactPresent()) {
            app.contacts().createContact(new ContactData().withFirstName("first name"));
        }
        app.contacts().removeContact();
    }

    @Test
    public void canRemoveAllContacts() {
        if (!app.contacts().isContactPresent()) {
            app.contacts().createFewContacts(new ContactData().withFirstName("first name"));
        }
        app.contacts().removeAllContacts();
    }

}
