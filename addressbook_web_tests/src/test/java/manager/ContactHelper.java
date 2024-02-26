package manager;

import model.ContactData;
import org.openqa.selenium.By;

public class ContactHelper extends HelperBase {

    public ContactHelper(ApplicationManager manager) {
        super(manager);
    }

    public void createOneContact(ContactData contact) {
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
        returnToHomePage();
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

}
