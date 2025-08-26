package default_package;

import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SelectOptionalCoversAndHealthAddOns {

    // Method to handle clicks with retry (recursion) and log click count
    private static void clickElementWithRetry(WebDriver driver, WebElement element, JavascriptExecutor js, int maxAttempts) {
        int attemptCount = 0;
        boolean clicked = false;
        
        while (attemptCount < maxAttempts && !clicked) {
            try {
                js.executeScript("arguments[0].scrollIntoView(true);", element);  // Scroll to the element
                element.click();  // Try clicking on the element
                clicked = true;  // If successful, set clicked to true
                System.out.println("Clicked on element after " + (attemptCount + 1) + " attempt(s)");  // Log the number of attempts
            } catch (org.openqa.selenium.ElementClickInterceptedException e) {
                // If the click was intercepted, retry after a brief pause
                System.out.println("Click intercepted. Retrying...");
                try {
                    Thread.sleep(500);  // Wait a bit before retrying
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                attemptCount++;
            }
        }

        if (!clicked) {
            System.out.println("Failed to click after " + maxAttempts + " attempts.");
        }
    }

    public static void main(String[] args) {

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--disable-notifications");
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
        options.setExperimentalOption("useAutomationExtension", false);
        options.addArguments("--disable-gpu");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--proxy-server='direct://'");
        options.addArguments("--proxy-bypass-list=*");

        WebDriver driver = new ChromeDriver(options);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        try {
            driver.get("https://www.adityabirlacapital.com/healthinsurance/homepage");

            wait.until(webDriver -> ((JavascriptExecutor) webDriver)
                    .executeScript("return document.readyState").equals("complete"));

            driver.findElement(By.xpath("//a[@class=\"dropdown-toggle overlay-btn dropbtn menu-link menu-bar-link\"]")).click();
            driver.findElement(By.xpath("//A[normalize-space(.)='Health & Wellness plans']")).click();
            driver.findElement(By.xpath("//label[contains(text(),'Activ One NXT')]/following-sibling::span[text()='View Details']")).click();

            System.out.println("Execution started");

            WebElement buyNowButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Buy Now']")));
            buyNowButton.click();
            System.out.println("Clicked on buy now");

            String originalWindow = driver.getWindowHandle();

            wait.until(ExpectedConditions.numberOfWindowsToBe(2));

            Set<String> windowHandles = driver.getWindowHandles();
            for (String window : windowHandles) {
                if (!window.equals(originalWindow)) {
                    driver.switchTo().window(window);
                    break;
                }
            }

            wait.until(webDriver -> ((JavascriptExecutor) webDriver)
                    .executeScript("return document.readyState").equals("complete"));

            System.out.println("Execution started after switching");

            driver.findElement(By.xpath("(//span[text()='SELF'])[1]")).click();
            driver.findElement(By.xpath("(//input[@placeholder=\"Enter Age\"])[1]")).sendKeys("35");
            driver.findElement(By.xpath("//button[text()='Next']")).click();
            driver.findElement(By.xpath("//input[@formcontrolname=\"MobileNumber\"]")).sendKeys("9878787656");
            driver.findElement(By.xpath("//input[@formcontrolname=\"EmailID\"]")).sendKeys("jkhgfdgh@gmail.com");
            driver.findElement(By.xpath("//input[@formcontrolname=\"Pincode\"]")).sendKeys("400001");
            driver.findElement(By.xpath("//button[text()='View plans']")).click();

            wait.until(webDriver -> ((JavascriptExecutor) webDriver)
                    .executeScript("return document.readyState").equals("complete"));

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'more Covers')]")));
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(),'more Covers')]")));

            JavascriptExecutor js = (JavascriptExecutor) driver;
            WebElement moreCovers = driver.findElement(By.xpath("//span[contains(text(),'Click to view more Covers')]"));
            js.executeScript("arguments[0].scrollIntoView(true);", moreCovers);
            moreCovers.click();

            List<WebElement> removeElements = driver.findElements(By.xpath("//span[contains(text(),'Remove')]/following-sibling::input"));

            for (WebElement webElement : removeElements) {
                System.out.println(webElement);
                js.executeScript("arguments[0].scrollIntoView(true);", webElement);
                webElement.click();
            }

            String input = "Critical Illness Cover:Vaccine Cover:Personal Accident Cover";
            String[] addOns = input.split(":");

            List<WebElement> optionalCovers = driver.findElements(By.xpath(
                    "//h2[contains(text(),'Optional Covers')]/../following-sibling::div//h4 | " +
                    "//h2[contains(text(),'Health Add-Ons')]/../following-sibling::div//h4"));

            for (WebElement webElement : optionalCovers) {
                String text = webElement.getText();
                for (String value : addOns) {
                    if (value.equals(text)) {
                        WebElement checkbox = driver.findElement(By.xpath("//h4[text()='" + text + "']/../preceding-sibling::div//input"));
                        clickElementWithRetry(driver, checkbox, js, 3);  // Try clicking 3 times
                    }
                }
            }

            String autoCovers = "Super Credit:Claim Protect:Chronic Care:HLTH Meter";
            String[] autoSelectedCovers = autoCovers.split(":");
            for (String cover : autoSelectedCovers) {
                if (!input.contains(cover)) {  // Check if 'input' does not contain the cover
                    WebElement HLTHcheckbox = driver.findElement(By.xpath("//h4[contains(text(),'" + cover + "')]/../preceding-sibling::div//input"));
                    clickElementWithRetry(driver, HLTHcheckbox, js, 3);  // Try clicking 3 times
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
