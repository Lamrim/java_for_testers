package mantis.tests;

import mantis.common.CommonFunctions;
import org.junit.jupiter.api.Test;

public class JamesTests extends TestBase {

    @Test
    void canCreateUser() {
        app.jamesCli().addUser
                (String.format("%s@localhost", CommonFunctions.randomString(5)), "password");
    }

}
