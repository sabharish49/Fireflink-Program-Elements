package Practice;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;

public class CloseDefaultBrowser {
    public static void main(String[] args) throws InterruptedException {

    	List<String> filteredLogs = new ArrayList<>();
		 String json="";
		 String Bearer="";
		 
			WebDriver driver = new ChromeDriver();
			driver.get("https://staging.gatekeeper.scm.gomercury.in/app/requests/all");
			Thread.sleep(5000);
			driver.findElement(By.xpath("//input[@id='username']")).sendKeys("naina.fireflink");
			driver.findElement(By.xpath("//input[@name=\"password\"]")).sendKeys("fireflink");
			driver.findElement(By.xpath("//input[@value=\"Sign In\"]")).click();
			 Thread.sleep(5000);
		try {		
			LogEntries logEntries = driver.manage().logs().get(LogType.PERFORMANCE);
			for (LogEntry entry : logEntries) {
				String message = entry.getMessage();
		if (message.contains("\"Authorization\":\"bearer ")) {
					filteredLogs.add(message);
					System.out.println(filteredLogs+"logs fetched");
				}
			}		
			 Bearer=filteredLogs.get(0).toString();
		        Pattern pattern = Pattern.compile("bearer\\s(.*?)\"");
		        Matcher matcher = pattern.matcher(Bearer);
		        if (matcher.find()) {
		            String bearerToken = matcher.group(1);
		            System.out.println(bearerToken);
		        } else {
		            System.out.println("Bearer token not found.");
		        }
		        System.out.println(Bearer);
		}
		
		catch (Exception e) {
		}
    }
}