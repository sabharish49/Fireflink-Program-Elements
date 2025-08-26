package default_package;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebDriverSingleton {
    private static WebDriver driver;

    // Get the single WebDriver instance
    public static WebDriver getDriver() {
        if (driver == null) {
          
            driver = new ChromeDriver();
        }
        return driver;
    }

    // Quit the WebDriver
    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
