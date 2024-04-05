package mantis.manager;

import java.io.File;
import java.io.IOException;

public class JamesCliHelper extends HelperBase {

    public JamesCliHelper(ApplicationManager manager) {
        super(manager);
    }

    public void addUser(String email, String password) {
        ProcessBuilder builder = new ProcessBuilder();
        var command =
                String.format("java -cp \"james-server-jpa-app.lib/*\" org.apache.james.cli.ServerCmd AddUser %s %s",
                        email, password);
        builder.command("cmd.exe", "/c", command);
        builder.directory(new File(manager.property("james.workingDir")));
        try {
            Process process = builder.start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
