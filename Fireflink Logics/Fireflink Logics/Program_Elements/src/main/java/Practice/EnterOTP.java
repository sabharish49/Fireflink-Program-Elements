package Practice;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

public class EnterOTP {
    public static void main(String[] args) throws MalformedURLException, InterruptedException {
        String OTP = "1234";
        DesiredCapabilities capabilitiesApp1 = new DesiredCapabilities();
        capabilitiesApp1.setCapability("platformName", "Android");
        URL appiumURL = new URL("http://localhost:4723/wd/hub");
        AppiumDriver driver = new AndroidDriver(appiumURL, capabilitiesApp1);
        System.out.println("app launched");
        Thread.sleep(5000);     
        List<String> otpList = new ArrayList<>();
        for (int i = 0; i < OTP.length(); i++) {
            otpList.add(String.valueOf(OTP.charAt(i)));
        }     
        int j=1;       
        for (int i = 0; i <otpList.size(); i++) {
        	System.out.println("entering otp");
            String digit = otpList.get(i);
            driver.findElement(By.xpath("(//android.widget.TextView[@text='Enter OTP :']/..//android.widget.EditText)["+j+"]")).click();
            driver.findElement(By.xpath("(//android.widget.TextView[@text='Enter OTP :']/..//android.widget.EditText)["+j+"]"))
                  .sendKeys(digit);
            j++;
        }
    }
}
