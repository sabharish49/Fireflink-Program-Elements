package screenshot;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;

public class ScreenshotOfWebElement {

	public static void main(String[] args) throws IOException {
		WebDriver driver= new ChromeDriver();
		driver.manage().window().maximize();
		String time=LocalDateTime.now().toString().replace(":", "-") ;
//		LocalDateTime sysTime=LocalDateTime.now();
//		String time = sysTime.toString().replace(":","-");
		driver.get("https:www.instagram.com/");
		
//		TakesScreenshot ts= (TakesScreenshot) driver;
//		File src=ts.getScreenshotAs(OutputType.FILE);
		File src=driver.findElement(By.id("")).getScreenshotAs(OutputType.FILE);
		File dest=new File("./Screenshots/"+time+".png");
		FileHandler.copy(src, dest);
		driver.quit();

	}

}
