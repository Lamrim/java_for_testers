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
        fillContactForm();
        submitContactCreation();
        returnToHomePage();
    }

    public void createTwoContacts(ContactData contact) {
        openHomePage();
        initContactCreation();
        fillContactForm();
        submitContactCreation();
        addNextContact();
        fillContactForm();
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

    private void fillContactForm() {
        type(By.name("firstname"), "first name");
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
