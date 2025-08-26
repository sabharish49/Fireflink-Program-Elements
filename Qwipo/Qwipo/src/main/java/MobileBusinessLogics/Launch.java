package MobileBusinessLogics;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.windows.WindowsDriver;


public class Launch {

	public static void main(String[] args) throws MalformedURLException {
		// TODO Auto-generated method stub
DesiredCapabilities cap=new DesiredCapabilities();
cap.setCapability("appium:platformName", "Windows");
cap.setCapability("appium:app", "C:\\Users\\Public\\Desktop\\SmartGreek.lnk");
WindowsDriver driver=new WindowsDriver(new URL("http://localhost:4723/wd/hub"),cap);
System.out.println("done");
	}

}
