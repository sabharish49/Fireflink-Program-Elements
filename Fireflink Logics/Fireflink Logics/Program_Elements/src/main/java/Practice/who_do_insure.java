package Practice;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class who_do_insure {
    public static void main(String[] args) throws InterruptedException {

        ChromeDriver driver=new ChromeDriver();
        driver.get("https://mtpre.adityabirlahealth.com/healthinsurance/homepage");
        driver.manage().window().maximize();
        Thread.sleep(30000);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        String insureType = "SELF:30,SPOUSE:38,SON:12";
        
        List<String> relationshipList = new ArrayList<>();
        List<Integer> ageList = new ArrayList<>();
        List<Integer> countList = new ArrayList<>();
        
        Pattern pattern = Pattern.compile("(\\w+):(\\d+)(?:\\|(\\d+))?,?\\s*");
        Matcher matcher = pattern.matcher(insureType);
             
        while (matcher.find()) {
            String relationship = matcher.group(1);
            int age = Integer.parseInt(matcher.group(2));
            int count = matcher.group(3) != null ? Integer.parseInt(matcher.group(3)) : 1;
            
            relationshipList.add(relationship);
            ageList.add(age);
            countList.add(count);
        }
        
        System.out.println("Relationships: " + relationshipList);
        System.out.println("Ages: " + ageList);
        System.out.println("Counts: " + countList);
        
        Set<String> handles = driver.getWindowHandles();
        String currentHandle = driver.getWindowHandle();
        for (String handle : handles) {
            if (!handle.equals(currentHandle)) {
                driver.switchTo().window(handle);
                break;
            }
        }
        driver.findElement(By.xpath("//h2[text()='Show others ']")).click();
        for (int i = 0; i < relationshipList.size(); i++) {
        	  WebElement element=null;
            String insureName = relationshipList.get(i);
            try {
                 element = driver.findElement(By.xpath("(//span[@class='member-name' and text()='"+insureName+"']/..)[1]"));
            }catch (Exception e) {
                element=driver.findElement(By.xpath("//h2[text()='Show others ']/../..//span[@class='member-name' and contains(text(),'"+insureName+"')]"));
			}
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView(true);", element);
            if(insureName.equals("SELF") || insureName.equals("SPOUSE")) {
            element.click();
            }
            else if (insureName.equals("SON") || insureName.equals("DAUGHTER")) {
                driver.findElement(By.xpath("//span[@class=\"member-name\" and contains(text(),'"+insureName+"')]/..//div//img"))
                        .click();
            }
            else {
          driver.findElement(By.xpath("//h2[text()='Show others ']/../..//span[@class='member-name' and contains(text(),'"+insureName+"')]")).click();
            }          
                Integer age = ageList.get(i);
                try {
                    driver.findElement(By.xpath("//span[@class=\"member-name\" and contains(text(),'"+insureName+"')]/following-sibling::input"))
                    .sendKeys(String.valueOf(age));
				} catch (Exception e) {
					 driver.findElement(By.xpath("//h2[text()='Show others ']/../..//span[@class='member-name' and contains(text(),'"+insureName+"')]/following-sibling::input"))
                     .sendKeys(String.valueOf(age));
				}
            }              
        System.out.println("loop exited");
        Thread.sleep(3000);
        driver.quit();
    }
}
