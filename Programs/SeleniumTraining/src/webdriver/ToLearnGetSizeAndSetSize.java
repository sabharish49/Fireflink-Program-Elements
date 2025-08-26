package webdriver;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.chrome.ChromeDriver;

public class ToLearnGetSizeAndSetSize {

	public static void main(String[] args) {
		ChromeDriver driver= new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.skillrary.com/");
		Dimension size=driver.manage().window().getSize();
		System.out.println(size.getHeight());
		System.out.println(size.getWidth());
		
		System.out.println("-------------------------------------");
		
		driver.manage().window().setSize(new Dimension(100,400));
		Dimension newSize=driver.manage().window().getSize();
		System.out.println(newSize.getHeight());
		System.out.println(newSize.getWidth());
		driver.quit();
		
		

	}

}
