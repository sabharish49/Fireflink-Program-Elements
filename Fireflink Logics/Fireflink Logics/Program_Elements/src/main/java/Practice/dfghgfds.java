package Practice;

import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

class dfghgfds {
	
	public static int integerValidation(String conditions) throws InterruptedException {
		
		String con = conditions;
		ArrayList<Integer> inValues = new ArrayList<Integer>();
		ArrayList<Integer> ninValues = new ArrayList<Integer>();
		int ActualNumber = 0;
		int ltValue = 0;
		int lteValue = 0;
		int gtValue = 0;
		int gteValue = 0;
		Pattern pattern = Pattern.compile("([A-Z]+) \\(([^)]+)\\)");
		Matcher matcher = pattern.matcher(con);
		while (matcher.find()) {
			String condition = matcher.group(1);
			System.out.println(condition);
			String valuesStr = matcher.group(2);
			System.out.println(valuesStr);
			String[] values = valuesStr.split(",");
			for (String value : values) {
				int intValue = Integer.parseInt(value.trim());
				switch (condition) {
				case "IN":
					inValues.add(intValue);
					break;
				case "NIN":
					ninValues.add(intValue);
					break;
				case "LT":
					ltValue = intValue;
					break;
				case "LTE":
					lteValue = intValue;
					break;
				case "GT":
					gtValue = intValue;
					break;
				case "GTE":
					gteValue = intValue;
					break;
				}
			}

		}
		
		System.out.println(inValues);
		if (!(inValues.isEmpty())) {
			ActualNumber = inValues.get(0);
			System.out.println(ActualNumber);
		}
		else if ((gtValue != 0 && ltValue != 0) && (gtValue < ltValue)) {
			ActualNumber = gtValue + 1;
		} else if (gtValue != 0 && ltValue == 0) {
			ActualNumber = gtValue + 1;
		} else if (ltValue != 0 && gtValue == 0) {
			ActualNumber = ltValue - 1;
		}
		else if ((gteValue != 0 && lteValue != 0) && (gteValue < lteValue)) {
			ActualNumber = gteValue + 1;
		} else if (gteValue != 0 && lteValue == 0) {
			ActualNumber = gteValue + 1;
		} else if (lteValue != 0 && gteValue == 0) {
			ActualNumber = lteValue - 1;
		} 
		if (!ninValues.contains(ActualNumber))
			System.out.println("ActualNumber :" + ActualNumber);
		return ActualNumber;
	}
	
	public static double floatValidation(String conditions) {
	    String con = conditions;
	    ArrayList<Double> inValues = new ArrayList<Double>();
	    ArrayList<Double> ninValues = new ArrayList<Double>();
	    double actualNumber = 0.0;
	    double ltValue = 0.0;
	    double lteValue = 0.0;
	    double gtValue = 0.0;
	    double gteValue = 0.0;
	    Pattern pattern = Pattern.compile("([A-Z]+) \\(([^)]+)\\)");
	    Matcher matcher = pattern.matcher(con);
	    while (matcher.find()) {
	        String condition = matcher.group(1);
	        System.out.println(condition);
	        String valuesStr = matcher.group(2);
	        System.out.println(valuesStr);
	        String[] values = valuesStr.split(",");
	        for (String value : values) {
	            double doubleValue = Double.parseDouble(value.trim());
	            switch (condition) {
	                case "IN":
	                    inValues.add(doubleValue);
	                    break;
	                case "NIN":
	                    ninValues.add(doubleValue);
	                    break;
	                case "LT":
	                    ltValue = doubleValue;
	                    break;
	                case "LTE":
	                    lteValue = doubleValue;
	                    break;
	                case "GT":
	                    gtValue = doubleValue;
	                    break;
	                case "GTE":
	                    gteValue = doubleValue;
	                    break;
	            }
	        }
	    }

	    System.out.println(inValues);
	    if (!inValues.isEmpty()) {
	        actualNumber = inValues.get(0);
	        System.out.println(actualNumber);
	    } else if (gtValue != 0 && ltValue != 0 && gtValue < ltValue) {
	        actualNumber = gtValue + 1;
	    } else if (gtValue != 0 && ltValue == 0) {
	        actualNumber = gtValue + 1;
	    } else if (ltValue != 0 && gtValue == 0) {
	        actualNumber = ltValue - 1;
	    } else if (gteValue != 0 && lteValue != 0 && gteValue < lteValue) {
	        actualNumber = gteValue + 1;
	    } else if (gteValue != 0 && lteValue == 0) {
	        actualNumber = gteValue + 1;
	    } else if (lteValue != 0 && gteValue == 0) {
	        actualNumber = lteValue - 1;
	    }
	    if (!ninValues.contains(actualNumber)) {
	        System.out.println("ActualNumber: " + actualNumber);
	    }
	    return actualNumber;
	}

	public static String stringValidation(String conditions) {
		String input = conditions;
		String output = "";
		Pattern pattern = Pattern.compile("\\(([^)]+)\\)");
		Matcher matcher = pattern.matcher(input);
		if (matcher.find()) {
			System.out.println(matcher.group(1));
			output = matcher.group(1);
		} else {
			throw new IllegalArgumentException("No value found inside parentheses.");
		}
 
		return output;
	}

	public static LocalDate dateValidation(String conditions) {
		String con = conditions;
		LocalDate resultDate =null;
		Pattern pattern = Pattern.compile("([A-Z]+) \\(([^)]+)\\)");
		Matcher matcher = pattern.matcher(con);
		while (matcher.find()) {
			String condition = matcher.group(1);
			System.out.println(condition);
			String valuesStr = matcher.group(2);
			System.out.println(valuesStr);
			String[] values = valuesStr.split(",");

			for (String value : values) {
				int intValue = Integer.parseInt(value.trim());
				switch (condition) {
				case "AFTER":
					 resultDate = LocalDate.now().plusDays(intValue);
					System.out.println(resultDate);
					break;
				case "BEFORE":
					resultDate = LocalDate.now().plusDays(intValue);
					System.out.println(resultDate);
					break;
				}
			}
		}
		return resultDate;
	}
	
	public static int enumValidation(String conditions) {
		String con = conditions;
		ArrayList<Integer> inValues = new ArrayList<Integer>();
		int ActualNumber = 0;
		Pattern pattern = Pattern.compile("([A-Z]+) \\(([^)]+)\\)");
		Matcher matcher = pattern.matcher(con);
		while (matcher.find()) {
			String condition = matcher.group(1);
			System.out.println(condition);
			String valuesStr = matcher.group(2);
			System.out.println(valuesStr);
			String[] values = valuesStr.split(",");

			for (String value : values) {
				int intValue = Integer.parseInt(value.trim());
				switch (condition) {
				case "IN":
					inValues.add(intValue);
					break;
				}
			}
		}
		if (!(inValues.isEmpty())) {
			ActualNumber = inValues.get(0);
			System.out.println(ActualNumber);
		}
		return ActualNumber;
	}

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

		List<WebElement> elements = driver
				.findElements(By.xpath("//mat-label[contains(text(),'Type')]/../following-sibling::div//mat-label[2]"));
		List<WebElement> validations = driver
				.findElements(By.xpath("//mat-label[contains(text(),'Type')]/../following-sibling::div//mat-label[5]"));
		ArrayList<String> datatypes = new ArrayList();
		ArrayList<String> conditions = new ArrayList<String>();
		for (WebElement e : elements) {
			String datatype = e.getText();
			datatypes.add(datatype);
		}

		System.out.println(datatypes);
		for (WebElement e : validations) {
			String condition = e.getText();
			conditions.add(condition);
		}
		System.out.println(validations);
		int value=0;
		double floatValue;
		String value1=null;
		LocalDate date=null;
		
		for (WebElement e : elements) {
			for (WebElement ele : validations) {
				
			}
		}
		
		int k=1;
		System.out.println(datatypes.size());
		for (int i = 0; i < datatypes.size(); i++) {
			System.out.println("i titeration"+i);
			switch (datatypes.get(i)) {
			case "INTEGER":
				value = integerValidation(conditions.get(i));
				driver.findElement(By.xpath("//mat-label[contains(text(),'Type')]/../following-sibling::div["+k+"]//input")).sendKeys(value+"");
                 k++;
				break;
				
			case "FLOAT":
				floatValue = floatValidation(conditions.get(i));
				
				 driver.findElement(By.xpath("//mat-label[contains(text(),'Type')]/../following-sibling::div["+k+"]//input")).sendKeys(floatValue+"");
                 k++;
				break;
							
			case "STRING":
				value1 = stringValidation(conditions.get(i));
				driver.findElement(By.xpath("//mat-label[contains(text(),'Type')]/../following-sibling::div["+k+"]//input")).sendKeys(value1+"");
                  k++;
				break;
			case "ENUM":
				value = enumValidation(conditions.get(i));
				driver.findElement(By.xpath("//mat-label[contains(text(),'Type')]/../following-sibling::div["+k+"]//input")).sendKeys(value+"");
                k++;
				break;
			case "DATE" :
				date=dateValidation(conditions.get(i));
				driver.findElement(By.xpath("//mat-label[contains(text(),'Type')]/../following-sibling::div["+k+"]//input")).sendKeys(date+"");
                k++;
				break;
			case "DATETIME" :
				date=dateValidation(conditions.get(i));
				driver.findElement(By.xpath("//mat-label[contains(text(),'Type')]/../following-sibling::div["+k+"]//input")).sendKeys(date+"");
                 k++;
				break;							
				
			}	
			
			
		}

	}

}