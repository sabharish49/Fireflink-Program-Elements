package canvas;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class canvas {
    public static void main(String[] args) throws InterruptedException {

        // Initialize WebDriver
        WebDriver driver = new ChromeDriver();

        // Open a webpage containing the canvas element
        driver.get("https://canvasjs.com/");
        driver.manage().window().maximize();
        Thread.sleep(5000);

        // Find the canvas element
        WebElement canvas = driver.findElement(By.xpath("(//canvas[@class='canvasjs-chart-canvas'])[8]"));

        // Get the height and width of the canvas element
        int canvasHeight = canvas.getSize().getHeight();
        int canvasWidth = canvas.getSize().getWidth();

        // Create Actions object
        Actions actions = new Actions(driver);

        // Move the mouse to a specific position inside the canvas
        int xPosition = canvasWidth / 2; // Example: move to the center horizontally
        int yPosition = canvasHeight / 2; // Example: move to the center vertically
        actions.moveToElement(canvas, xPosition, yPosition).perform();

        // Perform a click action on the canvas at the specified position
        actions.click().perform();

        // Close the browse
    }
}
