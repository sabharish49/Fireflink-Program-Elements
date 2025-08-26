package extentReports;

import java.time.LocalDateTime;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

	public class ExtendReportWithScreenshot {
		@Test
		public void screenshot()
		{
			
			WebDriver driver= new ChromeDriver();
			driver.manage().window().maximize();
			driver.get("https://demowebshop.tricentis.com/");
			String time = LocalDateTime.now().toString().replaceAll(":", "-");
			TakesScreenshot ts= (TakesScreenshot) driver;
			String errorImage = ts.getScreenshotAs(OutputType.BASE64);
			ExtentSparkReporter spark=new ExtentSparkReporter("./HTML_Reports/extentReport"+time+".html");
			ExtentReports report=new ExtentReports();
			report.attachReporter(spark);
			ExtentTest test = report.createTest("screenshot");
			
			test.log(Status.INFO, "logged");
			test.addScreenCaptureFromBase64String(errorImage, "captured Image");
			report.flush();
			
		}

	}

