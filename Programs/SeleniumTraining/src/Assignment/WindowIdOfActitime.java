package Assignment;

import org.openqa.selenium.chrome.ChromeDriver;

public class WindowIdOfActitime {

	public static void main(String[] args) {
		ChromeDriver driver= new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.actitime.com/");
		String windowId=driver.getWindowHandle();
		System.out.println(windowId);
        driver.close();
	}

}
