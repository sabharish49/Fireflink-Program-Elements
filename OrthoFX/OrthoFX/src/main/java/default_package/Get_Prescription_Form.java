package default_package;

import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Get_Prescription_Form {

	public static void main(String[] args) throws InterruptedException {
		
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://p1pro.orthofx.com/professional/doctor/3dworkspace-ofx/17813/12736/7939/1");
		String parentWindow = driver.getWindowHandle();
        Thread.sleep(70000);
        System.out.println("started");
        
        Set<String> allWindows = driver.getWindowHandles();
        for (String windowHandle : allWindows) {
            if (!windowHandle.equals(parentWindow)) {
            
        driver.switchTo().window(windowHandle);
        System.out.println("switched");
            }
        Thread.sleep(40000);
        System.out.println("started");
        System.out.println( driver.findElement(By.xpath("//div[@class='wrapper']")).isDisplayed());
      
        List<WebElement> elements = driver.findElements(By.xpath("//div//span[contains(@class,'ofx-medium-font')]"));
        for (WebElement element : elements) {
            String text = element.getText();
            System.out.println("Element text: " + text);
        }
       // System.out.println(driver.findElement(By.xpath("//div//span[contains(@class,'ofx-medium-font')]")).getText());
        
	}

}
	}

