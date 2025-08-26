package default_package;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ExcelToString {

    public static void main(String[] args) {
    	 
           	
            	 Integer daysToAdd=0;
            	 LocalDate currentDate = LocalDate.now();
                 LocalDate futureDate = currentDate.plusDays(daysToAdd);
                 int futureDay = futureDate.getDayOfMonth();
                 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM");
                 String futureMonth = futureDate.format(formatter).toUpperCase();
                 
                 
           	
   			
         		
         		//month=month.substring(0,1)+month.substring(1).toLowerCase();
         		System.out.println(month);
         		System.out.println(calenderDate);
    	 }
}
