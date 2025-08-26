package ActionClass;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class RightClickAndClickAndHold {

	public static void main(String[] args) {
		WebDriver driver= new ChromeDriver();
		driver.manage().window().maximize();
		
		driver.get("https://yonobusiness.sbi/login/yonobusinesslogin");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		Actions action = new Actions(driver);
		action.contextClick().perform();
	    //driver.findElement(By.idv("password")).sendKeys("test");
	    WebElement show = driver.findElement(By.xpath("//div[@class='disableddiv showPassword shownhide']"));
	    action.clickAndHold(show).perform();
	    
	    
	    
	    action.moveByOffset(1212, 303).clickAndHold().perform();
	    
		driver.quit();

	}

}
