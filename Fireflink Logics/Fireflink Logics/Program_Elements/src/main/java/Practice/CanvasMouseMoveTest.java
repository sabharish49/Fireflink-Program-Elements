package Practice;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class CanvasMouseMoveTest {

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
        int startX = 373;
        int startY = 410;
        int iterations = 12;
        int moveX;
        int moveY;
        List<String> list =new ArrayList();
        

        Actions actions = new Actions(driver);
        for (int i = 0; i < iterations; i++) 	{
            actions.moveToLocation(startX,startY).perform();
        String text = driver.findElement(By.xpath("(//canvas[@class='canvasjs-chart-canvas'])[8]/..//div[@class=\"canvasjs-chart-tooltip\"]/div")).getText();
        System.out.println(text);
            list.add(text);
        startX = startX+81;
            startY = startY;
            Thread.sleep(3000);

        }
        Map<String, Map<String, String>> salesMap = new HashMap<>();
        for (String data : list) {
            String[] lines = data.split("\n");
            String[] monthYear = lines[0].split(" ");
            String month = monthYear[0];
            String year = monthYear[1];

            Map<String, String> monthData = new HashMap<>();
            for (int i = 1; i < lines.length; i++) {
                String[] parts = lines[i].split(": ");
                monthData.put(parts[0], parts[1]);
            }
            salesMap.put(month + " " + year, monthData);
        }
        for (Map.Entry<String, Map<String, String>> entry : salesMap.entrySet()) {
            String monthYear = entry.getKey();
            System.out.println("Month and Year: " + monthYear);
            Map<String, String> monthData = entry.getValue();
            for (Map.Entry<String, String> monthEntry : monthData.entrySet()) {
                System.out.println(monthEntry.getKey() + ": " + monthEntry.getValue());
            }
            System.out.println();
        }
       
    }
}
