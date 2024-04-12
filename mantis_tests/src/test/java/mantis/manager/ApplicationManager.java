package mantis.manager;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.Properties;

public class ApplicationManager {
    private WebDriver driver;
    private String browser;
    private Properties properties;

    private SessionHelper sessionHelper;
    private HttpSessionHelper httpSessionHelper;
    private JamesCliHelper jamesCliHelper;
    private JamesApiHelper jamesApiHelper;
    private MailHelper mailHelper;
    private DeveloperMailHelper developerMailHelper;
    private UserHelper userHelper;
    private RestApiHelper restHelper;
    private SoapApiHelper soapHelper;


    public void init(String browser, Properties properties) {
        this.browser = browser;
        this.properties = properties;
    }

    public WebDriver driver() {
        if (driver == null) {
            if ("chrome".equals(browser)) {
                driver = new ChromeDriver();
            } else if ("firefox".equals(browser)) {
                driver = new FirefoxDriver();
            } else {
                throw new IllegalArgumentException(String.format("Unknown browser %s", browser));
            }
            Runtime.getRuntime().addShutdownHook(new Thread(driver::quit));
            driver.get(properties.getProperty("web.baseUrl"));
            driver.manage().window().setSize(new Dimension(1368, 770));
        }
        return driver;
    }

    public SessionHelper session() {
        if (sessionHelper == null) {
            sessionHelper = new SessionHelper(this);
        }
        return sessionHelper;
    }

    public HttpSessionHelper http() {
        {
            if (httpSessionHelper == null) {
                httpSessionHelper = new HttpSessionHelper(this);
            }
            return httpSessionHelper;
        }
    }

    public JamesCliHelper jamesCli() {
        {
            if (jamesCliHelper == null) {
                jamesCliHelper = new JamesCliHelper(this);
            }
            return jamesCliHelper;
        }
    }

    public DeveloperMailHelper developerMail() {
        {
            if (developerMailHelper == null) {
                developerMailHelper = new DeveloperMailHelper(this);
            }
            return developerMailHelper;
        }
    }

    public JamesApiHelper jamesApi() {
        {
            if (jamesApiHelper == null) {
                jamesApiHelper = new JamesApiHelper(this);
            }
            return jamesApiHelper;
        }
    }

    public MailHelper mail() {
        {
            if (mailHelper == null) {
                mailHelper = new MailHelper(this);
            }
            return mailHelper;
        }
    }

    public UserHelper user() {
        {
            if (userHelper == null) {
                userHelper = new UserHelper(this);
            }
            return userHelper;
        }
    }

    public RestApiHelper rest() {
        {
            if (restHelper == null) {
                restHelper = new RestApiHelper(this);
            }
            return restHelper;
        }
    }


    public SoapApiHelper soap() {
        {
            if (soapHelper == null) {
                soapHelper = new SoapApiHelper(this);
            }
            return soapHelper;
        }
    }

    public String property(String name) {
        return properties.getProperty(name);
    }
}
