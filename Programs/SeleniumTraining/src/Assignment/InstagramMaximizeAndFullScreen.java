package Assignment;

import org.openqa.selenium.chrome.ChromeDriver;

public class InstagramMaximizeAndFullScreen {

	public static void main(String[] args) {
		ChromeDriver driver= new  ChromeDriver();
		
		driver.get("https://www.instagram.com/");
		driver.manage().window().maximize();
		driver.manage().window().fullscreen();
		driver.close();


	}

}
