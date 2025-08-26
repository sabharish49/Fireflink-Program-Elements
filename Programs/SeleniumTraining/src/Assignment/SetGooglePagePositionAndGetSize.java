package Assignment;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.chrome.ChromeDriver;

public class SetGooglePagePositionAndGetSize {

	public static void main(String[] args) throws InterruptedException {
		ChromeDriver driver= new  ChromeDriver();
	    driver.manage().window().maximize();
		driver.get("https://www.google.com/");
		driver.manage().window().setPosition(new Point(500,300));
		Thread.sleep(2000);
	    Dimension size=	driver.manage().window().getSize();
	    System.out.println(size.getHeight());
	    System.out.println(size.getWidth());
		driver.close();


	}

}
