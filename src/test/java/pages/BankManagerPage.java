package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

/**
 * Base page class.
 *
 * @author Max Kulygin
 */
public class BankManagerPage {
    WebDriver driver;

    /**
     * Construct with WebDriver and WaitHelper.
     *
     * @param driver Chrome web driver
     */
    public BankManagerPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
}