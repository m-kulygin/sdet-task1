package utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Utility class, that provides selenium wait operations on web elements.
 *
 * @author Max Kulygin
 */
public final class WaitHelper {
    static WebDriverWait wait;

    /**
     * Set WebDriverWait.
     *
     * @param wait Wait instance
     */
    public static void setWait(WebDriverWait wait) {
        WaitHelper.wait = wait;
    }

    /**
     * Wait for Web Element to be clickable.
     *
     * @param element Web element object
     */
    public static void untilToBeClickable(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    /**
     * Wait for Web Elements to be present (by locator).
     *
     * @param locator Locator to locate elements
     */
    public static void untilToBePresent(By locator) {
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
    }
}
