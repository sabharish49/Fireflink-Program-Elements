package Practice;

import java.time.Duration;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Pharmeasy {
	public static void main(String[] args) throws InterruptedException {
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://staging.gatekeeper.scm.gomercury.in/app/requests/all");
		Thread.sleep(10000);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		WebElement email = driver.findElement(By.xpath("//label[text()='Username or email']/following-sibling::input"));
		wait.until(ExpectedConditions.elementToBeClickable(email));
		email.sendKeys("sabarish.fireflink");
		WebElement password = driver.findElement(By.xpath("//input[@id='password']"));
		password.sendKeys("fireflink");
		driver.findElement(By.xpath("//input[@value=\"Sign In\"]")).click();
		Thread.sleep(20000);

		String query = "UPDATE retailer_credit_control SET credit_period = :Credit_Period, dp_updated_at= NOW() WHERE id = :retailer_credit_control_id;UPDATE slab_task_detail\r\n"
				+ "SET sgst=:sgst, cgst=:cgst, updated_on=NOW()\r\n"
				+ "WHERE id = :slab_task_detail_id;UPDATE charges\r\n"
				+ "SET partner_name= :partner_name, doc_number= :doc_number, updated_on= NOW()\r\n"
				+ "WHERE id= :charges_";

		driver.findElement(By.xpath("//mat-label[.='Query']/following-sibling::textarea")).sendKeys(query);
		Thread.sleep(2000);
		driver.findElement(By.xpath("//h2[.='Set Parameters']")).click();

		ArrayList<String> list = new ArrayList();
		Pattern pattern = Pattern.compile(":\\w+");
		Matcher matcher = pattern.matcher(query);
		System.out.println("Placeholders found in the SQL query:");
		while (matcher.find()) {
			String placeholder = matcher.group().replaceAll(":", "");
			System.out.println(placeholder + ":" + placeholder.length());
			list.add(placeholder);
		}

		WebElement element = null;
		String parameter = null;
		boolean res = false;

		Actions action = new Actions(driver);

		String regex = "Integer_Array_Null;GT=6:Float_Array;LT=6,GT=3:String_Null;REGEX=hello:ENUM_Array_Null;IN=12:ENUM;IN=17:String_Array_Null;REGEX=helloworld:Date_Null;BEFORE=12,AFTER=2:Integer_Array;LT=7,GT=2";

		String[] split1 = regex.split(":");

		for (int i = 0; i < list.size(); i++) {
			parameter = list.get(i);
			element = driver.findElement(By.xpath("//mat-label[text()='" + parameter
					+ "']/following-sibling::mat-form-field//mat-label[text()='Field Type']"));

			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].scrollIntoView(false);", element);

			action.click(element).build().perform();

			String combination = split1[i];

			selectDataType(driver, combination, parameter);

			System.out.println("clicked on :" + parameter);

		}

		Thread.sleep(5000);
		// driver.quit();

	}

	public static void selectDataType(WebDriver driver, String combination, String parameter) {
		Actions action = new Actions(driver);
		boolean res = false;
		try {
			String[] split2 = null;
			String datatypeComb = null;
			String datatype = null;
			try {
				split2 = combination.split(";");
				datatypeComb = split2[0];

			} 
			catch (Exception e) {
				datatypeComb = combination;
			}

			try {
				String[] splitToggle = datatypeComb.split("_");
				datatype = splitToggle[0];

				res = false;
				while (res == false) {
					if (driver.findElement(By.xpath("//mat-option[contains(@class,'active')]//span")).getText()
							.contains(datatype)) {
						action.sendKeys(Keys.ENTER).build().perform();
						res = true;
					} else {
						Thread.sleep(500);
						action.sendKeys(Keys.ARROW_DOWN).build().perform();
					}

				}

				for (int i = 1; i <= splitToggle.length; i++) {
					try {
						System.out.println("entered inside toggle");
						Thread.sleep(500);
						if (splitToggle[i].equals("Array")) {
							System.out.println("about to click arr");
							driver.findElement(By.xpath(
									"(//mat-label[text()='" + parameter + "']/following-sibling::mat-slide-toggle)[1]"))
									.click();
						}
						if (splitToggle[i].equals("Null")) {
							System.out.println("about to click null");
							driver.findElement(By.xpath(
									"(//mat-label[text()='" + parameter + "']/following-sibling::mat-slide-toggle)[2]"))
									.click();
						}
					} catch (Exception e) {
						// datatype = splitToggle[0];
					}
				}

			} catch (Exception e) {
				datatype = combination;
				selectOnlyDataTypeWithNoAssertion(datatype, driver);
			}
			
			String[] split3 = null;
			String[] split4 = null;
			String operator = null;
			String value = null;
			try {
				split3 = split2[1].split(",");
			} catch (Exception e) {

				split4 = split2[1].split("=");
				operator = split4[0];
				value = split4[1];
				setSingleAssertion(driver, operator, value);

			}

			driver.findElement(By.xpath(
					"//mat-label[text()='" + parameter + "']/following-sibling::div//mat-icon[text()='library_add']"))
					.click();

			for (int k = 1; k <= split3.length; k++) {
				split4 = split3[k - 1].split("=");
				operator = split4[0];
				value = split4[1];

				driver.findElement(
						By.xpath("(//mat-label[text()='Operator'])[" + k + "]/following-sibling::mat-form-field"))
						.click();
				res = false;
				while (res == false) {
					if (driver.findElement(By.xpath("//mat-option[contains(@class,'active')]//span")).getText()
							.contains(operator)) {
						action.sendKeys(Keys.ENTER).build().perform();
						driver.findElement(By.xpath(
								"(//mat-label[text()='Value'])[" + k + "]/following-sibling::mat-form-field//input"))
								.sendKeys(value);
						res = true;
					} else {
						Thread.sleep(500);
						action.sendKeys(Keys.ARROW_DOWN).build().perform();
					}
				}
				Thread.sleep(500);
				if (k != split3.length) {
					driver.findElement(By.xpath("//span[text()='Add']")).click();
				}

			}

			driver.findElement(By.xpath("//span[text()='Submit']")).click();

		
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	public static void selectOnlyDataTypeWithNoAssertion(String datatype, WebDriver driver)
			throws InterruptedException {

		boolean res = false;
		Actions action = new Actions(driver);
		while (res == false) {
			if (driver.findElement(By.xpath("//mat-option[contains(@class,'active')]//span")).getText()
					.contains(datatype)) {
				action.sendKeys(Keys.ENTER).build().perform();
				res = true;
			} else {
				Thread.sleep(500);
				action.sendKeys(Keys.ARROW_DOWN).build().perform();
			}
		}

	}
	public static void setSingleAssertion(WebDriver driver, String operator, String value) throws InterruptedException {
		boolean res = false;
		Actions action = new Actions(driver);

		driver.findElement(By.xpath("(//mat-label[text()='Operator'])[1]/following-sibling::mat-form-field")).click();
		while (res == false) {
			if (driver.findElement(By.xpath("//mat-option[contains(@class,'active')]//span")).getText()
					.contains(operator)) {
				action.sendKeys(Keys.ENTER).build().perform();
				driver.findElement(
						By.xpath("(//mat-label[text()='Value'])[1]/following-sibling::mat-form-field//input"))
						.sendKeys(value);
				res = true;
			} else {
				Thread.sleep(500);
				action.sendKeys(Keys.ARROW_DOWN).build().perform();
			}
		}
		Thread.sleep(500);

		driver.findElement(By.xpath("//span[text()='Submit']")).click();
	}


}