package Assignment;

import org.openqa.selenium.chrome.ChromeDriver;

public class YatraUrlAndTitle {

	public static void main(String[] args) {
		
		ChromeDriver driver= new  ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.yatra.com/");
		String url= driver.getCurrentUrl();
		String title=driver.getTitle();
		System.out.println(url);
		System.out.println(title);
		driver.close();
	
	}

}
