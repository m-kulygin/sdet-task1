package pages;

import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.CustomersFormUtil;
import utilities.MessageConstants;
import utilities.WaitHelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertFalse;

/**
 * Page class, that realises customers list actions.
 *
 * @author Max Kulygin
 */
public class BankManagerCustomersForm extends BankManagerPage {

    /**
     * Main Customers button, that opens customers list form.
     */
    @FindBy(css = "button[ng-class='btnClass3']")
    protected WebElement customersButton;

    @FindBy(xpath = "//a[contains(text(), 'First Name')]")
    private WebElement firstNameHeader;

    @FindBy(xpath = "//tbody/tr/td[1]")
    private List<WebElement> firstNamesList;

    @FindBy(xpath = "//table[@class='table table-bordered table-striped']//tbody/tr")
    private List<WebElement> customerRows;

    private final static By customersTableLocator =
            By.xpath("//table[@class='table table-bordered table-striped']");
    private final static By deleteCustomerButtonLocator =
            By.xpath(".//button[contains(text(), 'Delete')]");

    /**
     * Construct with WebDriver and WaitHelper.
     *
     * @param driver Chrome web driver
     */
    public BankManagerCustomersForm(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    /**
     * Click first name header to perform customers list sorting by first name.
     *
     * @return This form object (for Fluent)
     */
    @Step("Click on first name header in table to do sorting")
    public BankManagerCustomersForm clickFirstNameHeader() {
        WaitHelper.untilToBeClickable(firstNameHeader);
        firstNameHeader.click();
        return this;
    }

    /**
     * Verify that customers list is sorted
     */
    @Step("Checking customers list is sorted by first name")
    public void verifyFirstNamesSorted() {
        List<String> originalNames = firstNamesList.stream()
                .map(WebElement::getText)
                .toList();
        boolean isSortedAsc = checkFirstNamesSortedAscending(originalNames);
        boolean isSortedDesc = checkFirstNamesSortedDescending(originalNames);
        Assert.assertTrue(MessageConstants.MSG_CUSTOMERS_LIST_NOT_SORTED, isSortedAsc || isSortedDesc);
    }

    /**
     * Check that customers list is sorted ascending.
     *
     * @param originalNames Original names list
     * @return true if customers list is sorted (asc), false otherwise
     */
    @Step("Checking customers list is sorted by first name in ascending order")
    public boolean checkFirstNamesSortedAscending(List<String> originalNames) {
        List<String> ascSortedCopy = new ArrayList<>(originalNames);
        Collections.sort(ascSortedCopy);
        return originalNames.equals(ascSortedCopy);
    }

    /**
     * Check that customers list is sorted descending.
     *
     * @param originalNames Original names list
     * @return true if customers list is sorted (desc), false otherwise
     */
    @Step("Checking customers list is sorted by first name in ascending order")
    public boolean checkFirstNamesSortedDescending(List<String> originalNames) {
        List<String> descSortedCopy = new ArrayList<>(originalNames);
        descSortedCopy.sort(Collections.reverseOrder());
        return originalNames.equals(descSortedCopy);
    }

    /**
     * Delete a customer with first name length closest to average.
     *
     * @return This form object (for Fluent)
     */
    @Step("Deleting customer with first name length closest to average")
    public BankManagerCustomersForm deleteCustomerBasedOnAverageFirstNameLength() {
        WaitHelper.untilToBePresent(customersTableLocator);

        List<String> firstNames = CustomersFormUtil.extractFirstNamesFromTable(customerRows);
        double avgLength = CustomersFormUtil.calculateAverageNameLength(firstNames);

        String closestName = CustomersFormUtil.findClosestName(firstNames, avgLength);
        if (closestName == null) {
            throw new AssertionError(MessageConstants.MSG_NO_CUSTOMER_AVG_NAME);
        }

        List<String> accountNumbers = CustomersFormUtil.retrieveAccountNumbersForUser(closestName, customerRows);

        deleteCustomerWithName(closestName);

        verifyDeletion(accountNumbers);

        return this;
    }

    /**
     * Deleting customer.
     */
    @Step("Deleting customer")
    private void deleteCustomerWithName(String name) {
        customerRows.stream()
                .filter(row -> CustomersFormUtil.getRowFirstName(row).equals(name))
                .findAny()
                .ifPresent(targetRow -> targetRow
                        .findElement(deleteCustomerButtonLocator)
                        .click());
    }

    /**
     * Verify that customer is deleted.
     */
    @Step("Verify customer deletion")
    private void verifyDeletion(List<String> accountNumbers) {
        boolean anyAccountFound = customerRows.stream()
                .flatMap(CustomersFormUtil::getRowAccountNumbers)
                .anyMatch(accountNumbers::contains);
        assertFalse(MessageConstants.MSG_CUSTOMER_AVG_NAME_NOT_DELETED, anyAccountFound);
    }

    /**
     * Click main Customers button to open customers list form.
     *
     * @return This form object (for Fluent)
     */
    @Step("Click Customers button to open customers table")
    public BankManagerCustomersForm clickCustomersButton() {
        WaitHelper.untilToBeClickable(customersButton);
        customersButton.click();
        return this;
    }
}