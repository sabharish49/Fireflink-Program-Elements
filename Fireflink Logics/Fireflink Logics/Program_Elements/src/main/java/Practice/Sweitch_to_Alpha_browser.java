package Practice;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Sweitch_to_Alpha_browser {
    public static void main(String[] args) throws InterruptedException {
System.setProperty("serenity.jquery.integration", "false");
        WebDriver driver = new ChromeDriver();

        driver.get("https://alphapreprod.apacfin.in:8087/");
        driver.manage().window().maximize();
        Thread.sleep(30000); 
        String alphaWindowHandle = driver.getWindowHandle();
        Set<String> windowHandles = driver.getWindowHandles();
        System.out.println(windowHandles);
        for (String windowHandle : windowHandles) {
            if (!windowHandle.equals(alphaWindowHandle)) {
                driver.switchTo().window(windowHandle);
                break;
            }     

        }
        Thread.sleep(10000);
        WebElement inputElement = driver.findElement(By.xpath("//input[@id='nomineeDob']"));
//        
        WebElement element = driver.findElement(By.id("your_element_id"));
        ((JavascriptExecutor) driver).executeScript(
            "arguments[0].setAttribute('required', 'false');", inputElement);
        
//       WebElement inputField = driver.findElement(By.className("form-control"));
//       inputField.click();
//       Actions actions = new Actions(driver);
//       actions.moveToElement(inputElement, 5, inputElement.getSize().getHeight() / 2).click().build().perform();
//       String msg="13-12-2000";  
//       
//       ((JavascriptExecutor) driver).executeScript(
//               "document.getElementById('nomineeDob').value = arguments[0];",
//               msg
//           );

    }
}
