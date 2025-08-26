package Logics;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

public class OTP {

	public static void main(String[] args) throws MalformedURLException {
		String input="214263";
		DesiredCapabilities dc = new DesiredCapabilities();
		dc.setCapability("platformName", "Android");
		dc.setCapability("appium:skipServerInstallation", true);
		dc.setCapability("appium:disableAndroidWatchers", true);
		dc.setCapability("appium:disableWindowAnimation", true);
		dc.setCapability("appium:waitForIdleTimeout", 10);
		dc.setCapability("appium:skipDeviceInitialization", true);
		URL url = new URL("http://localhost:4723/wd/hub");
		AndroidDriver driver = new AndroidDriver(url, dc);
		
		
		System.out.println("started");
		//AndroidDriver driver=(AndroidDriver)nlpRequestModel.getAndroidDriver();
		//log.info("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!androidDriver!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		for(int i=0;i<input.length();i++) {
		//log.info("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!forLoop!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!"+i);
			char ch=input.charAt(i);
			//log.info("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!Char ch!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!"+ch+"           "+i);
			int value=ch-'0';
			switch(value) {
			case 0:
				//log.info("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!SwitchCase!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! Case0");

				driver.pressKey(new KeyEvent().withKey(AndroidKey.DIGIT_0));
				break;
			case 1:
				//log.info("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!SwitchCase!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! Case1");

				driver.pressKey(new KeyEvent().withKey(AndroidKey.DIGIT_1));
				break;
			case 2:
				//log.info("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!SwitchCase!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! Case2");

				driver.pressKey(new KeyEvent().withKey(AndroidKey.DIGIT_2));
				break;
			case 3:
				//log.info("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!SwitchCase!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! Case3");

				driver.pressKey(new KeyEvent().withKey(AndroidKey.DIGIT_3));
				break;
			case 4:
				//log.info("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!SwitchCase!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! Case4");

				driver.pressKey(new KeyEvent().withKey(AndroidKey.DIGIT_4));
				break;
			case 5:
				//log.info("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!SwitchCase!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! Case5");

				driver.pressKey(new KeyEvent().withKey(AndroidKey.DIGIT_5));
				break;
			case 6:
				//log.info("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!SwitchCase!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! Case6");

				driver.pressKey(new KeyEvent().withKey(AndroidKey.DIGIT_6));
				break;
			case 7:
				//log.info("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!SwitchCase!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! Case7");

				driver.pressKey(new KeyEvent().withKey(AndroidKey.DIGIT_7));
				break;
			case 8:
				//log.info("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!SwitchCase!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! Case8");

				driver.pressKey(new KeyEvent().withKey(AndroidKey.DIGIT_8));
				break;
			case 9:
				//log.info("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!SwitchCase!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! Case9");

				driver.pressKey(new KeyEvent().withKey(AndroidKey.DIGIT_9));
				break;
			default:
				System.out.println("none");
			}
		}

	}

}
