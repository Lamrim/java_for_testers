package tests.contacts;

import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import tests.TestBase;

import java.util.ArrayList;
import java.util.List;

public class ContactCreationTests extends TestBase {

    public static List<ContactData> contactProvider() {
        var result = new ArrayList<ContactData>();
        for (var firstName : List.of("", "first name")) {
            for (var lastName : List.of("", "last name")) {
                for (var address : List.of("", "address")) {
                    for (var email1 : List.of("", "email1")) {
                        for (var mobilePhone : List.of("", "7777777")) {
                            result.add(new ContactData().withSomeFields(
                                    firstName,
                                    lastName,
                                    address,
                                    email1,
                                    mobilePhone));
                        }
                    }
                }
            }
        }
        for (int i = 1; i < 5; i++) {
            result.add(new ContactData(
                    randomString(i * (i + 1)),
                    randomString(i * (i + 1)),
                    randomString(i * (i + 1)),
                    randomString(i * (i + 1)),
                    randomString(i * (i + 1))));
        }
        return result;
    }

    @ParameterizedTest
    @MethodSource("contactProvider")
    public void canCreateMultipleContacts(ContactData contact) {
        int contactsCount = app.contacts().getCount();
        app.contacts().createContact(contact);
        int newContactsCount = app.contacts().getCount();
        Assertions.assertEquals(contactsCount + 1, newContactsCount);
    }

    @Test
    public void canCreateChainOfContacts() {
        app.contacts().createChainOfContacts(new ContactData());
    }
}
