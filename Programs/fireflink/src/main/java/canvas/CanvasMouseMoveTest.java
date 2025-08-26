package canvas;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class CanvasMouseMoveTest {

	public static void main(String[] args) throws InterruptedException {
		WebDriver driver= new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://canvasjs.com/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		Thread.sleep(10000);
		System.out.println("started");
		WebElement canvas = driver.findElement(By.xpath("(//canvas[@class='canvasjs-chart-canvas'])[8]"));
		WebElement data = driver.findElement(By.xpath("(//canvas[@class='canvasjs-chart-canvas'])[8]/..//div[@class='canvasjs-chart-tooltip']//div"));
		int height = canvas.getSize().getHeight();
        int width = canvas.getSize().getWidth();
        System.out.println(height);
        System.err.println(width);
        Actions act=new Actions(driver);
        act.moveByOffset(460, 430).perform();
		System.out.println("text data"+data.getText());
	
	}

}
