package android;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitForElementInvisibility {
    public static void main(String[] args) {
       
      
        WebDriver driver = new ChromeDriver();

        
        driver.get("");

       
        WebElement element = driver.findElement(By.id("your_element_id"));

      
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

       
        wait.until(ExpectedConditions.invisibilityOf(element));

       
        System.out.println("Element is now invisible.");

        
        driver.quit();
    }
}
