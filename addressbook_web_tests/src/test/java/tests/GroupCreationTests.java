package tests;

import model.GroupData;
import org.junit.jupiter.api.Test;

public class GroupCreationTests extends TestBase {

    @Test
    public void canCreateGroup() {
        app.openGroupsPage();
        app.createGroup(new GroupData("name", "header", "footer"));
    }

    @Test
    public void canCreateGroupWithEmptyName() {
        app.openGroupsPage();
        app.createGroup(new GroupData());
    }

    @Test
    public void canCreateGroupWithNameOnly() {
        app.openGroupsPage();
        app.createGroup(new GroupData().withName("some name"));
    }
}
