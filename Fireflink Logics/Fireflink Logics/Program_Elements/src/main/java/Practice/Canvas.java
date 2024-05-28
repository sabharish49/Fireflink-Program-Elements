package Practice;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class Canvas {

    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://canvasjs.com/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        Thread.sleep(10000);
        System.out.println("started");
        WebElement canvas = driver.findElement(By.xpath("(//canvas[@class='canvasjs-chart-canvas'])[8]"));
        int canvasHeight = canvas.getSize().getHeight();
        int canvasWidth = canvas.getSize().getWidth();
        System.out.println("Canvas Height: " + canvasHeight);
        System.out.println("Canvas Width: " + canvasWidth);
        int startX = canvasWidth / 2; 
        int startY = canvasHeight; // Bottom of the canvas
        System.out.println("Starting Coordinate (X,Y): (" + startX + "," + startY + ")");
        Actions actions = new Actions(driver);
        int endX = canvasWidth - 20; // Adjust as needed
        actions.moveToElement(canvas, startX, startY)
               .moveByOffset(endX - startX, 0)
               .perform();
        driver.quit();
    }
}
