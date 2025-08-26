
package Business_Logics;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import com.tyss.optimize.nlp.util.annotation.ReturnType;

import io.appium.java_client.android.AndroidDriver;




public class APACpdQuestionsPE implements Nlp {
	// @InputParams({@InputParam(name = "string1", type = "java.lang.String"),
	// @InputParam(name = "string2", type = "java.lang.String")})
	@ReturnType(name = "result", type = "java.lang.Boolean")

	@Override
	public List<String> getTestParameters() throws NlpException {
		List<String> params = new ArrayList<>();
		return params;
	}

	@Override
	public StringBuilder getTestCode() throws NlpException {
		StringBuilder sb = new StringBuilder();
		return sb;
	}

	@Override
	public NlpResponseModel execute(NlpRequestModel nlpRequestModel) throws NlpException {

		NlpResponseModel nlpResponseModel = new NlpResponseModel();
		Map<String, Object> attributes = nlpRequestModel.getAttributes();
//          String string1 = (String) attributes.get("string1");
//          String string2 = (String) attributes.get("string2");

		boolean res=false;
		try {
			AndroidDriver driver = nlpRequestModel.getAndroidDriver();

			Map<String, String> textfields = new <String, String>HashMap();
			textfields.put("Over All Experience (years)", "10");
			textfields.put("Current Experience In The Present Industry (years)", "10");
			textfields.put("How Far Is The Office/business From Branch (kms)", "5");
			textfields.put("Shop Rent Amount", "100");
			textfields.put("Stock Volume (in Lakhs)", "100000");
			textfields.put("No Of Employees", "50");
			textfields.put("No. Of Customers/orders Observed During Business Visit", "10");
			textfields.put("noOfCustomersInDay", "2");
			textfields.put("Daily Sales/income", "100000");
			textfields.put("Agricultural Land Owned (in Acres)", "10");
			textfields.put("millMachinery", "Small");
			textfields.put("Size Of The Shop (sq.ft)", "800");
			textfields.put("No Of Same/similar Shops/services In The Village", "1");
			textfields.put("Profit Margin (%)", "30");

			Map<String, String> dropdown = new <String, String>HashMap();
			dropdown.put("Location", "Residential");
			dropdown.put("Shop Ownership", "Owned");
			dropdown.put("Shop Structure Type", "Pica");
			dropdown.put("Shop Type", "Permanent Structure");
			dropdown.put("Income Document Avaiability", "100% Pucca");
			dropdown.put("Msme Registration", "No");
			dropdown.put("Gst Registration", "No");
			dropdown.put("Shop & Establishment Act/registration Certificate", "No");
			dropdown.put("Agricultural Land Owned", "No");
			dropdown.put("Procurement/purchase Point", "Wholesale Puchase");
			dropdown.put("Purchase Frequency", "Fortnightly");
			dropdown.put("No Of Shops Owned", "1");
			dropdown.put("No Of Incomes", "Single ");
			dropdown.put("Type Of Employment", "Permanent");
			dropdown.put("Salary Type", "Bank Credit");
			dropdown.put("Salary Document", "Salary Certificate");

			Map<String, String> radioButton = new <String, String>HashMap();
			radioButton.put("Warehouse/storage Unit", "No");

			setQuestions(nlpRequestModel, nlpResponseModel, driver, textfields, dropdown, radioButton);
			
			res=true;
			nlpResponseModel.setStatus(CommonConstants.pass);
			nlpResponseModel.setMessage("Answers set successfully in PdQuestions");

		} catch (Exception e) {
			nlpResponseModel.setStatus(CommonConstants.fail);
			nlpResponseModel.setMessage("Failed to set answers in PdQuestions "+e);
		}

		
		nlpResponseModel.getAttributes().put("result", res);
		return nlpResponseModel;
	}

	private void setQuestions(NlpRequestModel nlpRequestModel, NlpResponseModel nlpResponseModel, AndroidDriver driver,
			Map<String, String> textfields, Map<String, String> dropdown, Map<String, String> radioButton) {
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

		String lastText = null;
		for (int i = 0; i < 10; i++) {
			List<WebElement> elements = driver.findElements(
					By.xpath("//android.widget.TextView[@resource-id='com.apacfin.newalpha:id/tvPdQuestion']"));

			lastText = driver
					.findElement(By.xpath(
							"(//android.widget.TextView[@resource-id='com.apacfin.newalpha:id/tvPdQuestion'])[last()]"))
					.getText();

			ArrayList<String> textOfElements = new ArrayList();

			for (WebElement webElement : elements) {
				textOfElements.add(webElement.getText());
				System.out.println(webElement.getText());
			}

			System.out.println("size of texts list " + textOfElements.size());
			int count = 0;
			boolean elementPresent = true;

			for (int j = 0; j < textOfElements.size() - 1; j++) {
				count++;
				String text = textOfElements.get(j);
				if (textfields.containsKey(text)) {
					if (!(driver.findElement(By.xpath("//android.widget.TextView[@text='" + text
							+ "']/../following-sibling::android.widget.EditText")).getText().equals("Ans"))) {
						continue;
					} else {
						driver.findElement(By.xpath("//android.widget.TextView[@text='" + text
								+ "']/../following-sibling::android.widget.EditText")).sendKeys(textfields.get(text));
					}
				}

				else if (dropdown.containsKey(text)) {
					if (!(driver.findElement(By.xpath("//android.widget.TextView[@text='" + text
							+ "']/../following-sibling::android.widget.LinearLayout//android.widget.Spinner//android.widget.TextView"))
							.getText().equals("Select"))) {
						continue;
					} else {
						driver.findElement(By.xpath("//android.widget.TextView[@text='" + text
								+ "']/../following-sibling::android.widget.LinearLayout//android.widget.Spinner"))
								.click();
						String value = dropdown.get(text);
						wait.until(ExpectedConditions.visibilityOf(driver
								.findElement(By.xpath("//android.widget.CheckedTextView[@text='" + value + "']"))));
						driver.findElement(By.xpath("//android.widget.CheckedTextView[@text='" + value + "']")).click();
					}
				}

				else if (radioButton.containsKey(text)) {
					String value = radioButton.get(text);
					driver.findElement(By.xpath("//android.widget.TextView[@text='" + text
							+ "']/../following-sibling::android.widget.RadioGroup//android.widget.RadioButton[@text='"
							+ value + "']")).click();

				} else {
					System.out.println("element is not present");
					elementPresent = false;
					break;
				}

			}

			if (elementPresent == false) {
				System.out.println("Please add the element into Map");
				break;
			}

			Dimension screenSize = driver.manage().window().getSize();// Identify screen dimension
			int screenCenter = (int) (screenSize.getWidth() * 0.5);// Identify center point of screen for Y axis
			int startPoint = (int) (screenSize.getHeight() * 0.5);// Identify beginning point of scroll for X axis
			int endPoint = (int) (screenSize.getHeight() * 0.1);// Identify ending point of scroll

			for (int j = 0; j < 1; j++) {
				PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
				Sequence swipe = new Sequence(finger, 1);
				swipe.addAction(finger.createPointerMove(Duration.ofSeconds(0), PointerInput.Origin.viewport(),
						screenCenter, startPoint));
				swipe.addAction(finger.createPointerDown(0));
				swipe.addAction(finger.createPointerMove(Duration.ofMillis(1000), PointerInput.Origin.viewport(),
						screenCenter, endPoint));
				swipe.addAction(finger.createPointerUp(0));
				driver.perform(Arrays.asList(swipe));
			}

			if (driver
					.findElement(By.xpath(
							"(//android.widget.TextView[@resource-id='com.apacfin.newalpha:id/tvPdQuestion'])[last()]"))
					.getText().equals(lastText) && count == textOfElements.size() - 1) {
				if (textfields.containsKey(lastText)) {
					driver.findElement(By.xpath("//android.widget.TextView[@text='" + lastText
							+ "']/../following-sibling::android.widget.EditText")).sendKeys(textfields.get(lastText));

				} else if (dropdown.containsKey(lastText)) {
					driver.findElement(By.xpath("//android.widget.TextView[@text='" + lastText
							+ "']/../following-sibling::android.widget.LinearLayout//android.widget.Spinner")).click();
					String value = dropdown.get(lastText);
					wait.until(ExpectedConditions.visibilityOf(
							driver.findElement(By.xpath("//android.widget.CheckedTextView[@text='" + value + "']"))));
					driver.findElement(By.xpath("//android.widget.CheckedTextView[@text='" + value + "']")).click();
				} else if (radioButton.containsKey(lastText)) {
					String value = radioButton.get(lastText);
					driver.findElement(By.xpath("//android.widget.TextView[@text='" + lastText
							+ "']/../following-sibling::android.widget.RadioGroup//android.widget.RadioButton[@text='"
							+ value + "']")).click();
				} else {
					System.out.println("element is not present");
				}
				break;
			}

		}
		System.out.println("enetering details completed");
		
	}

}