package default_package;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;

public class SeparateCalender {
    public static void main(String[] args) throws MalformedURLException {

        DesiredCapabilities dc = new DesiredCapabilities();
        dc.setCapability("appium:platformName", "Android");
        URL url = new URL("http://localhost:4723/wd/hub");
        AndroidDriver driver = new AndroidDriver(url, dc);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        System.out.println("app launched");

        
        String date = "12/12/2002";  

        
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        LocalDate localDate = LocalDate.parse(date, inputFormatter);
        String formattedDate = localDate.format(inputFormatter);
        

        System.out.println(formattedDate);
        
       

        
        String[] userDateParts = formattedDate.split("[-/]");
        List<String> dateList = new ArrayList<>();
        for (String part : userDateParts) {
            dateList.add(part);
        }
       
        System.out.println("user day list is "+dateList);
        
        String userDay = dateList.get(0);  
        int userDaynum = Integer.parseInt(userDay);
        String userMonth = dateList.get(1);
        int userMonthnum = Integer.parseInt(userMonth);
        String userYear = dateList.get(2);  
        int userYearnum = Integer.parseInt(userYear);

       
        String actualDate = driver.findElement(By.xpath("//android.view.View[android.view.View[android.view.View[android.view.View[android.view.View[android.view.View[android.view.View[android.widget.ListView[android.view.View[android.view.View[android.view.View[android.widget.TextView[@class='android.widget.TextView']]]]]]]]]]]]/descendant-or-self::android.widget.TextView[contains(@class,'android.widget.TextView')][1]")).getText();
        System.out.println("Actual date from UI: " + actualDate);

       
        String[] actualDateParts = actualDate.split("[-/]");
        List<String> actualDateList = new ArrayList<>();
        for (String part : actualDateParts) {
            actualDateList.add(part);
        }
        
        System.out.println("UI day list is "+actualDateList);
        
        String uiDay = actualDateList.get(0);   
        int uiDaynum = Integer.parseInt(uiDay);
        String uiMonth = actualDateList.get(1); 
        int uiMonthnum = Integer.parseInt(uiMonth);
        String uiYear = actualDateList.get(2);  
        int uiYearnum = Integer.parseInt(uiYear);
               
         int monthDifference =  Math.abs(uiMonthnum-userMonthnum);
         
         int dayDifference = Math.abs(uiDaynum-userDaynum);
       
         int yearDifference = Math.abs(uiYearnum-userYearnum);
        
        System.out.println("Year difference: " + yearDifference);
        System.out.println("Month difference: " + monthDifference);
        System.out.println("Day difference: " + dayDifference);
        
        List<Integer> differenceList = new ArrayList<>();
        differenceList.add(monthDifference);
        differenceList.add(dayDifference);
        differenceList.add(yearDifference);
        
        WebElement year =   driver.findElement(By.xpath("(//android.widget.ListView)[3]"));
        WebElement day =   driver.findElement(By.xpath("(//android.widget.ListView)[2]"));
        WebElement month =   driver.findElement(By.xpath("(//android.widget.ListView)[1]"));
        
        swipe(driver,year,yearDifference,userYearnum,uiYearnum);
        swipe(driver,day,dayDifference,userDaynum,uiDaynum);
        swipe(driver,month,monthDifference,userMonthnum,uiMonthnum);
        driver.findElement(By.xpath("//android.widget.Button[@text='Set']")).click();
   
    }
        
        public static void swipe(AndroidDriver driver, WebElement element,int difference,int userNum,int uiNum) {
        
            int x = element.getLocation().getX();
            int y = element.getLocation().getY();
            int width = element.getSize().getWidth();
            int height = element.getSize().getHeight();
            int startX = (int) (x + (width * 0.5));
            int startY = (int) (y + (height * 0.3));

            int endX = (int) (x + width);
            int endY = (int) (y + (height * 0.5));
      
			for (int i = 0; i < difference; i++) {
				
				if (userNum == uiNum) {
		            System.out.println("Already at the target year.");
		            
		        } else if (userNum < uiNum) {
		            System.out.println("Target year is earlier, scroll down.");
		            scroll(driver, startX, endY, endX, startY);
		        } else {
		            System.out.println("Target year is later, scroll up.");
		            scroll(driver, startX, startY, endX, endY);
		        }
                	       
            }
        }
        
    
    public static void scroll(AndroidDriver driver, int startX, int startY, int endX, int endY) {
        // Create a pointer input for touch gestures
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");

        // Define a swipe action sequence
        Sequence swipe = new Sequence(finger, 1)
                .addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY))
                .addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                .addAction(finger.createPointerMove(Duration.ofMillis(1000), PointerInput.Origin.viewport(), endX, endY))
                .addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        // Perform the swipe
        driver.perform(Arrays.asList(swipe));
    }
}
