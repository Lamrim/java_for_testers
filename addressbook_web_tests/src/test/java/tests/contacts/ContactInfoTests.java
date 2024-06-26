package tests.contacts;

import common.CommonFunctions;
import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import tests.TestBase;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ContactInfoTests extends TestBase {

    @Test
    void testPhones() {
        if (app.hbm().getContactCount() == 0) {
            app.hbm().createContact(new ContactData()
                    .withHomePhone(CommonFunctions.randomString(7))
                    .withWorkPhone(CommonFunctions.randomString(7))
                    .withMobilePhone(CommonFunctions.randomString(7)));
        }
        var contacts = app.hbm().getContactList();
        var expected = contacts.stream().collect(Collectors.toMap(ContactData::id, contact ->
                Stream.of(contact.homePhone(), contact.mobilePhone(), contact.workPhone())
                        .filter(s -> s != null && !s.isEmpty())
                        .collect(Collectors.joining("\n"))));
        var phones = app.contacts().getPhones();
        Assertions.assertEquals(expected, phones);
    }

    @Test
    void checkContactInfo() {
        if (app.hbm().getContactCount() == 0) {
            app.hbm().createContact(new ContactData()
                    .withHomePhone(CommonFunctions.randomString(7))
                    .withWorkPhone(CommonFunctions.randomString(7))
                    .withMobilePhone(CommonFunctions.randomString(7))
                    .withEmail1(CommonFunctions.randomString(7))
                    .withEmail2(CommonFunctions.randomString(7))
                    .withEmail3(CommonFunctions.randomString(7))
                    .withAddress(CommonFunctions.randomString(7))
            );
        }
        var contacts = app.hbm().getContactList();
        var rnd = new Random();
        var index = rnd.nextInt(contacts.size());
        var contact = contacts.get(index);

        var expectedPhones = Stream.of(contact.homePhone(), contact.mobilePhone(), contact.workPhone())
                .filter(s -> s != null && !s.isEmpty())
                .collect(Collectors.joining("\n"));

        var expectedEmails = Stream.of(contact.email(), contact.email2(), contact.email3())
                .filter(s -> s != null && !s.isEmpty())
                .collect(Collectors.joining("\n"));

        Map<String, String> expectedContactInfoMap = new HashMap<>();
        expectedContactInfoMap.put("address", contact.address().replaceAll("\\r\\n", "\n"));
        expectedContactInfoMap.put("emails", expectedEmails);
        expectedContactInfoMap.put("phones", expectedPhones);

        Map<String, String> contactInfoMap = app.contacts().getContactInfo(contact);

        Assertions.assertEquals(expectedContactInfoMap, contactInfoMap);
    }
}

