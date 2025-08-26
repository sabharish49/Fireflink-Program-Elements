package extentReports;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.aventstack.extentreports.Status;
import com.tricenties.listenersUtility.ListenerImplementation;
import baseTest.BaseClass;
@Listeners(ListenerImplementation.class)
public class LoginToDemoWebShop extends BaseClass{
	@Test
	public void loginTest() {
		
		driver.findElement(By.xpath("//a[text()='Log in']")).click();
		Assert.assertEquals(driver.getCurrentUrl(), "https://demowebshop.tricentis.com/login");
		test.log(Status.PASS, "login page is displayed");
		driver.findElement(By.xpath("//input[@id='Email']")).sendKeys("nayanahn2000march@gmail.com");
		driver.findElement(By.xpath("//input[@id='Password']")).sendKeys("1234");
		driver.findElement(By.xpath("//input[@value='Log in']")).click();
		Assert.assertEquals(driver.getTitle(), "Demo Web Shop");
		test.log(Status.PASS, " user logged in successfully");
		
		
	}

}
