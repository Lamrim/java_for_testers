package tests.contacts;

import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import tests.TestBase;

public class ContactRemovalTests extends TestBase {

    @Test
    public void canRemoveContact() {
        if (app.contacts().getCount() == 0) {
            app.contacts().createContact(new ContactData());
        }
        int contactsCount = app.contacts().getCount();
        app.contacts().removeContact();
        int newContactsCount = app.contacts().getCount();
        Assertions.assertEquals(contactsCount - 1, newContactsCount);
    }

    @Test
    public void canRemoveAllContacts() {
        if (app.contacts().getCount() == 0) {
            app.contacts().createChainOfContacts(new ContactData());
        }
        app.contacts().removeAllContacts();
        int newContactsCount = app.contacts().getCount();
        Assertions.assertEquals(0, newContactsCount);
    }
}
