package business_logics;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.batik.transcoder.TranscoderException;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.image.PNGTranscoder;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TXReport {

	public static void main(String[] args) throws InterruptedException, MalformedURLException, IOException, TranscoderException {
		  File tempFile = File.createTempFile("orthoteeth", ".png");
		 String s = tempFile.getAbsolutePath();
		  System.out.println(s);
		
	WebDriver driver=new ChromeDriver();
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
	driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
	WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(30));
	driver.navigate().to("https://p1pro.orthofx.com/tx-report?pId=12736&pN=Patient%20POC9&pPT=EXPRESS_PRO&url=P_12736%2FTREATMENT_REPORT_1723097282163.json&plmr=FX_TETRA&dNS=UNIVERSAL");
	
	Thread.sleep(5000);
	
	driver.findElement(By.xpath("//input[@name=\"username\"]")).sendKeys("sujith1984g+fireflinkdoc@gmail.com");
	driver.findElement(By.xpath("//input[@name=\"password\"]")).sendKeys("OrthoFX1");
	driver.findElement(By.xpath("//button[text()=\"LOG IN\"]")).click();
	System.out.println("Login successfully");
	
	WebElement spinner=driver.findElement(By.xpath("//div[@style=\"display: block;\"]//mat-spinner"));
	wait.until(ExpectedConditions.invisibilityOf(spinner));
	
	driver.findElement(By.xpath("//mat-cell[text()=\"OFX - Fireflink - Fremont\"]/..//button[text()=\"Select\"]\r\n"
			+ "")).click();
	
	wait.until(ExpectedConditions.invisibilityOf(spinner));
	driver.findElement(By.xpath("//input[@placeholder=\"Search for Patient Name, ID\"]")).sendKeys("12736");
	driver.findElement(By.xpath("//button[@class=\"cta-primary search-btn align-items-center\"]")).click();
	
	driver.findElement(By.xpath("//span[contains(@class,\"patient-id\") and text()='12736']\r\n"
			+ "")).click();
	
	System.out.println("Searched done");
	
	driver.findElement(By.xpath("//button[@class=\"mat-tooltip-trigger cta-link-sm\"]")).click();
	
	System.out.println("Fx Opened successfully");
	
    Set<String> allWindowHandles = driver.getWindowHandles();
    System.out.println(allWindowHandles.size());
    for (String handle : allWindowHandles) {
    	driver.switchTo().window(handle);
        if (driver.getCurrentUrl().contains("https://p1pro.orthofx.com/professional/doctor/3dworkspace-ofx")) 
        {
        	Thread.sleep(20000);
         wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class=\"tab report\"]"))).click();
         System.out.println("Successfully Cliecked on TX Report");
        }
			
	}
    
    Set<String> txWindowhandles = driver.getWindowHandles();
    System.out.println(allWindowHandles.size());
    for (String handle : txWindowhandles) {
    	driver.switchTo().window(handle);
        if (driver.getCurrentUrl().contains("https://p1pro.orthofx.com/tx-report")) 
        {
        	 String text=driver.findElement(By.xpath("//div[@class=\"title\"]//h1")).getText();
        	    System.out.println(text);	
        }
			
	}
    
    
    //System.out.println("X Cor "+ x + " Y cor "+y);
    WebElement imgElement=driver.findElement(By.xpath("//img[@class='attachment']"));
    JavascriptExecutor js = (JavascriptExecutor) driver;
    String contentValue = (String) js.executeScript(
            "var ele = arguments[0];" +
            "var style = window.getComputedStyle(ele);" +
            "return style.getPropertyValue('content');", 
            imgElement
        );
    		
    String regex = "url\\(\"([^\"]*)\"\\)";
    
    // Compile the regex pattern
    Pattern pattern = Pattern.compile(regex);
    
    // Create a matcher for the input string
    Matcher matcher = pattern.matcher(contentValue);
    String url = "";
    // Find and print all matches
    while (matcher.find()) {
        // Extract the URL (group 1 in the pattern)
         url = matcher.group(1);
        System.out.println("Extracted URL: " + url);
    }	
  //  File  elementOutput = new File("C:\\Users\\User\\Desktop\\elementScreenShot.png");
    convertSvgToPng(url, "C:\\Users\\User\\Desktop\\elementScreenShot.png");
		
	}
	public static void convertSvgToPng(String svgUrl, String outputPath) throws TranscoderException, IOException {
        // Create a PNG Transcoder
        PNGTranscoder transcoder = new PNGTranscoder();

        // Create the TranscoderInput
        InputStream inputStream = new URL(svgUrl).openStream();
        TranscoderInput input = new TranscoderInput(inputStream);

        // Create the TranscoderOutput
        FileOutputStream outputStream = new FileOutputStream(outputPath);
        TranscoderOutput output = new TranscoderOutput(outputStream);

        // Convert the SVG to PNG
        transcoder.transcode(input, output);

        // Close the streams
        inputStream.close();
        outputStream.close();
    }
	}
