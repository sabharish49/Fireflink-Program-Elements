package com.tricenties.listenersUtility;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.Status;

import baseTest.BaseClass;

public class ListenerImplementation extends BaseClass implements ITestListener {

	@Override
	public void onTestFailure(ITestResult result) {
		TakesScreenshot ts=(TakesScreenshot) driver;
		test.addScreenCaptureFromBase64String(ts.getScreenshotAs(OutputType.BASE64));
		test.log(Status.FAIL, result.getName()+"test script failed");
		
	}
	
	
	

}
