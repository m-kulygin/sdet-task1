package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.WaitHelper;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;

public class BankManagerAddCustomerForm extends BankManagerPage {

    @FindBy(css = "button[ng-class='btnClass1']")
    protected WebElement addCustomerButton;

    @FindBy(css = "input[type=text][ng-model='fName']")
    private WebElement firstNameField;

    @FindBy(css = "input[type=text][ng-model='lName']")
    private WebElement lastNameField;

    @FindBy(css = "input[type=text][ng-model='postCd']")
    private WebElement postCodeField;

    @FindBy(css = "button[type=submit][class='btn btn-default']")
    private WebElement submitAddCustomerButton;

    public BankManagerAddCustomerForm(WebDriver driver, WaitHelper waiter) {
        super(driver, waiter);
        PageFactory.initElements(driver, this);
    }

    @Step("Заполнение поля first name значением {firstName}")
    public BankManagerAddCustomerForm fillFirstNameField(String firstName) {
        waiter.untilToBeClickable(firstNameField);
        firstNameField.sendKeys(firstName);
        return this;
    }

    @Step("Заполнение поля last name значением {lastName}")
    public BankManagerAddCustomerForm fillLastNameField(String lastName) {
        waiter.untilToBeClickable(lastNameField);
        lastNameField.sendKeys(lastName);
        return this;
    }

    @Step("Заполнение поля post code значением {postCode}")
    public BankManagerAddCustomerForm fillPostCodeField(String postCode) {
        waiter.untilToBeClickable(postCodeField);
        postCodeField.sendKeys(postCode);
        return this;
    }

    @Step("Нажатие на кнопку Add Customer для добавления клиента")
    public BankManagerAddCustomerForm clickSubmitAddCustomerButton() {
        waiter.untilToBeClickable(submitAddCustomerButton);
        submitAddCustomerButton.click();
        return this;
    }

    @Step("Проверка наличия алерта об успешном добавлении клиента")
    public BankManagerAddCustomerForm verifyCustomerAddedAlertPresent() throws InterruptedException {
        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();
        alert.accept();
        assertThat(alertText, containsString("Customer added successfully"));
        return this;
    }

    @Step("Нажатие на кнопку Add Customer для открытия формы")
    public BankManagerAddCustomerForm clickAddCustomerButton() {
        waiter.untilToBeClickable(addCustomerButton);
        addCustomerButton.click();
        return this;
    }
}
