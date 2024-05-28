package Practice;

import java.time.Duration;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.support.ui.*;

public class pop_up {

    public static void main(String[] args) {


        WebDriver driver = new ChromeDriver();
        driver.get("https://www.example.com");
        WebElement popupTrigger = driver.findElement(By.id("popup-trigger"));
        popupTrigger.click();
        boolean isAlertDisplayed = isAlertPresent(driver);
        if (isAlertDisplayed) {
            System.out.println("Alert is displayed.");

        } else {
            System.out.println("Alert is not displayed.");
        }
    }
    
    public static boolean isAlertPresent(WebDriver driver) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.alertIsPresent());
            return true;
        } catch (TimeoutException e) {
            return false;
        }
}
}
