package practice;

import org.openqa.selenium.WebDriver;

public class SecondScript {
    public void execute() {
        WebDriver driver = WebDriverSingleton.getDriver();
        driver.get("https://app.fireflink.com/");
        System.out.println("Second script executed.");
    }
}
