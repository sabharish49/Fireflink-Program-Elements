package module1;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
import com.epam.healenium.SelfHealingDriver;

public class Healenium {
	@Test
	public void new1() throws InterruptedException {
		SelfHealingDriver driver ;
		WebDriver delegate=new ChromeDriver();
	    driver= SelfHealingDriver.create(delegate);
	    driver.get("https://www.google.com/");
	    Thread.sleep(2000);
	    WebElement search = delegate.findElement(By.name("q"));
        search.sendKeys("test");
        search.clear();
        WebElement element = delegate.findElement(By.name("q"));
        ((JavascriptExecutor) delegate).executeScript("arguments[0].setAttribute('name', 'write');", element);
        Thread.sleep(2000);
        driver.findElement(By.name("q")).sendKeys("test");
          
	}	           
	}
