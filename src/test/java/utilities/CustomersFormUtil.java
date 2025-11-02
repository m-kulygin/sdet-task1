package utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Utility class, that provides data-parsing operations on customers table.
 *
 * @author Max Kulygin
 */
public class CustomersFormUtil {

    /**
     * Extracts list of customers' first names from table rows.
     *
     * @param customerRows Customer rows from table
     * @return First names' list value
     */
    public static List<String> extractFirstNamesFromTable(List<WebElement> customerRows) {
        return customerRows.stream()
                .map(CustomersFormUtil::getRowFirstName)
                .collect(Collectors.toList());
    }

    /**
     * Calculates average first name length
     *
     * @param names First names list
     * @return Average first name length
     */
    public static double calculateAverageNameLength(List<String> names) {
        return names.stream()
                .mapToInt(String::length)
                .average()
                .orElseThrow(() -> new AssertionError(MessageConstants.MSG_EMPTY_CUSTOMERS));
    }

    /**
     * Finds all names, having length closest to average
     *
     * @param names     First names list
     * @param avgLength Average length value
     * @return Result names list
     */
    public static List<String> findClosestNames(List<String> names, double avgLength) {
        if (names.isEmpty()) {
            return null;
        }
        double minLength = names.stream()
                .min(Comparator.comparingDouble(name -> Math.abs(name.length() - avgLength)))
                .map(String::length)
                .get();
        return names.stream()
                .filter(name -> name.length() == minLength)
                .toList();
    }

    /**
     * Retrieves customers acc number by first name in customers table.
     *
     * @param userName     First name of a customer to obtain his accnumber
     * @param customerRows Customer rows from table
     * @return Account numbers
     */
    public static List<String> retrieveAccountNumbersForUser(String userName, List<WebElement> customerRows) {
        for (WebElement row : customerRows) {
            if (getRowFirstName(row).equals(userName)) {
                return getRowAccountNumbers(row);
            }
        }
        return new ArrayList<>();
    }

    /**
     * Get account number from row
     *
     * @param row Customer row from table
     * @return Account number for the row
     */
    public static List<String> getRowAccountNumbers(WebElement row) {
        return row
                .findElements(By.tagName("td"))
                .get(3)
                .findElements(By.tagName("span"))
                .stream()
                .map(WebElement::getText)
                .toList();
    }

    /**
     * Get first name from row
     *
     * @param row Customer row from table
     * @return First name for the row
     */
    public static String getRowFirstName(WebElement row) {
        return row
                .findElements(By.tagName("td"))
                .get(0)
                .getText();
    }

    /**
     * Get first name strings from web elements list
     *
     * @param firstNamesList Web element list containing first names
     * @return List of first names strings
     */
    public static List<String> getFirstNamesFromWebElements(List<WebElement> firstNamesList) {
        return firstNamesList.stream()
                .map(WebElement::getText)
                .toList();
    }
}
