package android;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;

public class Android {

	public static void main(String[] args) throws MalformedURLException {
		    DesiredCapabilities dc = new DesiredCapabilities();
	        dc.setCapability("deviceName", "Samsung A13");
			dc.setCapability("platformName", "Android");

			dc.setCapability("appPackage", "com.choiceequitybroking.jiffy");
			dc.setCapability("appActivity", "com.choiceequitybroking.jiffy2.ui.splash.activity.SplashActivity");
			dc.setCapability("noReset","true");
	        
	     // AndroidDriver driver = new AndroidDriver(new URL("http://localhost:4723/wd/hub"), dc);
	      AndroidDriver driver = null;
	      driver = new AndroidDriver(new URL("http://localhost:4723/wd/hub"),dc);



	}

}
