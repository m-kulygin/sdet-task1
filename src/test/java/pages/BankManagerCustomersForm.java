package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utilities.CustomersFormUtil;
import utilities.MessageConstants;
import utilities.WaitHelper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Page class, that realises customers list actions.
 *
 * @author Max Kulygin
 */
public class BankManagerCustomersForm extends BankManagerPage {

    private final static By customersTableLocator = By.xpath("//table[@class='table table-bordered table-striped']");
    private final static By deleteCustomerButtonLocator = By.xpath(".//button[contains(text(), 'Delete')]");

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

    /**
     * Construct with WebDriver and WaitHelper.
     *
     * @param driver Chrome web driver
     */
    public BankManagerCustomersForm(WebDriver driver) {
        super(driver);
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
     * Verify that customers list is sorted ascending.
     *
     * @return This form object (for Fluent)
     */
    @Step("Verifying customers list is sorted by first name in ascending order")
    public BankManagerCustomersForm verifyFirstNamesSortedAscending() {
        List<String> originalNames = CustomersFormUtil.getFirstNamesFromWebElements(firstNamesList);
        List<String> ascSortedCopy = new ArrayList<>(originalNames);
        Collections.sort(ascSortedCopy);
        boolean sortedAsc = originalNames.equals(ascSortedCopy);
        assertTrue(MessageConstants.MSG_CUSTOMERS_LIST_NOT_SORTED_ASC, sortedAsc);
        return this;
    }

    /**
     * Verify that customers list is sorted descending.
     *
     * @return This form object (for Fluent)
     */
    @Step("Verifying customers list is sorted by first name in ascending order")
    public BankManagerCustomersForm verifyFirstNamesSortedDescending() {
        List<String> originalNames = CustomersFormUtil.getFirstNamesFromWebElements(firstNamesList);
        List<String> descSortedCopy = new ArrayList<>(originalNames);
        boolean sortedDesc = originalNames.equals(descSortedCopy);
        assertTrue(MessageConstants.MSG_CUSTOMERS_LIST_NOT_SORTED_DESC, sortedDesc);
        return this;
    }

    /**
     * Find all customers first names, which have first name lengths closest to average.
     *
     * @return Closest to average first names
     */
    @Step("Find customers with first name lengths closest to average")
    public List<String> findCustomersNamesWithFirstNameLengthsClosestToAverage() {
        WaitHelper.untilToBePresent(customersTableLocator);
        List<String> firstNames = CustomersFormUtil.extractFirstNamesFromTable(customerRows);
        double avgLength = CustomersFormUtil.calculateAverageNameLength(firstNames);
        return CustomersFormUtil.findClosestNames(firstNames, avgLength);
    }

    /**
     * Delete customers by first names list.
     *
     * @param firstNamesList First names list
     * @return This form object (for Fluent)
     */
    @Step("Deleting customers by first names")
    public BankManagerCustomersForm deleteCustomersByFirstNames(List<String> firstNamesList) {
        if (firstNamesList == null || firstNamesList.isEmpty()) {
            throw new AssertionError(MessageConstants.MSG_NO_CUSTOMER_AVG_NAME);
        }
        WaitHelper.untilToBePresent(customersTableLocator);
        for (String firstName : firstNamesList) {
            deleteCustomerWithName(firstName);
        }
        return this;
    }

    /**
     * Verify customers deletion by first names list.
     *
     * @param firstNamesList First names list
     * @return This form object (for Fluent)
     */
    @Step("Verify customers deletion by first names")
    public BankManagerCustomersForm verifyCustomersDeletionByFirstNames(List<String> firstNamesList) {
        WaitHelper.untilToBePresent(customersTableLocator);
        for (String firstName : firstNamesList) {
            List<String> accountNumbers = CustomersFormUtil.retrieveAccountNumbersForUser(firstName, customerRows);
            verifyDeletion(accountNumbers);
        }
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
                .map(CustomersFormUtil::getRowAccountNumbers)
                .flatMap(Collection::stream)
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
