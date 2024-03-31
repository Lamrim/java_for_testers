package manager;

import model.ContactData;
import model.GroupData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ContactHelper extends HelperBase {

    public ContactHelper(ApplicationManager manager) {
        super(manager);
    }

    public void createContact(ContactData contact) {
        openHomePage();
        initContactCreation();
        fillContactForm(contact);
        submitContactCreation();
        returnToHomePage();
    }

    public void createContact(ContactData contact, GroupData group) {
        openHomePage();
        initContactCreation();
        fillContactForm(contact);
        selectGroupOnContactCard(group);
        submitContactCreation();
        returnToHomePage();
    }

    public void createChainOfContacts(ContactData contact) {
        openHomePage();
        initContactCreation();
        for (int i = 0; i < 2; i++) {
            fillContactForm(contact);
            submitContactCreation();
            addNextContact();
        }
        fillContactForm(contact);
        submitContactCreation();
        returnToHomePage();
    }

    public void removeContact(ContactData contact) {
        openHomePage();
        selectContact(contact);
        removeSelectedContacts();
    }

    public void removeAllContacts() {
        openHomePage();
        selectAllContacts();
        removeSelectedContacts();
    }

    public void modifyContact(ContactData contact, ContactData modifiedData) {
        openHomePage();
        initContactModification(contact);
        fillContactForm(modifiedData);
        submitContactModification();
        returnToHomePage();
    }

    public void addContactInGroup(ContactData contact, GroupData group) {
        openHomePage();
        selectContact(contact);
        selectGroupOnHomePage(group);
        addContactToGroup();
        returnToHomePage();
    }

    public void removeContactFromGroup(ContactData contact, GroupData group) {
        openHomePage();
        selectGroupPage(group);
        selectContact(contact);
        sumbitRemovalContactFromGroup();
        returnToGroupPage(group);
    }

    private void selectGroupOnContactCard(GroupData group) {
        new Select(manager.driver.findElement(By.name("new_group"))).selectByValue(group.id());
    }

    private void selectGroupOnHomePage(GroupData group) {
        new Select(manager.driver.findElement(By.name("to_group"))).selectByValue(group.id());
    }

    private void openHomePage() {
        click(By.linkText("home"));
        new Select(manager.driver.findElement(By.name("group"))).selectByValue("");
    }

    private void initContactCreation() {
        click(By.linkText("add new"));
    }

    private void fillContactForm(ContactData contact) {
        type(By.name("firstname"), contact.firstName());
        type(By.name("lastname"), contact.lastName());
        attach(By.name("photo"), String.valueOf(contact.photo()));
        type(By.name("address"), contact.address());
        type(By.name("email"), contact.email());
        type(By.name("email2"), contact.email2());
        type(By.name("email3"), contact.email3());
        type(By.name("mobile"), contact.mobilePhone());
        type(By.name("home"), contact.homePhone());
        type(By.name("work"), contact.workPhone());
    }


    private void submitContactCreation() {
        click(By.name("submit"));
    }

    private void returnToHomePage() {
        click(By.linkText("home"));
    }

    private void addNextContact() {
        click(By.linkText("add next"));
    }

    public void removeSelectedContacts() {
        click(By.xpath("//input[@value='Delete']"));
    }

    private void selectContact(ContactData contact) {
        click(By.cssSelector(String.format("input[value='%s']", contact.id())));
    }

    private void initContactModification(ContactData contact) {
        click(By.xpath(String.format("//a[@href='edit.php?id=%s']", contact.id())));
    }

    private void submitContactModification() {
        click(By.name("update"));
    }

    private void selectAllContacts() {
        click(By.id("MassCB"));
    }

    private void addContactToGroup() {
        click(By.name("add"));
    }

    public int getCount() {
        openHomePage();
        return manager.driver.findElements(By.name("selected[]")).size();
    }

    public ArrayList<ContactData> getList() {
        openHomePage();
        var contacts = new ArrayList<ContactData>();
        var rows = manager.driver.findElements(By.xpath("//table/tbody/tr[@name='entry']"));
        for (var row : rows) {
            var id = row.findElement(By.name("selected[]")).getAttribute("id");
            var lastName = row.findElement(By.cssSelector("td:nth-child(2)")).getText();
            var firstName = row.findElement(By.cssSelector("td:nth-child(3)")).getText();
            var address = row.findElement(By.cssSelector("td:nth-child(4)")).getText();
            var email1 = row.findElement(By.cssSelector("td:nth-child(5)")).getText();
            var mobilePhone = row.findElement(By.cssSelector("td:nth-child(6)")).getText();
            contacts.add(new ContactData()
                    .withId(id)
                    .withLastName(lastName)
                    .withFirstName(firstName)
                    .withAddress(address)
                    .withEmail1(email1)
                    .withMobilePhone(mobilePhone));
        }
        return contacts;
    }

    private void returnToGroupPage(GroupData group) {
        click(By.xpath(String.format("//a[@href='./?group=%s']", group.id())));
    }

    private void sumbitRemovalContactFromGroup() {
        click(By.name("remove"));
    }

    private void selectGroupPage(GroupData group) {
        new Select(manager.driver.findElement(By.name("group"))).selectByValue(group.id());
    }

    public String getPhones(ContactData contact) {
        return manager.driver.findElement(By.xpath(
                String.format("//input[@id='%s']/../../td[6]", contact.id()))).getText();
    }

    public Map<String, String> getPhones() {
        openHomePage();
        var result = new HashMap<String, String>();
        List<WebElement> rows = manager.driver.findElements(By.name("entry"));
        for (WebElement row : rows) {
            var id = row.findElement(By.name("selected[]")).getAttribute("id");
            var phones = row.findElements(By.tagName("td")).get(5).getText();
            result.put(id, phones);
        }
        return result;
    }

    public String getEmails(ContactData contact) {
        return manager.driver.findElement(By.xpath(
                String.format("//input[@id='%s']/../../td[5]", contact.id()))).getText();
    }

    public Map<String, String> getContactInfo(ContactData contact) {
        openHomePage();
        Map<String, String> contactInfoMap = new HashMap<>();
        contactInfoMap.put("address", manager.driver.findElement(By.xpath(
                String.format("//input[@id='%s']/../../td[4]", contact.id()))).getText());
        contactInfoMap.put("emails", manager.driver.findElement(By.xpath(
                String.format("//input[@id='%s']/../../td[5]", contact.id()))).getText());
        contactInfoMap.put("phones", manager.driver.findElement(By.xpath(
                String.format("//input[@id='%s']/../../td[6]", contact.id()))).getText());
        return contactInfoMap;
    }
}
