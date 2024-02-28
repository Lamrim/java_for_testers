package tests.contacts;

import model.ContactData;
import org.junit.jupiter.api.Test;
import tests.TestBase;

public class ContactCreationTests extends TestBase {

    @Test
    public void canCreateContact() {
        app.contacts().createContact(new ContactData().withFirstName("first name"));
    }

    @Test
    public void canCreateFewContacts() {
        app.contacts().createFewContacts(new ContactData().withSomeFields("firstName", "last name",
                "address", "email", "7777777"));
    }

    @Test
    public void canCreateEmptyContact() {
        app.contacts().createContact(new ContactData());
    }
}
