package extentReports;

import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class DemoTest {
	@Test
	public void demo()
	{
		ExtentSparkReporter spark=new ExtentSparkReporter("./HTML_Reports/extentReport.html");
		ExtentReports report=new ExtentReports();
		report.attachReporter(spark);
		ExtentTest test = report.createTest("demo test");
		test.log(Status.INFO, "logged");
		report.flush();
		
	}
	

}
