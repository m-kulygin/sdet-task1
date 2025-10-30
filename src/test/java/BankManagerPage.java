import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class BankManagerPage {
    protected final WebDriver driver;
    protected final WebDriverWait wait;

    @FindBy(css = "button[ng-class='btnClass1']")
    protected WebElement addCustomerButton;

    @FindBy(css = "button[ng-class='btnClass2']")
    protected WebElement openAccountButton;

    @FindBy(css = "button[ng-class='btnClass3']")
    protected WebElement customersButton;


    protected BankManagerPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(1));
        PageFactory.initElements(driver, this);
    }

    protected WebElement getWhenClickable(WebElement element) {
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public BankManagerPage clickAddCustomerButton() {
        getWhenClickable(addCustomerButton).click();
        return this;
    }

    public BankManagerPage clickOpenAccountButton() {
        getWhenClickable(openAccountButton).click();
        return this;
    }

    public BankManagerPage clickCustomersButton() {
        getWhenClickable(customersButton).click();
        return this;
    }


}
