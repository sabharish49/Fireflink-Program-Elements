package Practice;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Sample {
    public static void main(String[] args) throws InterruptedException {
        String date = "7:30:am";
        String[] d = date.split(":");
        WebDriver driver = new ChromeDriver();
        driver.get("https://admin.stage.gfrsd.aws.direct.randstad.ca/");
        Thread.sleep(50000);

        adjustTime(driver, "//label[text()='Start Time']/following-sibling::div//div[@class='rdtCounter']//div", d[0]);
        adjustTime(driver, "//label[text()='Start Time']/following-sibling::div//div[@class='rdtCounter'][2]//div", d[1]);

        // Close the browser
        driver.quit();
    }

    private static void adjustTime(WebDriver driver, String xpath, String expectedTime) {
        int expectedValue = Integer.parseInt(expectedTime);
        System.out.println("Expected: " + expectedValue);

        String current = driver.findElement(By.xpath(xpath)).getText();
        int actualValue = Integer.parseInt(current);
        System.out.println("Actual: " + actualValue);

        String arrowXPath = xpath + "//span[2]";

        if (actualValue <= expectedValue) {
            System.out.println("Adjusting up...");
            adjust(driver, xpath, arrowXPath, expectedValue, true);
        } else {
            System.out.println("Adjusting down...");
            adjust(driver, xpath, arrowXPath, expectedValue, false);
        }
    }

    private static void adjust(WebDriver driver, String xpath, String arrowXPath, int expectedValue, boolean increase) {
        int i = 1;
        do {
            driver.findElement(By.xpath(arrowXPath)).click();
            System.out.println("Click successful");

            String current = driver.findElement(By.xpath(xpath)).getText();
            int currentValue = Integer.parseInt(current);

            if (currentValue == expectedValue) {
                System.out.println("Break");
                break;
            }

            i++;
        } while (i <= 12);
    }
}
