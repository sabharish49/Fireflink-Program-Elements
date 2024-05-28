package Practice;


import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

public class cordinates {

	public static void main(String[] args) throws MalformedURLException, InterruptedException {
		
		DesiredCapabilities dc = new DesiredCapabilities();
		dc.setCapability("platformName", "Android");

		URL url = new URL("http://localhost:4725/wd/hub");
		AndroidDriver driver = new AndroidDriver(url, dc);
		Thread.sleep(3000);
		
		WebElement element = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[5]"));	
		Dimension size = element.getSize();
		
		Point location = element.getLocation();
		int x=location.getX();
		int y=location.getY();
		
		int width=size.getWidth();
		int height=size.getHeight();
		
		System.out.println(x+width/3);
		System.out.println(y+height/2);
		
		int x1=x+width/3;
		int y1=y+height/2;
		Actions action=new Actions(driver);
		//action.moveToLocation(x1,y1 ).click().perform();
		System.out.println("clicked");
		String input="111111";
			for(int i=0;i<input.length();i++) {
				char ch=input.charAt(i);
				int value=ch-'0';
				switch(value) {
				case 0:

					driver.pressKey(new KeyEvent().withKey(AndroidKey.DIGIT_0));
					break;
				case 1:

					driver.pressKey(new KeyEvent().withKey(AndroidKey.DIGIT_1));
					break;
				case 2:

					driver.pressKey(new KeyEvent().withKey(AndroidKey.DIGIT_2));
					break;
				case 3:

					driver.pressKey(new KeyEvent().withKey(AndroidKey.DIGIT_3));
					break;
				case 4:

					driver.pressKey(new KeyEvent().withKey(AndroidKey.DIGIT_4));
					break;
				case 5:

					driver.pressKey(new KeyEvent().withKey(AndroidKey.DIGIT_5));
					break;
				case 6:

					driver.pressKey(new KeyEvent().withKey(AndroidKey.DIGIT_6));
					break;
				case 7:

					driver.pressKey(new KeyEvent().withKey(AndroidKey.DIGIT_7));
					break;
				case 8:

					driver.pressKey(new KeyEvent().withKey(AndroidKey.DIGIT_8));
					break;
				case 9:

					driver.pressKey(new KeyEvent().withKey(AndroidKey.DIGIT_9));
					break;
				default:
					System.out.println("none");
				}
			}

	}

}

