package tests;

import model.GroupData;
import org.junit.jupiter.api.Test;

public class GroupRemovalTests extends TestBase {
    @Test
    public void canRemoveGroup() {
        app.groups().openGroupsPage();
        if (app.groups().isGroupPresent()) {
            app.groups().createGroup(new GroupData("group1", "header", "footer"));
        }
        app.groups().removeGroup();
    }
}
