package Practice;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;

public class Fetch_network_logs {
	public static void main(String[] args) throws InterruptedException {
		
		ChromeOptions options = new ChromeOptions();
		LoggingPreferences logPrefs = new LoggingPreferences();
        logPrefs.enable(LogType.PERFORMANCE, Level.ALL);
        options.setCapability("goog:loggingPrefs", logPrefs);
		WebDriver driver=new ChromeDriver(options);		
		List<String> filteredLogs = new ArrayList<>();
//		driver.get("https://staging.gatekeeper.scm.gomercury.in/app/requests/all");
//		driver.manage().window().maximize();
//		Thread.sleep(2000);
//		driver.findElement(By.xpath("//input[@id='username']")).sendKeys("naina.fireflink");
//		driver.findElement(By.xpath("//input[@name='password']")).sendKeys("fireflink");
//		driver.findElement(By.xpath("//input[@value=\"Sign In\"]")).click();
		
		driver.manage().window().maximize();
		driver.get("https://demo.fundexpert.net/");
     	Thread.sleep(2000);
		
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		
		driver.findElement(By.xpath("(//input[@name=\"email\"])[2]")).sendKeys("1805");
		driver.findElement(By.xpath("(//input[@name=\"password\"])[1]")).sendKeys("demo@12345");
		driver.findElement(By.xpath("//input[@value=\"Login\"]")).click();
		
		
		
		
		Thread.sleep(2000);		
		LogEntries logEntries = driver.manage().logs().get(LogType.PERFORMANCE);
		for (LogEntry entry : logEntries) {
			String message = entry.getMessage();
			
			System.out.println(message);
//	if (message.contains("\"Authorization\":\"bearer ")) {
//				filteredLogs.add(message);
//			}
//		}
//		
//		Pattern pattern = Pattern.compile("bearer\\s(.*?)\"");
//        Matcher matcher = pattern.matcher(filteredLogs.get(0));
//        if (matcher.find()) {
//            String bearerToken = matcher.group(1);
//            System.out.println(bearerToken);
//        }
//        else {
//            System.out.println("Bearer token not found.");
      }		
	}

}
