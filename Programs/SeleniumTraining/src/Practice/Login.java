package Practice;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Login {

	public static void main(String[] args) throws InterruptedException {
		WebDriver driver= new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://shoppersstack.com/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.findElement(By.xpath("//h3[contains(text(),'Welcome to ShoppersStack')]")).isDisplayed();
		driver.findElement(By.xpath("//button[text()='Login']")).click();
		driver.findElement(By.xpath("//input[@id='Email']")).sendKeys("nayanahn2000march@gmail.com");
		driver.findElement(By.xpath("//input[@id='Password']")).sendKeys("Nayana@1025");
	    driver.findElement(By.xpath("//span[text()='Login']")).click();
	    driver.findElement(By.linkText("Home")).isDisplayed();
	    driver.findElement(By.id("search")).sendKeys("iphone");
	    driver.findElement(By.xpath("//*[name()='svg' and @id='searchBtn']")).click();
	    driver.findElement(By.linkText("Electronic")).click();
	   
	    driver.findElement(By.xpath(" (//span[contains(text(),'APPLE iPhone')])[1]/following::button[text()='add to cart'][1]")).click();
	    Thread.sleep(2000);
	    
	    driver.findElement(By.name("cart")).click();
	    
	    driver.findElement(By.xpath("//button[ text()='Remove from cart']")).click();
	  
	    driver.quit();
		
	}

}
