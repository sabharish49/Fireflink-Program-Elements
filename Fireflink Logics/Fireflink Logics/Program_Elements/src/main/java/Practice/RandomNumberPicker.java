package Practice;

import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class RandomNumberPicker {
    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.get("https://alphapreprod.apacfin.in:8087/");
        driver.manage().window().maximize();
        Thread.sleep(30000);
        WebElement dropdown = driver.findElement(By.xpath("//select[@id='mtenor']"));
        Select select = new Select(dropdown);
        List<WebElement> options = select.getOptions();
        int totalOptions = options.size();
        Random random = new Random();
        int randomIndex = random.nextInt(totalOptions);
        select.selectByIndex(randomIndex);
    }
}