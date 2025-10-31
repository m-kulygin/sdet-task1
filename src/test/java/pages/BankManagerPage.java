package pages;

import org.openqa.selenium.WebDriver;
import utilities.WaitHelper;


public class BankManagerPage {
    WebDriver driver;
    WaitHelper waiter;

    public BankManagerPage(WebDriver driver, WaitHelper waiter) {
        this.driver = driver;
        this.waiter = waiter;
    }
}
