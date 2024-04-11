package mantis.manager;

import mantis.common.CommonFunctions;
import org.openqa.selenium.By;

public class UserHelper extends HelperBase {
    public UserHelper(ApplicationManager manager) {
        super(manager);
    }

    public void signUp(String username, String email) {
        openSignUpPage();
        fillSignUpForm(username, email);
        submitSignUp();
    }


    public void proceedSignUp(String url, String realname, String password) {
        openEditAccountPage(url);
        fillAccountInformation(realname, password);
        submitUpdateUser();
    }

    private void openEditAccountPage(String url) {
        openLink(url);
    }

    private void fillAccountInformation(String realname, String password) {
        type(By.id("realname"), realname);
        type(By.id("password"), password);
        type(By.id("password-confirm"), password);
    }

    private void submitUpdateUser() {
        click(By.xpath("//button[@type='submit']"));
    }

    private void openSignUpPage() {
        click(By.linkText("Signup for a new account"));
    }

    private void fillSignUpForm(String username, String email) {
        type(By.id("username"), username);
        type(By.id("email-field"), email);
    }

    private void submitSignUp() {
        click(By.xpath("//input[@type='submit']"));
    }
}
