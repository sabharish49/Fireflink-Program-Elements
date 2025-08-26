package default_package;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

import java.util.Set;

public class SwitchTabXuriti {

    public static void main(String[] args) throws InterruptedException {
        
        // Set the path to the WebDriver (ensure ChromeDriver is in your PATH or specify the path)
        // System.setProperty("webdriver.chrome.driver", "/path/to/chromedriver"); // Update this path if needed

        // Initialize the ChromeDriver
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        // Step 1: Navigate to the first URL in the initial tab
        String url1 = "https://xurititest.spacempact.cloud/adminpanel/#/";
        driver.get(url1);

        // Optional: Wait to observe the first URL
        try {
            Thread.sleep(3000); // 3 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Step 2: Open a new tab (using JavaScript)
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.open();");

        // Step 3: Get all window handles (tabs)
        Set<String> handles = driver.getWindowHandles();
        String firstTabHandle = driver.getWindowHandle();  // Store the handle of the first tab

        // Log in on the first tab (Assuming you're on the login page of the first tab)
        driver.findElement(By.xpath("//input[@type='email']")).sendKeys("admin@xuriti.com");
        driver.findElement(By.xpath("//input[@type='password']")).sendKeys("Test$123");
        driver.findElement(By.xpath("//span[contains(.,'LOGIN')]")).click();

        // Switch to the new tab (second tab)
        String secondTabHandle = "";
        for (String handle : handles) {
            if (!handle.equals(firstTabHandle)) {
                secondTabHandle = handle;  // Capture the second tab's handle
                driver.switchTo().window(handle);  // Switch to the second tab
                break;
            }
        }

        // Step 4: Navigate to the second URL in the new tab
        String url2 = "https://xurititest.spacempact.cloud/";
        driver.get(url2);

        // Optional: Wait to observe the second URL
        try {
            Thread.sleep(3000); // 3 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Step 5: Switch back to the first tab
       
        driver.findElement(By.xpath("//input[@type='email']")).sendKeys("fireflinktest123@gmail.com");
        driver.findElement(By.xpath("//input[@type='password']")).sendKeys("Xuriti@2024");
        driver.findElement(By.xpath("//span[contains(.,'LOGIN')]")).click();// Switch back to the first tab
        Thread.sleep(50000);
        //driver.findElement(By.xpath("//mat-icon[.='keyboard_arrow_right']")).click();
//        driver.findElement(By.xpath("//input[@data-placeholder='Enter GST Number']")).sendKeys("07ADDPA2443B1ZF");
//        driver.findElement(By.xpath("//span[contains(.,'GET GST DETAILS')]")).click();
//        driver.findElement(By.xpath("//span[contains(.,'CREATE')]")).click();
        driver.switchTo().window(firstTabHandle); 
        // Optional: Wait to observe the first tab
        try {
            Thread.sleep(3000); // 3 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        
       
    }
}
