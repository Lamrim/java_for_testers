package tests.contacts;

import model.ContactData;
import org.junit.jupiter.api.Test;
import tests.TestBase;

public class ContactCreationTests extends TestBase {

    @Test
    public void canCreateContact() {
        app.contacts().createContact(new ContactData("firstName"));
    }

    @Test
    public void canCreateTwoContacts() {
        app.contacts().createTwoContacts(new ContactData("firstName"));
    }
}
