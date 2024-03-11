package tests.contacts;

import model.ContactData;
import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import tests.TestBase;

import java.util.ArrayList;
import java.util.Comparator;
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
                    "", randomString(i * (i + 1)),
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
        var oldContacts = app.contacts().getList();
        app.contacts().createContact(contact);
        var newContacts = app.contacts().getList();
        Comparator<ContactData> compareByFirstAndLastName = (o1, o2) -> {
            var name1 = String.format("%s %s", o1.lastName(), o1.firstName());
            var name2 = String.format("%s %s", o2.lastName(), o2.firstName());
            return name1.compareTo(name2);
        };

    }

    @Test
    public void canCreateChainOfContacts() {
        app.contacts().createChainOfContacts(new ContactData());
    }
}
