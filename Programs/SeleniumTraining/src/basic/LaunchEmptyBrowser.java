package basic;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class LaunchEmptyBrowser {

	public static void main(String[] args) {
		ChromeDriver driver= new ChromeDriver();
		FirefoxDriver driver1=new FirefoxDriver();
        EdgeDriver driver2= new EdgeDriver();
	}

}
