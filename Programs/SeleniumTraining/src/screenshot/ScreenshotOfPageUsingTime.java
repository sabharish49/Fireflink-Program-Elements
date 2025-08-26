package screenshot;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;

public class ScreenshotOfPageUsingTime {

	public static void main(String[] args) throws IOException {
		WebDriver driver= new ChromeDriver();
		driver.manage().window().maximize();
		LocalDateTime sysTime=LocalDateTime.now();
		String time = sysTime.toString().replace(":","-");
		driver.get("https://demoapp.skillrary.com/");
		TakesScreenshot ts= (TakesScreenshot) driver;
		File src=ts.getScreenshotAs(OutputType.FILE);
		File dest=new File("./Screenshots/"+time+".png");
		FileHandler.copy(src, dest);
		driver.quit();

	}

}
