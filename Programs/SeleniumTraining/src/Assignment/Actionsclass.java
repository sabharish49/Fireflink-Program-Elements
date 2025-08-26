package Assignment;

import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

public class Actionsclass {
	public static void main(String[] args) {
		ChromeOptions option = new ChromeOptions();
		option.addArguments("--disable-notifications");
		ChromeDriver driver= new  ChromeDriver(option);
		
	    driver.manage().window().maximize();
		driver.get("https://www.redbus.com/");
		Actions action = new Actions(driver);
		action.sendKeys(Keys.TAB).click().perform();
		action.sendKeys(Keys.ENTER).click().perform();
		
	}

}
