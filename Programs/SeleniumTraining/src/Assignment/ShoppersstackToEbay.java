package Assignment;

import org.openqa.selenium.chrome.ChromeDriver;

public class ShoppersstackToEbay {

	public static void main(String[] args) {
		try {
		ChromeDriver driver= new ChromeDriver();
		
		driver.get("https://shoppersstack.com/");
		driver.navigate().to("https://www.ebay.com/");
		driver.manage().window().maximize();
        driver.close();
		}
		catch (Exception e) {
			
		}
	}

}
