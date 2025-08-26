package Practice;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class Registration {

	public static void main(String[] args) {
		ChromeDriver driver= new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://demoqa.com/automation-practice-form");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.findElement(By.xpath("//h5[text()='Student Registration Form']")).isDisplayed();
		driver.findElement(By.id("firstName")).sendKeys("Nayana");
		driver.findElement(By.id("lastName")).sendKeys("H N");
		driver.findElement(By.id("userEmail")).sendKeys("nayanahn2000march@gmail.com");
		driver.findElement(By.xpath("//label[text()='Female']")).click();
		driver.findElement(By.id("userNumber")).sendKeys("9845453357");
		driver.findElement(By.id("dateOfBirthInput")).click();
		WebElement year=driver.findElement(By.xpath("//select[@class='react-datepicker__year-select']"));
		Select s= new Select(year);
		s.selectByVisibleText("2000");
		WebElement month=driver.findElement(By.xpath("//select[@class='react-datepicker__month-select']"));
		Select s1= new Select(month);
		s1.selectByVisibleText("March");
		driver.findElement(By.xpath("//div[text()='25']")).click();
		//driver.findElement(By.xpath("//div[@id='subjectsContainer']")).sendKeys("I'm just practicing selenium");
		driver.findElement(By.xpath("//label[text()='Reading']")).click();
		driver.findElement(By.id("currentAddress")).sendKeys("indicube south end circle");
		//driver.findElement(By.xpath("//button[text()='Submit']")).click();
        driver.close();
        
       
        
	}

}
