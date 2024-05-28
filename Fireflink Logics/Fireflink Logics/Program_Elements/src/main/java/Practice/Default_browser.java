package Practice;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Default_browser {
    public static void main(String[] args) throws InterruptedException {
        // Set the path to the ChromeDriver executable
        System.setProperty("webdriver.chrome.driver", "C:/Users/User/Downloads/chromedriver-win32/chromedriver-win32/chromedriver.exe/");
        WebDriver driver = new ChromeDriver();
        driver.get("https://accounts.google.com");
        WebElement emailField = driver.findElement(By.name("identifier"));
        emailField.sendKeys("abhisammy098@gmail.com");
        emailField.sendKeys(Keys.RETURN);
        Thread.sleep(10000);
        WebElement passwordField = driver.findElement(By.name("password"));
        passwordField.sendKeys("Abhisam@098");
        passwordField.sendKeys(Keys.RETURN);
        driver.get("https://example.com/specific-page");
        driver.quit();       
    }
}
