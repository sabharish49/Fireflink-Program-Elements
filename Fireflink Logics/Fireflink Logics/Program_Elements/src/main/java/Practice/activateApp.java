package Practice;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;

public class activateApp {
	public static void main(String[] args) throws MalformedURLException, InterruptedException {
		 DesiredCapabilities capabilitiesApp1 = new DesiredCapabilities();
	     capabilitiesApp1.setCapability("platformName", "Android");
	     Thread.sleep(3000);
	     AndroidDriver  driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilitiesApp1);
	     String adbCommand = "adb shell am start -a android.intent.action.MAIN" +
                 " -c android.intent.category.LAUNCHER" +
                 " -n com.apacfin.newalpha/com.apacfin.newalpha.view.basic.splash.SplashActivity";
         try {
             Process process = Runtime.getRuntime().exec(adbCommand);
             process.waitFor();
         } catch (Exception e) {
             e.printStackTrace();
         }  
	}
}