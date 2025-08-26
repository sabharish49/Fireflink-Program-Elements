package Business_Logics;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import com.tyss.optimize.nlp.util.annotation.InputParam;
import com.tyss.optimize.nlp.util.annotation.InputParams;

public class SelectTwoDateFinhaat implements Nlp {
    
    @InputParams({@InputParam(name = "StartDate", type = "java.lang.String"), @InputParam(name = "EndDate", type = "java.lang.String")})
    @Override
    public List<String> getTestParameters() throws NlpException {
        List<String> params = new ArrayList<>();
        return params;
    }

    @Override
    public StringBuilder getTestCode() throws NlpException {
        StringBuilder sb = new StringBuilder();
        return sb;
    }

    @Override
    public NlpResponseModel execute(NlpRequestModel nlpRequestModel) throws NlpException {
        NlpResponseModel nlpResponseModel = new NlpResponseModel();
        Map<String, Object> attributes = nlpRequestModel.getAttributes();
        WebDriver driver = nlpRequestModel.getWebDriver();
        Duration implicitWait = driver.manage().timeouts().getImplicitWaitTimeout();
        String startDate = (String) attributes.get("StartDate");
        String endDate = (String) attributes.get("EndDate");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
            String[] formats = {"yyyy-MM-dd", "dd/MM/yyyy", "MM-dd-yyyy", "yyyy/MM/dd", "dd-MM-yyyy"};
            LocalDate parsedStartDate = null, parsedEndDate = null;
            String startYearString = null, endYearString = null;
            String startMonth = null, endMonth = null;
            int startCalendarDate = 0, endCalendarDate = 0;

            // Parse Start Date
            parsedStartDate = parseDate(startDate, formats);
            if (parsedStartDate != null) {
                startCalendarDate = parsedStartDate.getDayOfMonth();
                startMonth = parsedStartDate.getMonth().toString().substring(0, 3);
                startYearString = Integer.toString(parsedStartDate.getYear());
            } else {
                nlpResponseModel.setStatus(CommonConstants.fail);
                nlpResponseModel.setMessage("Invalid start date format.");
                return nlpResponseModel;
            }

            // Parse End Date
            parsedEndDate = parseDate(endDate, formats);
            if (parsedEndDate != null) {
                endCalendarDate = parsedEndDate.getDayOfMonth();
                endMonth = parsedEndDate.getMonth().toString().substring(0, 3);
                endYearString = Integer.toString(parsedEndDate.getYear());
            } else {
                nlpResponseModel.setStatus(CommonConstants.fail);
                nlpResponseModel.setMessage("Invalid end date format.");
                return nlpResponseModel;
            }

            // Ensure the start date is before the end date
            if (parsedStartDate.isAfter(parsedEndDate)) {
                nlpResponseModel.setStatus(CommonConstants.fail);
                nlpResponseModel.setMessage("Start date cannot be after the end date.");
                return nlpResponseModel;
            }

            // Open the calendar popup
           driver.findElement(By.xpath("//mat-calendar[contains(@class,'mat-calendar')]/descendant::span[contains(@id,'mat-calendar-button')]"))
           .click();

            // Select the Start Date
            selectDate(driver, wait, startYearString, startMonth, startCalendarDate);
            driver.findElement(By.xpath("//mat-calendar[contains(@class,'mat-calendar')]/descendant::span[contains(@id,'mat-calendar-button')]"))
            .click();
            // Select the End Date
            selectDate(driver, wait, endYearString, endMonth, endCalendarDate);
            nlpResponseModel.setStatus(CommonConstants.pass);
            nlpResponseModel.setMessage("Dates selected successfully. Start Date: " + startDate + ", End Date: " + endDate);
        } catch (Exception e) {
            nlpResponseModel.setStatus(CommonConstants.fail);
            nlpResponseModel.setMessage("Unable to select dates: " + e.getMessage());
        } finally {
            driver.manage().timeouts().implicitlyWait(implicitWait);
        }

        return nlpResponseModel;
    }

    // Method to parse the date
    private LocalDate parseDate(String date, String[] formats) {
        LocalDate parsedDate = null;
        for (String format : formats) {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
                parsedDate = LocalDate.parse(date, formatter);
                break;
            } catch (DateTimeParseException e) {
                // Continue to next format
            }
        }      
        return parsedDate;
    }

    // Method to select the date from the calendar    
    private void selectDate(WebDriver driver, WebDriverWait wait, String yearString, String month, int calendarDate) {
        WebElement yearElement = null;

        // Loop to select the correct year
        
        while (yearElement == null) {
            try {
                yearElement = driver.findElement(By.xpath("//mat-calendar[contains(@class,'mat-calendar')]/descendant::div[contains(text(),'" + yearString + "')]"));
                yearElement.click();
                break;
                
            } catch (NoSuchElementException e) {
                WebElement previousButton = driver.findElement(By.xpath("//button[contains(@class,'mat-calendar-previous-button')]"));
                wait.until(ExpectedConditions.elementToBeClickable(previousButton));
                previousButton.click();
            }
        }

        // Select the month
        driver.findElement(By.xpath("//mat-calendar[contains(@class,'mat-calendar')]/descendant::div[contains(text(),'" + month + "')]")).click();

        // Select the day of the month
        driver.findElement(By.xpath("//mat-calendar[contains(@class,'mat-calendar')]/descendant::div[normalize-space(.)='" + calendarDate + "']")).click();
    }
}
