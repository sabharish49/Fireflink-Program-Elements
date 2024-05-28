package Practice;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Stock_Buy_Sell {

    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
        WebDriver driver = new ChromeDriver();
        String stockUrl = "your_stock_url";
        driver.get(stockUrl);
        WebElement colorElement = driver.findElement(By.cssSelector("your_css_selector_for_color_element"));
        String color = colorElement.getCssValue("color");
        if (isColorGreen(color)) {
            executeBuyOrder();
        } else if (isColorRed(color)) {
            executeSellOrder();
        }
        driver.quit();
    }
    
    private static boolean isColorGreen(String color) {
        return color.equalsIgnoreCase("#00FF00");
    }
    
    private static boolean isColorRed(String color) {
        return color.equalsIgnoreCase("#FF0000");
    }
    
    private static void executeBuyOrder() {
        System.out.println("Executing Buy Order");
    }
    private static void executeSellOrder() {
        System.out.println("Executing Sell Order");
    }
}
