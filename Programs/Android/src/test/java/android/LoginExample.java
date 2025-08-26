package android;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginExample {
    public static void main(String[] args) throws InterruptedException {
       
        WebDriver driver = new ChromeDriver();
        driver.get("https://staging.gatekeeper.scm.gomercury.in/app/requests/all");
		driver.manage().window().maximize();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@id='username']")).sendKeys("naina.fireflink");
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys("fireflink");
		driver.findElement(By.xpath("//input[@value=\"Sign In\"]")).click();
		Thread.sleep(2000);

       
        int statusCode = driver.manage().logs().get("performance").getAll().stream()
                .filter(logEntry -> logEntry.getMessage().contains("Network.responseReceived"))
                .findFirst().map(entry -> Integer.parseInt(entry.getMessage().split("\"status\":")[1].split(",")[0].trim())).orElse(-1);

        System.out.println("Status code: " + statusCode);

       
        driver.quit();
    }
}
