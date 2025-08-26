package Assignment;

import org.openqa.selenium.chrome.ChromeDriver;

public class SourceCodeOfDemoWebShop {

	public static void main(String[] args) {
		ChromeDriver driver= new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://demowebshop.tricentis.com/");
		String pageSource=driver.getPageSource();
		System.out.println(pageSource);
        driver.close();
	}

}
