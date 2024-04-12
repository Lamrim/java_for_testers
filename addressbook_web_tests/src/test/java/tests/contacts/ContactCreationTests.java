package tests.contacts;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import common.CommonFunctions;
import model.ContactData;
import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import tests.TestBase;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class ContactCreationTests extends TestBase {

    public static List<ContactData> contactProvider() throws IOException {
        var result = new ArrayList<ContactData>();

        ObjectMapper mapper = new ObjectMapper();
        var value = mapper.readValue(new File("contacts.json"), new TypeReference<List<ContactData>>() {
        });
        result.addAll(value);
        return result;
    }

    public static List<ContactData> singleRandomContactProvider() throws IOException {
        return List.of(new ContactData()
                .withFirstName(CommonFunctions.randomString(7))
                .withLastName(CommonFunctions.randomString(7))
                .withAddress(CommonFunctions.randomString(7))
                .withEmail1(CommonFunctions.randomString(7))
                .withEmail2(CommonFunctions.randomString(7))
                .withEmail3(CommonFunctions.randomString(7))
                .withMobilePhone(CommonFunctions.randomString(7))
                .withHomePhone(CommonFunctions.randomString(7))
                .withWorkPhone(CommonFunctions.randomString(7)));
    }

    @ParameterizedTest
    @MethodSource("contactProvider")
    public void canCreateMultipleContacts(ContactData contact) {
        var oldContacts = app.hbm().getContactList();
        app.contacts().createContact(contact);
        var newContacts = app.hbm().getContactList();
        var expectedList = new ArrayList<>(oldContacts);
        expectedList.add(contact.withId(newContacts.getLast().id()));
        Assertions.assertEquals(Set.copyOf(newContacts), Set.copyOf(expectedList));
    }

    @ParameterizedTest
    @MethodSource("singleRandomContactProvider")
    public void canCreateSingleContact(ContactData contact) {
        var oldContacts = app.hbm().getContactList();
        app.contacts().createContact(contact);
        var newContacts = app.hbm().getContactList();
        var expectedList = new ArrayList<>(oldContacts);
        expectedList.add(contact.withId(newContacts.getLast().id()));
        Assertions.assertEquals(Set.copyOf(newContacts), Set.copyOf(expectedList));
    }

    @ParameterizedTest
    @MethodSource("singleRandomContactProvider")
    void canCreateContactInGroup(ContactData contact) {
        if (app.hbm().getGroupCount() == 0) {
            app.hbm().createGroup(new GroupData());
        }
        var group = app.hbm().getGroupList().getFirst();

        var oldRelated = app.hbm().getContactsInGroup(group);
        app.contacts().createContact(contact, group);
        var newRelated = app.hbm().getContactsInGroup(group);
        var expectedRelated = new ArrayList<>(oldRelated);
        expectedRelated.add(contact.withId(newRelated.getLast().id()));

        Assertions.assertEquals(Set.copyOf(newRelated), Set.copyOf(expectedRelated));

    }

    @Test
    public void canCreateChainOfContacts() {
        app.contacts().createChainOfContacts(new ContactData());
    }

}
