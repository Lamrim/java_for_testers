package tests.contacts;

import common.CommonFunctions;
import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import tests.TestBase;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;
import java.util.Set;

public class ContactModificationTests extends TestBase {

    @Test
    public void canModifyContact() {
        if (app.hbm().getContactCount() == 0) {
            app.hbm().createContact(new ContactData());
        }
        var oldContacts = app.hbm().getContactList();
        var rnd = new Random();
        var index = rnd.nextInt(oldContacts.size());
        var testData = new ContactData()
                .withFirstName(CommonFunctions.randomString(7))
                .withLastName(CommonFunctions.randomString(7))
                .withAddress(CommonFunctions.randomString(7))
                .withEmail1(CommonFunctions.randomString(7))
                .withMobilePhone(CommonFunctions.randomString(7));

        app.contacts().modifyContact(oldContacts.get(index), testData);

        var newContacts = app.hbm().getContactList();
        var expectedList = new ArrayList<>(oldContacts);
        expectedList.set(index, testData.withId(oldContacts.get(index).id()));

        Assertions.assertEquals(Set.copyOf(newContacts), Set.copyOf(expectedList));
    }
}
