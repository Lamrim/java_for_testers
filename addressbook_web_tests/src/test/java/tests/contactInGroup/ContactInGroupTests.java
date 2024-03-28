package tests.contactInGroup;

import common.CommonFunctions;
import model.ContactData;
import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import tests.TestBase;

import java.util.ArrayList;
import java.util.Random;

public class ContactInGroupTests extends TestBase {

    @Test
    void canAddSingleContactInGroup() {
        if (app.hbm().getGroupCount() == 0) {
            app.hbm().createGroup(new GroupData().withName(CommonFunctions.randomString(7)));
        }
        if (app.hbm().getContactCount() == 0) {
            app.hbm().createContact(new ContactData().withFirstName(CommonFunctions.randomString(7)));
        }
        var group = app.hbm().getGroupList().getFirst();
        var oldRelated = app.hbm().getContactsInGroup(group);
        var contactList = app.hbm().getContactList();

        var contactWithoutGroup = new ArrayList<>(contactList);
        contactWithoutGroup.removeAll(oldRelated);

        if (contactWithoutGroup.isEmpty()) {
            app.hbm().createContact(new ContactData().withFirstName(CommonFunctions.randomString(7)));
            oldRelated = app.hbm().getContactsInGroup(group);
            contactList = app.hbm().getContactList();
            contactWithoutGroup = new ArrayList<>(contactList);
            contactWithoutGroup.removeAll(oldRelated);
        }

        app.contacts().addContactInGroup(contactWithoutGroup.getFirst(), group);
        var newRelated = app.hbm().getContactsInGroup(group);
        var expectedRelated = new ArrayList<>(oldRelated);

        expectedRelated.add(contactWithoutGroup.getFirst());

        Assertions.assertEquals(newRelated, expectedRelated);
    }

    @Test
    void canRemoveSingleContactFromGroup() {
        if (app.hbm().getGroupCount() == 0) {
            app.hbm().createGroup(new GroupData().withName(CommonFunctions.randomString(7)));
        }
        var group = app.hbm().getGroupList().getFirst();
        var contactsInGroup = app.hbm().getContactsInGroup(group);
        if (contactsInGroup.isEmpty()) {
            app.contacts().createContact(new ContactData().withFirstName(CommonFunctions.randomString(7)), group);
        }
        var oldRelated = app.hbm().getContactsInGroup(group);

        var rnd = new Random();
        var index = rnd.nextInt(oldRelated.size());

        app.contacts().removeContactFromGroup(oldRelated.get(index), group);

        var newRelated = app.hbm().getContactsInGroup(group);
        var expectedRelated = new ArrayList<>(oldRelated);
        expectedRelated.remove(index);

        Assertions.assertEquals(newRelated, expectedRelated);
    }

//    private static Comparator<ContactData> getCompareById() {
//        Comparator<ContactData> compareById = (o1, o2) ->
//                Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
//        return compareById;
//    }
}
