package tests;

import manager.ApplicationManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Properties;

public class TestBase {

    protected static ApplicationManager app;

    @BeforeEach
    public void setUp() throws IOException, URISyntaxException {
        if (app == null) {
            var properties = new Properties();
            properties.load(new FileReader(System.getProperty("environment", "local.properties")));
            app = new ApplicationManager();
            app.init(System.getProperty("browser", "chrome"), properties);
        }
    }

//    @AfterEach
//    void checkDbConsistency() {
//        app.jdbc().checkConsistency();
//    }
}
