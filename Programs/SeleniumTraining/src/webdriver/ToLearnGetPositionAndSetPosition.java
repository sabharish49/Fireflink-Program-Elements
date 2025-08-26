package webdriver;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.chrome.ChromeDriver;

public class ToLearnGetPositionAndSetPosition {

	public static void main(String[] args) {
		ChromeDriver driver= new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.skillrary.com/");
		Point position=driver.manage().window().getPosition();
		System.out.println(position.getX());
		System.out.println(position.getY());
		
		System.out.println("-------------------------------------");
		
		driver.manage().window().setPosition(new Point(100,400));
		Point newPosition=driver.manage().window().getPosition();
		System.out.println(newPosition.getX());
		System.out.println(newPosition.getY());
		driver.quit();

	}

}
