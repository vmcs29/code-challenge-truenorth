package base;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.testng.annotations.*;
import pageObjects.HomePage;
import utilities.CommonUtilities;
import utilities.Log;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class BaseTest {

    public static EnvironmentProperties env = new EnvironmentProperties();
    protected String projectPath = System.getProperty("user.dir");
    protected Properties propi = EnvironmentProperties.getProperties();
    protected WebDriver driver;
    protected HomePage homePage;

    @BeforeSuite
    public void init() {
        Log.startLog("Test Suite is starting");
    }


    @BeforeTest()
    public void setup() {
        Log.info("Initialize WebDriver instance");
        driver = getDriver();

        Log.info("Deleting Cookies");
        driver.manage().deleteAllCookies();

        Log.info("Applying the implicit wait during 10 seconds");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        homePage = new HomePage(driver);
        Log.info("Open the web site to test");
        driver.get(propi.getProperty("webapp.website"));
        Log.info("Opening : " + propi.getProperty("webapp.website"));
    }

    @AfterTest
    public void teardown() {
        try {
            Log.endLog("Test node is ending");
            if (driver != null) {
                Log.endLog("Driver close");
                driver.close();
                driver.quit();
            }
        } catch (Exception ignored) {
        }

    }

    @AfterSuite
    public void end() {
        try {
            Log.endLog("Test Suite is ending");
        } catch (WebDriverException we) {
            Log.fatal(we.getMessage());
        }
    }

    public WebDriver getDriver() {
        if (driver == null)
            driver = connectDriver();
        return driver;
    }

    public WebDriver connectDriver() {
        String propBrowser = propi.getProperty("webapp.browser").toLowerCase();

        Properties propSystem = System.getProperties();
        Enumeration e = propSystem.propertyNames();

        Map<String, String> propMap = new HashMap<String, String>();
        propMap = CommonUtilities.putSysProps(e, propSystem);
        projectPath = propMap.get("user.dir");
        String os = propMap.get("os.name").trim().toLowerCase();

        if (propBrowser.isEmpty()) {
            throw new IllegalArgumentException("Missing parameter value for browser");
        } else {

            if (os.contains("mac")) {

                if (propBrowser.trim().contentEquals("chrome")) {

                    System.setProperty("webdriver.chrome.driver", projectPath + "/src/driver/chromedriver");

                        ChromeOptions op = new ChromeOptions();
                        if (propi.getProperty("webapp.headlessExecution").equals("true")) {
                            op.addArguments("--headless");
                        }
                        if (propi.getProperty("webapp.incognito").equals("true")) {
                            op.addArguments("--incognito");
                        }
                        op.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
                        op.setPageLoadStrategy(PageLoadStrategy.NONE);
                        op.addArguments("enable-automation");
                        op.addArguments("--disable-gpu");
                        op.addArguments("--disable-extensions");
                        op.addArguments("--no-sandbox");
                        op.addArguments("--disable-dev-shm-usage");

                        driver = new ChromeDriver(op);
                    }

                else {
                    throw new IllegalArgumentException("Browser name is not correct or is not valid...");
                }
            } else {
                throw new IllegalArgumentException("OS out of scope ...");
            }
        }

        return driver;
    }


}
