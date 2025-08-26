package datadriven;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ReadDataFromPropertyFile {

	public static void main(String[] args) throws IOException {
		FileInputStream fis=new FileInputStream("./testdata/property.properties");
		Properties property=new Properties();
		property.load(fis);
		String url=property.getProperty("url");
		
		
		
		WebDriver driver= new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(url);
		

	}

}
