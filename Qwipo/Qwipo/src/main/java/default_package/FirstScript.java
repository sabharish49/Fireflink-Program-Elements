package default_package;

import org.openqa.selenium.WebDriver;

public class FirstScript {
    public void execute() {
        WebDriver driver = WebDriverSingleton.getDriver();
        driver.get("https://example.com");
        System.out.println("First script executed.");
    }
}
