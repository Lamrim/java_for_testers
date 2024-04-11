package mantis.tests;

import mantis.common.CommonFunctions;
import mantis.model.DeveloperMailUser;
import mantis.model.UserData;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.regex.Pattern;

public class UserRegistrationTests extends TestBase {

    DeveloperMailUser user;

    @Test
    void canRegisterUser() {
        // создать пользователя на почтовом сервере (JamesHelper)
        var email = String.format("%s@localhost", CommonFunctions.randomString(5));
        var username = CommonFunctions.randomString(7);
        var password = CommonFunctions.randomString(7);
        var newPassword = CommonFunctions.randomString(7);
        var url = "";
        app.jamesCli().addUser(email, password);
        // заполнить форму создания (браузер)
        app.user().signUp(username, email);
        // ждем почту (MailHelper)
        var messages = app.mail().receive(email, password, Duration.ofSeconds(10));
        // извлечть ссылку из письма
        var text = messages.getFirst().content();
        url = CommonFunctions.extractUrl(text, url);
        // перейти по ссылке и завершить регистрацию (браузер)
        app.user().proceedSignUp(url, newPassword);
        // проверить, что пользователь может залогиниться (HttpSessionHelper)
        app.http().login(username, newPassword);
        Assertions.assertTrue(app.http().isLoggedIn());
    }

    @Test
    void canRegisterUserWithDeveloperMail() {
        var newPassword = CommonFunctions.randomString(7);
        var url = "";

        user = app.developerMail().addUser();

        var email = String.format("%s@developermail.com", user.name());
        app.user().signUp(user.name(), email);
        var message = app.developerMail().receive(user, Duration.ofSeconds(10));
        url = CommonFunctions.extractUrl(message, url);

        app.user().proceedSignUp(url, newPassword);
        app.http().login(user.name(), newPassword);
        Assertions.assertTrue(app.http().isLoggedIn());
    }

    @Test
    void canRegisterUserWithRestAPI() {
        var email = String.format("%s@localhost", CommonFunctions.randomString(5));
        var username = CommonFunctions.randomString(7);
        var password = CommonFunctions.randomString(7);
        var newPassword = CommonFunctions.randomString(7);
        var url = "";
        app.jamesApi().addUser(email, password);
        app.rest().startRegisterUser(new UserData()
                .withUsername(username)
                .withEmail(email));

        var messages = app.mail().receive(email, password, Duration.ofSeconds(60));
        var text = messages.getFirst().content();
        url = CommonFunctions.extractUrl(text, url);

        app.user().proceedSignUp(url, newPassword);
        app.http().login(username, newPassword);
        Assertions.assertTrue(app.http().isLoggedIn());
    }


    @AfterEach
    void deleteMailUser() {
        if (user != null) {
            app.developerMail().deleteUser(user);
        }
    }
}
