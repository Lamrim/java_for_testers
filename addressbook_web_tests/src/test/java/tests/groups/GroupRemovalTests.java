package tests.groups;

import model.GroupData;
import org.junit.jupiter.api.Test;
import tests.TestBase;

public class GroupRemovalTests extends TestBase {
    @Test
    public void canRemoveGroup() {
        if (!app.groups().isGroupPresent()) {
            app.groups().createGroup(new GroupData("group1", "header", "footer"));
        }
        app.groups().removeGroup();
    }
}
