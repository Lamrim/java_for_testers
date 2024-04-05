package mantis.tests;

import mantis.common.CommonFunctions;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.regex.Pattern;

public class UserRegistrationTests extends TestBase {

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
        app.session().signUp(username, email);
        // ждем почту (MailHelper)
        var messages = app.mail().receive(email, password, Duration.ofSeconds(10));
        // извлечть ссылку из письма
        var text = messages.getFirst().content();
        var pattern = Pattern.compile("http://\\S*");
        var matcher = pattern.matcher(text);
        if (matcher.find()) {
            url = text.substring(matcher.start(), matcher.end());
        }
        // перейти по ссылке и завершить регистрацию (браузер)
        app.session().proceedSignUp(url, newPassword);
        // проверить, что пользователь может залогиниться (HttpSessionHelper)
        app.http().login(username, newPassword);
        Assertions.assertTrue(app.http().isLoggedIn());
    }
}
