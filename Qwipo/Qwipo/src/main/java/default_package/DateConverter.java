package default_package;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DateConverter {
    public static void main(String[] args) {
        String inputDate = "23-10-2023";  
        
        
        String[] formats = {"yyyy-MM-dd", "dd/MM/yyyy", "MM-dd-yyyy", "yyyy/MM/dd","dd-MM-yyyy"};
        LocalDate parsedDate = null;
        
       
        for (String format : formats) {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
                parsedDate = LocalDate.parse(inputDate, formatter);
                break;  
            } catch (DateTimeParseException e) {
               
            }
        }
        
       
        if (parsedDate != null) {
            // Extract day, month, and year
            int day = parsedDate.getDayOfMonth();
            String month = parsedDate.getMonth().toString().substring(0, 3);
            int year = parsedDate.getYear();

           
            DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
            String formattedDate = parsedDate.format(outputFormatter).toUpperCase();
            
            System.out.println("Formatted Date: " + formattedDate);
            System.out.println("Day: " + day);
            System.out.println("Month: " + month);
            System.out.println("Year: " + year);
            
        } else {
            System.out.println("Invalid date format.");
        }
    }
    
    
    
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

 // Click the calendar button
 WebElement calendarButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//small[contains(text(),'" + calenderName + "')]/ancestor::app-cdot-entry-form/../following-sibling::div[@class='cdk-overlay-container']/descendant::span[contains(@id,'mat-calendar-button')]")));
 calendarButton.click();

 // Wait for the calendar popup to appear
 WebElement calendarPopup = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//mat-calendar[contains(@class, 'mat-calendar')]")));

 // Continue with your calendar date selection logic
 WebElement yearElement = driver.findElement(By.xpath("//small[contains(text(),'" + calenderName + "')]/ancestor::app-cdot-entry-form/../following-sibling::div[@class='cdk-overlay-container']/descendant::div[contains(text(),'" + yearString + "')]"));
 yearElement.click();

}
