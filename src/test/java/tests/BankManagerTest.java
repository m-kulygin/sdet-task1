package tests;

import io.qameta.allure.Owner;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import pages.BankManagerAddCustomerForm;
import pages.BankManagerCustomersForm;
import utilities.DataGenerator;

@DisplayName("Кейсы тестирования формы XYZBank вкладки менеджера")
public class BankManagerTest extends BaseTest {

    @Test
    @DisplayName("Кейс 1: добавление клиента")
    @Owner("Max Kulygin")
    public void testAddCustomer() {
        try {
            String postCode = DataGenerator.generatePostCode();
            String firstName = DataGenerator.generateFirstNameByPostCode(postCode);
            String lastName = DataGenerator.generateLastName();

            new BankManagerAddCustomerForm(driver, waiter)
                    .clickAddCustomerButton()
                    .fillFirstNameField(firstName)
                    .fillLastNameField(lastName)
                    .fillPostCodeField(postCode)
                    .clickSubmitAddCustomerButton()
                    .verifyCustomerAddedAlertPresent();
        } catch (InterruptedException ignored) {
        }
    }

    @Test
    @DisplayName("Кейс 2: сортировка списка клиентов")
    @Owner("Max Kulygin")
    public void testCustomersSort() {
        try {
            new BankManagerCustomersForm(driver, waiter)
                    .clickCustomersButton()
                    .clickFirstNameHeader()
                    .verifyFirstNamesSorted();
        } catch (InterruptedException ignored) {
        }
    }

    @Test
    @DisplayName("Кейс 3: удаление клиента с именем, наиболее близким по длине к средней")
    @Owner("Max Kulygin")
    public void testCustomerDeletion() {
        try {
            new BankManagerCustomersForm(driver, waiter)
                    .clickCustomersButton()
                    .deleteCustomerBasedOnAverageFirstNameLength();
        } catch (InterruptedException ignored) {
        }
    }
}
