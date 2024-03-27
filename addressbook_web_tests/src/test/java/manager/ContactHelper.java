package manager;

import model.ContactData;
import org.openqa.selenium.By;

import java.util.ArrayList;

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

    private void openHomePage() {
        click(By.linkText("home"));
    }

    private void initContactCreation() {
        click(By.linkText("add new"));
    }

    private void fillContactForm(ContactData contact) {
        type(By.name("firstname"), contact.firstName());
        type(By.name("lastname"), contact.lastName());
        attach(By.name("photo"), String.valueOf(contact.photo()));
        type(By.name("address"), contact.address());
        type(By.name("email"), contact.email1());
        type(By.name("mobile"), contact.mobilePhone());
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
}
