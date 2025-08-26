package Practice;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class WebElementMethods {

	public static void main(String[] args) throws InterruptedException {
		ChromeDriver driver= new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://demoapp.skillrary.com/");
		WebElement select=driver.findElement(By.xpath("//select[@class='chosen-select']"));
		Select s= new Select(select);
		s.selectByIndex(1);
		Thread.sleep(20000);

	}

}
