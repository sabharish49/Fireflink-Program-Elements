package default_package;

	
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

	public class DateCalculatorFinhatUsingPlugin {
	    public static void main(String[] args) throws InterruptedException {
	    	String inputDate = "23-10-2000";  
	    	String calenderName="DOB";
	        
	        String[] formats = {"yyyy-MM-dd", "dd/MM/yyyy", "MM-dd-yyyy", "yyyy/MM/dd","dd-MM-yyyy"};
	        LocalDate parsedDate = null;
	        String yearString=null;
	        String month=null;
	        int calendarDate=0;
	       
	        for (String format : formats) {
	            try {
	                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
	                parsedDate = LocalDate.parse(inputDate, formatter);
	                break;  
	            } catch (DateTimeParseException e) {
	               
	            }
	        }    
	       
	        if (parsedDate != null) {
	          
	            calendarDate = parsedDate.getDayOfMonth();
	             month = parsedDate.getMonth().toString().substring(0, 3);
	            int year = parsedDate.getYear();
	             yearString = Integer.toString(year);
	            
	           
	            DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
	            String formattedDate = parsedDate.format(outputFormatter).toUpperCase();
	            
	            System.out.println("Formatted Date: " + formattedDate);
	            System.out.println("Day: " + calendarDate);
	            System.out.println("Month: " + month);
	            System.out.println("Year: " + year);
	            
	        } else {
	            System.out.println("Invalid date format.");
	        }	      
	        
	        WebDriver driver= new ChromeDriver();
	        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
	        driver.manage().window().maximize();
	        driver.get("https://bimatest.finhaat.com/#/bima");
	        driver.findElement(By.xpath("//input[@placeholder=\"Mobile number/UserId\"]")).sendKeys("Testingfos");
	        driver.findElement(By.xpath("//input[@placeholder=\"Password\"]")).sendKeys("8208257327");
	        driver.findElement(By.xpath("//button[@type=\"submit\"]")).click();	          	        
	        WebDriverWait wait =new WebDriverWait(driver,Duration.ofSeconds(10));	   	   
	        System.out.println("execution started");
	        Thread.sleep(5000);
	        WebElement calendarPopup = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//mat-calendar[contains(@class, 'mat-calendar')]")));
	        driver.findElement(By.className("mat-focus-indicator mat-calendar-period-button mat-button mat-button-base")).click();	        
	        WebElement yearElement = null;

	        while (yearElement == null) {
	            try {
	               
	                yearElement = driver.findElement(By.xpath("//small[contains(text(),'" + calenderName + "')]/ancestor::app-cdot-entry-form/../following-sibling::div[@class='cdk-overlay-container']/descendant::div[contains(text(),'" + yearString + "')]"));
	                yearElement.click();
	                break;
	                
	            } catch (NoSuchElementException e) {
	                
	                WebElement previousButton = driver.findElement(By.xpath("//small[contains(text(),'" + calenderName + "')]/ancestor::app-cdot-entry-form/../following-sibling::div[@class='cdk-overlay-container']/descendant::button[contains(@class,'mat-focus-indicator mat-calendar-previous-button mat-icon-button mat-button-base')]"));
	                wait.until(ExpectedConditions.elementToBeClickable(previousButton));
	                previousButton.click();
	            }
	        }	        
	     
	        driver.findElement(By.xpath("//BUTTON[@class='mat-focus-indicator mat-calendar-previous-button mat-icon-button mat-button-base']/following::DIV[normalize-space(.)='"+month+"']")).click();
	        
	        driver.findElement(By.xpath("//small[contains(text(),'"+calenderName+"')]/ancestor::app-cdot-entry-form/../following-sibling::div[@class='cdk-overlay-container']/descendant::div[contains(text(),'"+calendarDate+"')]")).click();
	        
	    
	    }
	}