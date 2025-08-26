package android;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginExample {
    public static void main(String[] args) {
        // Set the path to your ChromeDriver executable
       // System.setProperty("webdriver.chrome.driver", "/path/to/chromedriver");

        // Initialize ChromeDriver
        WebDriver driver = new ChromeDriver();

        // Navigate to the login page
        driver.get("https://staging.gatekeeper.scm.gomercury.in/app/requests/all");

        // Find username and password fields and login button
        WebElement usernameField = driver.findElement(By.id("username"));
        WebElement passwordField = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.name("login"));

        // Enter credentials
        usernameField.sendKeys("naina.fireflink");
        passwordField.sendKeys("fireflink");

        // Click the login button
        loginButton.click();

        // Once logged in, you can get the status code of the current page
        int statusCode = driver.manage().logs().get("performance").getAll().stream()
                .filter(logEntry -> logEntry.getMessage().contains("Network.responseReceived"))
                .findFirst().map(entry -> Integer.parseInt(entry.getMessage().split("\"status\":")[1].split(",")[0].trim())).orElse(-1);

        System.out.println("Status code: " + statusCode);

        // Close the browser
        
        driver.quit();
    }
}
