import io.qameta.allure.Owner;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.UUID;

@DisplayName("Кейсы тестирования формы XYZBank вкладки менеджера")
public class BankManagerTest {
    private WebDriver driver;
    private BankManagerAddCustomerForm addCustomerForm;
    private BankManagerCustomersForm customersForm;
    private BankManagerCustomersForm customersForm2; // ЗАМЕНИТЬ / ПОПРАВИТЬ

    @Before
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
//        options.setPageLoadStrategy(PageLoadStrategy.EAGER);
//        options.addArguments("--window-size=1920,1080");
//        options.addArguments("--start-maximized");
//        options.addArguments("--headless");
//        String tempDir = System.getProperty("java.io.tmpdir") + UUID.randomUUID() + "/";
//        options.addArguments("--user-data-dir=" + tempDir);
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--headless");
        options.addArguments("--user-data-dir=/tmp/chrome-profile-" + UUID.randomUUID());
        WebDriver driver = new ChromeDriver(options);
        driver.get("https://www.globalsqa.com/angularJs-protractor/BankingProject/#/manager");
        this.driver = driver;
        addCustomerForm = new BankManagerAddCustomerForm(driver);
        customersForm = new BankManagerCustomersForm(driver);
        customersForm2 = new BankManagerCustomersForm(driver);
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    @DisplayName("Кейс 1: добавление клиента")
    @Owner("Max Kulygin")
    public void testAddCustomer() {
        try {
            String postCode = DataGenerator.generatePostCode();
            String firstName = DataGenerator.generateFirstNameByPostCode(postCode);
            String lastName = DataGenerator.generateLastName();

        addCustomerForm
                .clickAddCustomerButton()
                .fillFirstNameField(firstName)
                .fillLastNameField(lastName)
                .fillPostCodeField(postCode)
                .clickSubmitAddCustomerButton()
                .verifyCustomerAddedAlertPresent();
            Thread.sleep(2000);
        }
        catch(InterruptedException ignored){
        }
    }

    @Test
    @DisplayName("Кейс 2: сортировка списка клиентов")
    @Owner("Max Kulygin")
    public void testCustomersSort() {
        try {
            customersForm
                    .clickCustomersButton()
                    .clickFirstNameHeader()
                    .verifyFirstNamesSorted();
            Thread.sleep(2000);
        } catch (InterruptedException ignored){}
    }

    @Test
    @DisplayName("Кейс 3: удаление клиента с именем, наиболее близким по длине к средней")
    @Owner("Max Kulygin")
    public void testCustomerDeletion() {
        try {
            customersForm2
                    .clickCustomersButton()
                    .deleteCustomerBasedOnAverageFirstNameLength();
            Thread.sleep(2000);
        } catch (InterruptedException ignored) {
        }
    }

    /* TODO:
    * 1) улучшить селекторы
    * 2) вынести селекторы из методов в поля класса?
    * 3) убрать лишние слипы + throws, там где нужно заменить на правильные вейты
    * 4) отрефакторить третий кейс (упростить, прикрутить флюент)
    * 5) разобраться с наследованием форм (как лучше ?)
    * 6) учесть недочёты по фидбеку ТЗ
    * 7) учесть рекомендации по мастер-классу
    * 8) у генератора вынести что нужно в аргументы методов / константы класса
    * */

}
