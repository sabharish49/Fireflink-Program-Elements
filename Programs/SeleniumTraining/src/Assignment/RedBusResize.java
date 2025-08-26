package Assignment;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.chrome.ChromeDriver;

public class RedBusResize {

	public static void main(String[] args) throws InterruptedException {
    ChromeDriver driver= new  ChromeDriver();
    driver.manage().window().maximize();
	driver.get("https://www.redbus.com/");
	driver.manage().window().setSize(new Dimension(500,300));
	Thread.sleep(2000);
	driver.close();

	}

}
