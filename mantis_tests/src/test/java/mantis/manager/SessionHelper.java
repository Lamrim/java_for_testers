package mantis.manager;

import mantis.common.CommonFunctions;
import org.apache.commons.exec.CommandLine;
import org.openqa.selenium.By;

public class SessionHelper extends HelperBase {
    public SessionHelper(ApplicationManager manager) {
        super(manager);
    }

    public void login(String user, String password) {
        type(By.id("username"), user);
        click(By.cssSelector("input[type='submit']"));
        type(By.id("password"), password);
        click(By.cssSelector("input[type='submit']"));
    }

    public boolean isLoggedIn() {
        return isElementPresent(By.cssSelector("span.user-info"));
    }

    public void signUp(String username, String email) {
        openSignUpPage();
        fillSignUpForm(username, email);
        submitSignUp();
    }

    public void proceedSignUp(String url, String password) {
        openEditAccountPage(url);
        fillAccountInformation(password);
        submitUpdateUser();
    }

    private void openEditAccountPage(String url) {
        openLink(url);
    }

    private void fillAccountInformation(String password) {
        type(By.id("realname"), CommonFunctions.randomString(5));
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
