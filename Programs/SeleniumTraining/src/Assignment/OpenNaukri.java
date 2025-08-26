package Assignment;

import org.openqa.selenium.firefox.FirefoxDriver;

public class OpenNaukri {

	public static void main(String[] args) {
		FirefoxDriver driver= new  FirefoxDriver();
		driver.manage().window().maximize();
		driver.get("https://www.naukri.com/");
		driver.close();

	}

}
