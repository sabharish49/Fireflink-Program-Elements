package Practice;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class EnterInputIfEmpty {

    public static void main(String[] args) throws InterruptedException {
    	ChromeDriver driver = new ChromeDriver();
    	driver.get("https://admin.stage.gfrsd.aws.direct.randstad.ca/");
    	driver.manage().window().maximize();
    	Thread.sleep(60000);
        WebElement postalCodeInput = driver.findElement(By.id("postalCode"));
        //postalCodeInput.sendKeys("20026");
        String currentValue = postalCodeInput.getAttribute("value");
        System.out.println(currentValue);
        if (currentValue.isEmpty()) {
            postalCodeInput.sendKeys("123456");
            System.out.println("Input entered into the postalCode field.");
        } else {
            System.out.println("The postalCode field already has a value: " + currentValue);
        }
    }
}

