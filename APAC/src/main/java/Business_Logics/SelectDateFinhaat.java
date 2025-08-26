package Business_Logics;

import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import com.tyss.optimize.nlp.util.annotation.InputParam;
import com.tyss.optimize.nlp.util.annotation.InputParams;

import java.util.Map;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SelectDateFinhaat implements Nlp {
    
    @InputParams({@InputParam(name = "Date", type = "java.lang.String")})
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
        String inputDate = (String) attributes.get("Date");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
            String[] formats = {"yyyy-MM-dd", "dd/MM/yyyy", "MM-dd-yyyy", "yyyy/MM/dd", "dd-MM-yyyy"};
            LocalDate parsedDate = null;
            String yearString = null;
            String month = null;
            int calendarDate = 0;

            for (String format : formats) {
                try {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
                    parsedDate = LocalDate.parse(inputDate, formatter);
                    break;
                } catch (DateTimeParseException e) {
                    // Continue to next format
                }
            }

            if (parsedDate != null) {
                int monthValue = parsedDate.getMonthValue();
                if (monthValue > 12) {
                    throw new IllegalArgumentException("Invalid month value. Please provide a valid month (1-12). Input: " + inputDate);
                }

                calendarDate = parsedDate.getDayOfMonth();
                month = parsedDate.getMonth().toString().substring(0, 3);
                int year = parsedDate.getYear();
                yearString = Integer.toString(year);

            } else {
                nlpResponseModel.setStatus(CommonConstants.fail);
                nlpResponseModel.setMessage("Invalid date format.");
                return nlpResponseModel;
            }

            WebElement calendarPopup = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//mat-calendar[contains(@class, 'mat-calendar')]")));
            driver.findElement(By.xpath("//app-cdot-entry-form/../following-sibling::div[@class='cdk-overlay-container']/descendant::span[contains(@id,'mat-calendar-button')]")).click();

            WebElement yearElement = null;

            while (yearElement == null) {
                try {
                    yearElement = driver.findElement(By.xpath("//app-cdot-entry-form/../following-sibling::div[@class='cdk-overlay-container']/descendant::div[contains(text(),'" + yearString + "')]"));
                    yearElement.click();
                    break;
                } catch (NoSuchElementException e) {
                    WebElement previousButton = driver.findElement(By.xpath("//button[contains(@class,'mat-calendar-previous-button')]"));
                    wait.until(ExpectedConditions.elementToBeClickable(previousButton));
                    previousButton.click();
                }
            }

            driver.findElement(By.xpath("//app-cdot-entry-form/../following-sibling::div[@class='cdk-overlay-container']/descendant::div[contains(text(),'" + month + "')]")).click();
            driver.findElement(By.xpath("//app-cdot-entry-form/../following-sibling::div[@class='cdk-overlay-container']/descendant::div[normalize-space(.)='" + calendarDate + "']")).click();

            nlpResponseModel.setStatus(CommonConstants.pass);
            nlpResponseModel.setMessage("Date selected successfully");
        } catch (IllegalArgumentException e) {
            nlpResponseModel.setStatus(CommonConstants.fail);
            nlpResponseModel.setMessage(e.getMessage());
        } catch (Exception e) {
            nlpResponseModel.setStatus(CommonConstants.fail);
            nlpResponseModel.setMessage("Unable to select Date: " + e.getMessage());
        } finally {
            driver.manage().timeouts().implicitlyWait(implicitWait);
        }

        return nlpResponseModel;
    }
}
