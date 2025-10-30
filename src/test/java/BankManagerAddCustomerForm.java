import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;

public class BankManagerAddCustomerForm extends BankManagerPage{
    @FindBy(css = "input[type=text][ng-model='fName']")
    private WebElement firstNameField;

    @FindBy(css = "input[type=text][ng-model='lName']")
    private WebElement lastNameField;

    @FindBy(css = "input[type=text][ng-model='postCd']")
    private WebElement postCodeField;

    @FindBy(css = "button[type=submit][class='btn btn-default']")
    private WebElement submitAddCustomerButton;

    public BankManagerAddCustomerForm(WebDriver driver) {
        super(driver);
    }

    public BankManagerAddCustomerForm fillFirstNameField(String firstName) {
        getWhenClickable(firstNameField).sendKeys(firstName);
        return this;
    }

    public BankManagerAddCustomerForm fillLastNameField(String lastName) {
        getWhenClickable(lastNameField).sendKeys(lastName);
        return this;
    }

    public BankManagerAddCustomerForm fillPostCodeField(String postCode) {
        getWhenClickable(postCodeField).sendKeys(postCode);
        return this;
    }

    public BankManagerAddCustomerForm clickSubmitAddCustomerButton() {
        getWhenClickable(submitAddCustomerButton).click();
        return this;
    }

    public BankManagerAddCustomerForm verifyCustomerAddedAlertPresent() throws InterruptedException {
        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();
        alert.accept();
        assertThat(alertText, containsString("Customer added successfully"));
        return this;
    }

    @Override
    public BankManagerAddCustomerForm clickAddCustomerButton() {
        getWhenClickable(addCustomerButton).click();
        return this;
    }
}
