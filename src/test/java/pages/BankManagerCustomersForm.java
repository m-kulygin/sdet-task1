package pages;

import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.WaitHelper;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.Assert.assertFalse;

public class BankManagerCustomersForm extends BankManagerPage {

    @FindBy(css = "button[ng-class='btnClass3']")
    protected WebElement customersButton;

    @FindBy(xpath = "//a[contains(text(), 'First Name')]")
    private WebElement firstNameHeader;

    @FindBy(xpath = "//tbody/tr/td[1]")
    private List<WebElement> firstNamesList;

    @FindBy(xpath = "//table[@class='table table-bordered table-striped']//tbody/tr")
    private List<WebElement> customerRows;

    public BankManagerCustomersForm(WebDriver driver, WaitHelper waiter) {
        super(driver, waiter);
        PageFactory.initElements(driver, this);
    }

    @Step("Нажатие на заголовок First Name в таблице для сортировки")
    public BankManagerCustomersForm clickFirstNameHeader() throws InterruptedException {
        waiter.untilToBeClickable(firstNameHeader);
        firstNameHeader.click();
        return this;
    }

    @Step("Проверка отсортированности списка клиентов по first name")
    public boolean verifyFirstNamesSorted() throws InterruptedException {
        List<String> originalNames = firstNamesList.stream()
                .map(WebElement::getText)
                .toList();

        List<String> ascSortedCopy = new ArrayList<>(originalNames);
        Collections.sort(ascSortedCopy);

        List<String> descSortedCopy = new ArrayList<>(originalNames);
        descSortedCopy.sort(Collections.reverseOrder());

        boolean isSortedAsc = originalNames.equals(ascSortedCopy);
        boolean isSortedDesc = originalNames.equals(descSortedCopy);

        Assert.assertTrue(isSortedAsc || isSortedDesc);

        return true;
    }

    @Step("Удаление их списка клиента, имя которого по длине наиболее близка к средней длине всех имён")
    public BankManagerCustomersForm deleteCustomerBasedOnAverageFirstNameLength() throws InterruptedException {
        waiter.untilToBePresent(By.xpath("//table[@class='table table-bordered table-striped']"));

        List<String> firstNames = extractFirstNamesFromTable();
        double avgLength = calculateAverageNameLength(firstNames);

        Optional<String> closestNameOpt = findClosestName(firstNames, avgLength);
        if (closestNameOpt.isEmpty()) {
            throw new RuntimeException("No suitable name found");
        }
        String closestName = closestNameOpt.get();

        List<String> accountNumbers = retrieveAccountNumbersForUser(closestName);

        deleteCustomerWithName(closestName);

        verifyDeletion(accountNumbers);

        return this;
    }

    private List<String> extractFirstNamesFromTable() throws InterruptedException {
        return customerRows.stream()
                .map(row -> row.findElements(By.tagName("td")).get(0).getText())
                .collect(Collectors.toList());
    }

    private double calculateAverageNameLength(List<String> names) {
        return names.stream()
                .mapToInt(String::length)
                .average().orElseThrow(() -> new IllegalStateException("No data"));
    }

    private Optional<String> findClosestName(List<String> names, double avgLength) {
        return names.stream()
                .min(Comparator.comparingDouble(name -> Math.abs(name.length() - avgLength)));
    }

    private List<String> retrieveAccountNumbersForUser(String userName) {
        for (WebElement row : customerRows) {
            if (row.findElements(By.tagName("td"))
                    .get(0)
                    .getText()
                    .equals(userName)) {
                return row.findElements(By.tagName("td"))
                        .get(3)
                        .findElements(By.tagName("span"))
                        .stream()
                        .map(WebElement::getText)
                        .collect(Collectors.toList());
            }
        }
        return new ArrayList<>();
    }

    private void deleteCustomerWithName(String name) {
        customerRows.stream()
                .filter(row -> row.findElements(By.tagName("td")).get(0).getText().equals(name))
                .findAny()
                .ifPresent(targetRow -> targetRow
                        .findElement(By.xpath(".//button[contains(text(), 'Delete')]")).click());
    }

    private void verifyDeletion(List<String> accountNumbers) throws InterruptedException {
        boolean anyAccountFound = customerRows.stream()
                .flatMap(row -> row.findElements(By.tagName("td")).get(3)
                        .findElements(By.tagName("span"))
                        .stream()
                        .map(WebElement::getText))
                .anyMatch(accountNumbers::contains);

        assertFalse(anyAccountFound);
    }

    @Step("Нажатие на кнопку Customers для открытия формы")
    public BankManagerCustomersForm clickCustomersButton() {
        waiter.untilToBeClickable(customersButton);
        customersButton.click();
        return this;
    }
}
