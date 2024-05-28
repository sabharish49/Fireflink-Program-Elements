package Practice;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;

public class veriify_Stock {
	public static void main(String[] args) throws MalformedURLException, InterruptedException {
		DesiredCapabilities dc=new DesiredCapabilities();
		dc.setCapability("noReset", "true");
		dc.setCapability("platformName", "Android");
		dc.setCapability("noReset", true);
		URL url = new URL("http://localhost:4723/wd/hub");
		AndroidDriver driver = new AndroidDriver(url, dc);
		Thread.sleep(5000);
		String xpath = "//android.view.View[contains(@content-desc,\"OPEN\")]/../following-sibling::android.view.View//android.view.View//android.view.View//android.widget.ScrollView//android.view.View//android.view.View//android.view.View";
        String stockName="BANKNIFTY";
		List<WebElement> elementsList = driver.findElements(By.xpath(xpath));
  	  for(int i=0;i<elementsList.size();i++) {
      	  xpath='('+xpath+')'+'['+i+']';
      	  WebElement element = driver.findElement(By.xpath(xpath));
      	  String contentDesc = element.getAttribute("content-desc");
      	  if(contentDesc.contains(stockName)) {
      		  System.out.println("stock is available");
      	  }
      	  else {
      		  
      	  }
	}
	}
}
