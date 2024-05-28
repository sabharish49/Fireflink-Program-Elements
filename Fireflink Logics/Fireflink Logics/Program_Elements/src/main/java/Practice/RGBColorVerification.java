package Practice;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class RGBColorVerification {
    public static void main(String[] args) throws InterruptedException {
        ChromeDriver driver = new ChromeDriver();        
        driver.get("https://temples.vibhaga.com/"); 
        Thread.sleep(5000);
        WebElement element = driver.findElement(By.xpath("//A[@href='https://temples.vibhaga.com/digital-panchang/']"));
        Actions act=new Actions(driver);
        act.moveToElement(element).perform();
        Thread.sleep(2000);
        String actualColor = element.getCssValue("text-decoration-color");
        System.out.println(actualColor);
        int[] actualRGB = parseRGB(actualColor);
        String rgb="255,104,5";
        String[] rgbArray = rgb.split(",");
        int[] expectedRGB = new int[3];   
        for (int i = 0; i < 3; i++) {
        	expectedRGB[i] = Integer.parseInt(rgbArray[i]);
        }
        if (compareColors(actualRGB, expectedRGB)) {
            System.out.println("Element color matches the expected color.");
        } else {
            System.out.println("Element color does not match the expected color.");
        }
                driver.quit();
    }
    private static int[] parseRGB(String color) {
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(color);
        int[] rgb = new int[3];
        for (int i = 0; i < 3; i++) {
            matcher.find();
            rgb[i] = Integer.parseInt(matcher.group());
        }
        return rgb;
    }
        private static boolean compareColors(int[] color1, int[] color2) {
        for (int i = 0; i < 3; i++) {
            if (color1[i] != color2[i]) {
                return false;
            }
        }
        return true;
    }
}
