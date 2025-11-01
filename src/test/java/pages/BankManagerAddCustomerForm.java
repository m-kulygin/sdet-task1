package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.MessageConstants;
import utilities.WaitHelper;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Page class, that realises customer addition actions.
 *
 * @author Max Kulygin
 */
public class BankManagerAddCustomerForm extends BankManagerPage {

    /**
     * Main Add Customer button, that opens customer addition form.
     */
    @FindBy(css = "button[ng-class='btnClass1']")
    protected WebElement addCustomerButton;

    @FindBy(css = "input[ng-model='fName']")
    private WebElement firstNameField;

    @FindBy(css = "input[ng-model='lName']")
    private WebElement lastNameField;

    @FindBy(css = "input[ng-model='postCd']")
    private WebElement postCodeField;

    @FindBy(css = "button[type=submit]")
    private WebElement submitAddCustomerButton;

    /**
     * Construct with WebDriver and WaitHelper.
     *
     * @param driver Chrome web driver
     */
    public BankManagerAddCustomerForm(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    /**
     * Fill first name field on customer addition form.
     *
     * @param firstName First name value
     * @return This form object (for Fluent)
     */
    @Step("Fill first name field with walue [{firstName}]")
    public BankManagerAddCustomerForm fillFirstNameField(String firstName) {
        WaitHelper.untilToBeClickable(firstNameField);
        firstNameField.sendKeys(firstName);
        return this;
    }

    /**
     * Fill last name field on customer addition form.
     *
     * @param lastName Last name value
     * @return This form object (for Fluent)
     */
    @Step("Fill last name field with value [{lastName}]")
    public BankManagerAddCustomerForm fillLastNameField(String lastName) {
        WaitHelper.untilToBeClickable(lastNameField);
        lastNameField.sendKeys(lastName);
        return this;
    }

    /**
     * Fill post code field on customer addition form.
     *
     * @param postCode Post code value
     * @return This form object (for Fluent)
     */
    @Step("Fill post code field with value [{postCode}]")
    public BankManagerAddCustomerForm fillPostCodeField(String postCode) {
        WaitHelper.untilToBeClickable(postCodeField);
        postCodeField.sendKeys(postCode);
        return this;
    }

    /**
     * Click submit Add Customer button to create a customer with specified fields' values.
     *
     * @return This form object (for Fluent)
     */
    @Step("Click Add Customer button to submit customer creation")
    public BankManagerAddCustomerForm clickSubmitAddCustomerButton() {
        WaitHelper.untilToBeClickable(submitAddCustomerButton);
        submitAddCustomerButton.click();
        return this;
    }

    /**
     * Verify that an alert is present with a message containing text.
     *
     * @return This form object (for Fluent)
     */
    @Step("Verify presence of an alert about successful customer creation")
    public BankManagerAddCustomerForm verifyCustomerAddedAlertPresent() {
        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();
        alert.accept();
        boolean alertTextContainsSubstring = alertText.contains(MessageConstants.MSG_CUSTOMER_ADD_SUCCESS_SUBSTRING);
        assertTrue(MessageConstants.MSG_CUSTOMER_ADD_ALERT_REQUIRES
                        + " \"" + MessageConstants.MSG_CUSTOMER_ADD_SUCCESS_SUBSTRING + "\"",
                alertTextContainsSubstring);
        return this;
    }

    /**
     * Click main Add Customer button to open customer addition form.
     *
     * @return This form object (for Fluent)
     */
    @Step("Click base Add Customer button to open customer adding form")
    public BankManagerAddCustomerForm clickAddCustomerButton() {
        WaitHelper.untilToBeClickable(addCustomerButton);
        addCustomerButton.click();
        return this;
    }
}