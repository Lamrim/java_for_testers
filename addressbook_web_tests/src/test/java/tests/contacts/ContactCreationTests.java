package tests.contacts;

import common.CommonFunctions;
import model.ContactData;
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
                            result.add(new ContactData()
                                    .withFirstName(firstName)
                                    .withLastName(lastName)
                                    .withAddress(address)
                                    .withEmail1(email1)
                                    .withMobilePhone(mobilePhone)
                                    .withPhoto(CommonFunctions.randomFile("./src/test/resources/images")));
                        }
                    }
                }
            }
        }
        for (int i = 1; i < 5; i++) {
            result.add(new ContactData(
                    "", CommonFunctions.randomString(i * (i + 1)),
                    CommonFunctions.randomString(i * (i + 1)),
                    CommonFunctions.randomString(i * (i + 1)),
                    CommonFunctions.randomString(i * (i + 1)),
                    CommonFunctions.randomString(i * (i + 1)),
                    CommonFunctions.randomFile("./src/test/resources/images")));
        }
        return result;
    }

    @ParameterizedTest
    @MethodSource("contactProvider")
    public void canCreateMultipleContacts(ContactData contact) {
        var oldContacts = app.contacts().getList();
        app.contacts().createContact(contact);
        var newContacts = app.contacts().getList();
        Comparator<ContactData> compareById = (o1, o2) ->
                Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));

        newContacts.sort(compareById);

        var expectedList = new ArrayList<>(oldContacts);
        expectedList.add(contact.withId(newContacts.getLast().id()));

        expectedList.sort(compareById);
        Assertions.assertEquals(newContacts, expectedList);
    }

    @Test
    public void canCreateChainOfContacts() {
        app.contacts().createChainOfContacts(new ContactData());
    }

    @Test
    public void canCreateContact() {
        var contact = new ContactData()
                .withFirstName(CommonFunctions.randomString(10))
                .withLastName(CommonFunctions.randomString(10))
                .withPhoto(CommonFunctions.randomFile("./src/test/resources/images"));
        app.contacts().createContact(contact);
    }
}
