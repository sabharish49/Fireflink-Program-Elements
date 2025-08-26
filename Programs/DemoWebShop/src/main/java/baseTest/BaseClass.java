package baseTest;

import java.lang.reflect.Method;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.tricenties.javaUtility.JavaUtility;

public class BaseClass {
	public  JavaUtility jlib= new JavaUtility();
	public ExtentSparkReporter spark;
	public ExtentReports report;
	public static WebDriver driver;
	public static ExtentTest test;
	@BeforeSuite
	public void reportCofig()
	{
		String time = jlib.getTime();

		spark=new ExtentSparkReporter("./HTML_Reports/extentReport"+time+".html");
		report=new ExtentReports();
		report.attachReporter(spark);
	}
	@BeforeClass
	public void browserSetup()
	{
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}
	@BeforeMethod
	public void methodSetup(Method method)
	{
        test=report.createTest(method.getName());
        driver.get("https://demowebshop.tricentis.com/");
        test.log(Status.PASS, "welcome page is displayed");      
	}
	@AfterClass
	public void closeBrowser()
	{
		driver.quit();
	}
	@AfterSuite
	public void reportBackup()
	{
		report.flush();
	}



}
