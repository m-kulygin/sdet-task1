package tests;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.WaitHelper;

import java.time.Duration;
import java.util.UUID;

/**
 * Base test class.
 *
 * @author Max Kulygin
 */
public abstract class BaseTest {

    private final static String URL = "https://www.globalsqa.com/angularJs-protractor/BankingProject/#/manager";
    private final static long WAIT_TIME_IN_SECONDS = 2L;

    WebDriver driver;
    WebDriverWait webDriverWait;

    /**
     * Setting up base test class object: chrome driver and waiters initialization.
     */
    @Before
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--headless");
        options.addArguments("--user-data-dir=/tmp/chrome-profile-" + UUID.randomUUID());
        driver = new ChromeDriver(options);
        webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIME_IN_SECONDS));
        WaitHelper.setWait(webDriverWait);
        driver.get(URL);
    }

    /**
     * Closing drivers.
     */
    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}