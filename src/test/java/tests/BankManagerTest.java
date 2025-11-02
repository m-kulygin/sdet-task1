package tests;

import io.qameta.allure.Owner;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import pages.BankManagerAddCustomerForm;
import pages.BankManagerCustomersForm;
import utilities.DataGenerator;

import java.util.List;

/**
 * Class, that realises test-cases.
 *
 * @author Max Kulygin
 */
@DisplayName("Test-cases for autotesting XYZBank manager form")
public class BankManagerTest extends BaseTest {

    /**
     * Test-case 1 realisation: customer addition.
     */
    @Test
    @DisplayName("Test-case 1: customer addition")
    @Owner("Max Kulygin")
    public void testAddCustomer() {
        String postCode = DataGenerator.generatePostCode();
        String firstName = DataGenerator.generateFirstNameByPostCode(postCode);
        String lastName = DataGenerator.generateLastName();
        new BankManagerAddCustomerForm(driver)
                .clickAddCustomerButton()
                .fillFirstNameField(firstName)
                .fillLastNameField(lastName)
                .fillPostCodeField(postCode)
                .clickSubmitAddCustomerButton()
                .verifyCustomerAddedAlertPresent();
    }

    /**
     * Test-case 2 realisation: customers list sorting.
     */
    @Test
    @DisplayName("Test-case 2: customers list sorting")
    @Owner("Max Kulygin")
    public void testCustomersSort() {
        new BankManagerCustomersForm(driver)
                .clickCustomersButton()
                .clickFirstNameHeader()
                .verifyFirstNamesSortedDescending()
                .clickFirstNameHeader()
                .verifyFirstNamesSortedAscending();
    }

    /**
     * Test-case 3 realisation: deleting a customer with first name length closest to average.
     */
    @Test
    @DisplayName("Test-case 3: deleting a customer with first name length closest to average")
    @Owner("Max Kulygin")
    public void testCustomerDeletion() {
        BankManagerCustomersForm customersForm = new BankManagerCustomersForm(driver);
        List<String> closestNames = customersForm
                .clickCustomersButton()
                .findCustomersNamesWithFirstNameLengthsClosestToAverage();
        customersForm
                .deleteCustomersByFirstNames(closestNames)
                .verifyCustomersDeletionByFirstNames(closestNames);
    }
}
