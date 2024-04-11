package mantis.tests;

import mantis.common.CommonFunctions;
import mantis.model.DeveloperMailUser;
import mantis.model.UserData;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.Duration;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class UserRegistrationTests extends TestBase {

    DeveloperMailUser user;

    public static Stream<UserData> randomUserProvider() {
        Supplier<UserData> randomGroup = () -> new UserData()
                .withEmail(String.format("%s@localhost", CommonFunctions.randomString(7)))
                .withUsername(CommonFunctions.randomString(7))
                .withRealname(CommonFunctions.randomString(7))
                .withPassword(CommonFunctions.randomString(7));
        return Stream.generate(randomGroup).limit(1);
    }

    @ParameterizedTest
    @MethodSource("randomUserProvider")
    void canRegisterUser(UserData user) {
        // создать пользователя на почтовом сервере (JamesHelper)
        var url = "";
        app.jamesCli().addUser(user.email(), user.password());
        // заполнить форму создания (браузер)
        app.user().signUp(user.username(), user.email());
        // ждем почту (MailHelper)
        var messages = app.mail().receive(user.email(), user.password(), Duration.ofSeconds(10));
        // извлечть ссылку из письма
        var text = messages.getFirst().content();
        url = CommonFunctions.extractUrl(text, url);
        // перейти по ссылке и завершить регистрацию (браузер)
        app.user().proceedSignUp(url, user.realname(), user.password());
        // проверить, что пользователь может залогиниться (HttpSessionHelper)
        app.http().login(user.username(), user.password());
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

        app.user().proceedSignUp(url, CommonFunctions.randomString(5), newPassword);
        app.http().login(user.name(), newPassword);
        Assertions.assertTrue(app.http().isLoggedIn());
    }

    @ParameterizedTest
    @MethodSource("randomUserProvider")
    void canRegisterUserWithRestAPI(UserData user) {
        var url = "";
        app.jamesApi().addUser(user.email(), user.password());
        app.rest().startRegisterUser(new UserData()
                .withUsername(user.username())
                .withEmail(user.email()));

        var messages = app.mail().receive(user.email(), user.password(), Duration.ofSeconds(10));
        var text = messages.getFirst().content();
        url = CommonFunctions.extractUrl(text, url);

        app.user().proceedSignUp(url, user.realname(), user.password());
        app.http().login(user.username(), user.password());
        Assertions.assertTrue(app.http().isLoggedIn());
    }


    @AfterEach
    void deleteMailUser() {
        if (user != null) {
            app.developerMail().deleteUser(user);
        }
    }
}
