package manager;

import model.ContactData;
import org.openqa.selenium.By;

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

    public void createFewContacts(ContactData contact) {
        openHomePage();
        initContactCreation();
        for (int i = 0; i < 3; i++) {
            fillContactForm(contact);
            submitContactCreation();
            addNextContact();
        }
        fillContactForm(contact);
        submitContactCreation();
        returnToHomePage();
    }

    public void removeContact() {
        openHomePage();
        selectContact();
        removeSelectedContacts();
    }

    public void removeAllContacts() {
        openHomePage();
        selectAllContacts();
        removeSelectedContacts();
    }


    private void openHomePage() {
        if (!manager.IsElementPresent(By.name("MainForm"))) {
            click(By.linkText("home"));
        }
    }

    private void initContactCreation() {
        click(By.linkText("add new"));
    }

    private void fillContactForm(ContactData contact) {
        type(By.name("firstname"), contact.firstName());
        type(By.name("lastname"), contact.lastName());
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

    public boolean isContactPresent() {
        openHomePage();
        return manager.IsElementPresent(By.name("selected[]"));
    }

    public void removeSelectedContacts() {
        click(By.xpath("//input[@value='Delete']"));
    }

    private void selectContact() {
        click(By.name("selected[]"));
    }

    private void selectAllContacts() {
        click(By.id("MassCB"));
    }
}
