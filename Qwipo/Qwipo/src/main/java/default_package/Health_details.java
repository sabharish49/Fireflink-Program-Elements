package default_package;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;

public class Health_details {
	public static AndroidDriver driver;
	
	
	public static void swipe(AndroidDriver driver) {
		
		int screenWidth = driver.manage().window().getSize().getWidth();
        int screenHeight = driver.manage().window().getSize().getHeight();

        // Swipe up example
        int startX = screenWidth / 2;
        int startY = (int) (screenHeight * 0.8);
        int endX = screenWidth / 2;
        int endY = (int) (screenHeight * 0.3);
        // Create a virtual finger input
        PointerInput finger1 = new PointerInput(PointerInput.Kind.TOUCH, "finger1");

        // Define the swipe sequence
        Sequence swipe = new Sequence(finger1, 1)
                .addAction(finger1.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY))
                .addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                .addAction(finger1.createPointerMove(Duration.ofMillis(1000), PointerInput.Origin.viewport(), endX, endY))
                .addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        // Perform the swipe action
        driver.perform(Arrays.asList(swipe));
    }
	
	
	
	
	public static void main(String[] args) throws MalformedURLException {
		
		DesiredCapabilities dc = new DesiredCapabilities();
        dc.setCapability("appium:platformName", "Android");
        URL url = new URL("http://localhost:4723/wd/hub");
        driver = new AndroidDriver(url, dc);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        System.out.println("app launched");
       // JavascriptExecutor js = (JavascriptExecutor) driver;
        
        
		
			LinkedHashMap<String, Object> orderedMap = new LinkedHashMap<>();
			Pattern pattern = Pattern.compile("\\\"([^\\\"]+)\\\":(\\[.*?\\]|\\\".*?\\\"),?");
		
			String jsonStr="{\r\n"
					+ "\"Have you or any of the persons proposed for insurance, ever suffered from or taken treatment, or hospitalized for or have been recommended to take investigations / medication / surgery or undergone a surgery for medical conditions specified on Proposal form?\":\"Yes\",\r\n"
					+ "\r\n"
					+ "\r\n"
					+ "\r\n"
					+ "\"Any other illness/disease/injury/disability in the past other than childbirth, flu, or minor injuries, that has/have completely healed?\":\"Yes\",\r\n"
					+ "\r\n"
					+ "\r\n"
					+ "\"Are you or any persons proposed on regular medication (including any Ayurvedic treatment) or awaiting any procedure/treatment?\":\"Yes\",\r\n"
					+ "\r\n"
					+ "\r\n"
					+ "\r\n"
					+ "\"Have you ever been diagnosed with any of these medical conditions with or without any follow-up tests/medications? — Elevated Blood Sugar/ Diabetes/ Elevated Blood Pressure/ Hypertension/High Cholesterol/ Hypothyroidism?\":\"Yes\",\r\n"
					+ "\r\n"
					+ "\r\n"
					+ "\"Is any of the insured pregnant currently? If yes, please mention expected date of delivery (EDD). Any history of pregnancy related complications?\":\"Yes\",\r\n"
					+ "\r\n"
					+ "\r\n"
					+ "\r\n"
					+ "\"Has any application you've made for life, health, or critical illness insurance ever been declined, postponed, loaded, or made subject to any special conditions by any insurance company?\":\"Yes\",\r\n"
					+ "\r\n"
					+ "\r\n"
					+ "\"Has any health or life insurance policy ever been terminated in the past?\":\"Yes\"\r\n"
					+ "}";
			Matcher matcher = pattern.matcher(jsonStr);
			while (matcher.find()) {
				String key1 = matcher.group(1);
				String value = matcher.group(2);
			
					orderedMap.put(key1, value);
				}
			
			List<String> namelist = new ArrayList<String>();
			namelist.add("Self");
			//namelist.add("Spouse");
			namelist.add("Father");
			int size = namelist.size();
			
			
			String inputs="{\r\n"
					+ "\"Name of Disease\r\n"
					+ "Name of Disease\":\"Cataract - One eye\",\r\n"
					+ "\"Operative status\r\n"
					+ "Operative status\":\"Operated\",\r\n"
					+ "\"Treatment Status\r\n"
					+ "Treatment Status\":\"Cured\",\r\n"
					+ "\"Complication\r\n"
					+ "Complication\":\"No\",\r\n"
					+ "\"Date of Diagnostics\":\"30\",\r\n"
					+ "\"Expected date of delivery\":\"25-03-2025\",\r\n"
					+ "\"description\":\"description\"\r\n"
					+ "\r\n"
					+ "\r\n"
					+ "}";
			
			
			
			for (int i = 0; i <size; i++) {
				for (Map.Entry<String, Object> entry : orderedMap.entrySet()) {
					String name = namelist.get(i);
					System.out.println(name);
					String key1 = entry.getKey();
					Object value = entry.getValue();
					
						driver.findElement(By.xpath("//android.view.View[@content-desc='"+name+"']/descendant::android.view.View[@content-desc='"+entry.getKey()+"']/android.view.View[@content-desc=\"Yes\"]/preceding-sibling::android.widget.RadioButton")).click();
													
						  
						
						if (entry.getKey().equals("Have you or any of the persons proposed for insurance, ever suffered from or taken treatment, or hospitalized for or have been recommended to take investigations / medication / surgery or undergone a surgery for medical conditions specified on Proposal form?")) {
							
							  driver.findElement(By.xpath("//android.widget.Button[contains(@content-desc,'Name of Disease')]"
									)).click();
							
							 driver.findElement(By.xpath("//android.view.View[@content-desc=\"Calculus - Renal/Urinary Tract\"]")).click();
							 swipe(driver);
							
						
							
							 driver.findElement(By.xpath("//android.widget.Button[contains(@content-desc,'Operative status')]")).click();
							 driver.findElement(By.xpath("//android.view.View[@content-desc=\"Operated\"]")).click();
							// swipe(driver);
							 
							 driver.findElement(By.xpath("//android.widget.Button[contains(@content-desc,'Type of surgery')]"
							 		)).click();
							 driver.findElement(By.xpath("//android.view.View[@content-desc=\"Bilateral\"]")).click();
							// swipe(driver);
							 
							 

							 driver.findElement(By.xpath("//android.widget.Button[contains(@content-desc,'Treatment Status')]")).click();
							 driver.findElement(By.xpath("//android.view.View[@content-desc=\"Cured\"]")).click();
							// swipe(driver);
							 
							 driver.findElement(By.xpath("//android.widget.Button[contains(@content-desc,'Complication')]"
							 		)).click();
								 driver.findElement(By.xpath("//android.view.View[@content-desc=\"No\"]")).click();
								 swipe(driver);
							 
						  }
						
						
						
						        else if (entry.getKey().equals("Is any of the insured pregnant currently? If yes, please mention expected date of delivery (EDD). Any history of pregnancy related complications?")) {
						        	 swipe(driver);
						        	
						        	driver.findElement(By.xpath("//android.view.View[@content-desc=\"Is any of the insured pregnant currently? If yes, please mention expected date of delivery (EDD). Any history of pregnancy related complications?\"]/android.view.View[3]/android.view.View/android.widget.EditText")).click();
									driver.findElement(By.xpath("//android.view.View[@content-desc=\"Is any of the insured pregnant currently? If yes, please mention expected date of delivery (EDD). Any history of pregnancy related complications?\"]/android.view.View[3]/android.view.View/android.widget.EditText")).sendKeys("25-03-2025");
									 swipe(driver);
						        
						        }
								else if(entry.getKey().equals("Has any application you've made for life, health, or critical illness insurance ever been declined, postponed, loaded, or made subject to any special conditions by any insurance company?")) 
								{
									 swipe(driver);
									
									driver.findElement(By.xpath("//android.view.View[@content-desc=\"Has any application you've made for life, health, or critical illness insurance ever been declined, postponed, loaded, or made subject to any special conditions by any insurance company?\"]/android.view.View[3]/android.view.View/android.widget.EditText")).click();
									driver.findElement(By.xpath("//android.view.View[@content-desc=\"Has any application you've made for life, health, or critical illness insurance ever been declined, postponed, loaded, or made subject to any special conditions by any insurance company?\"]/android.view.View[3]/android.view.View/android.widget.EditText")).sendKeys("description");
									 swipe(driver);
								}
								else if(entry.getKey().equals("Has any health or life insurance policy ever been terminated in the past?"))
								{
									
									 swipe(driver);
									driver.findElement(By.xpath("//android.view.View[@content-desc=\"Has any health or life insurance policy ever been terminated in the past?\"]/android.view.View[3]/android.view.View/android.widget.EditText")).click();
									driver.findElement(By.xpath("//android.view.View[@content-desc=\"Has any health or life insurance policy ever been terminated in the past?\"]/android.view.View[3]/android.view.View/android.widget.EditText")).sendKeys("description");
									 swipe(driver);
								}
								else
								{
									System.out.println("entered else");
									
									driver.findElement(By.xpath("//android.widget.Button[contains(@content-desc,'Name of Disease')][last()]")).click();
									driver.findElement(By.xpath("//android.view.View[@content-desc=\"Calculus - Renal/Urinary Tract\"]")).click();
									driver.findElement(By.xpath("//android.view.View[@content-desc=\"Date Of Diagnostics\"]")).click();
									driver.findElement(By.xpath("//android.widget.Button[@content-desc=\"OK\"]")).click();
									swipe(driver);
									
								}
				}
			}
		}
	}


	