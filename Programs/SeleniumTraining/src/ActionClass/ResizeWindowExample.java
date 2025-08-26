package ActionClass;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.JavascriptExecutor;

public class ResizeWindowExample {
    public static void main(String[] args) {
     
        WebDriver driver = new ChromeDriver();

       
        driver.get("https://alphapreprod.apacfin.in:8087/");

        
        JavascriptExecutor js = (JavascriptExecutor) driver;

       
        js.executeScript("document.body.style.zoom='150%'");

       
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

      
        js.executeScript("document.body.style.zoom='50%'");

     
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        js.executeScript("document.body.style.zoom='100%'");

       
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

      
        driver.quit();
    }
}
