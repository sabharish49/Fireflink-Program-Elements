package default_package;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;

public class Questions {
    public static AndroidDriver driver;

    public static void swipe(AndroidDriver driver) {
        int screenWidth = driver.manage().window().getSize().getWidth();
        int screenHeight = driver.manage().window().getSize().getHeight();

        int startX = screenWidth / 2;
        int startY = (int) (screenHeight * 0.8);
        int endX = screenWidth / 2;
        int endY = (int) (screenHeight * 0.3);

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence swipe = new Sequence(finger, 1)
                .addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY))
                .addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                .addAction(finger.createPointerMove(Duration.ofMillis(1000), PointerInput.Origin.viewport(), endX, endY))
                .addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(Arrays.asList(swipe));
    }

    public static void selectOption(String field, String option) {
        driver.findElement(By.xpath("//android.widget.Button[contains(@content-desc,'" + field + "')]")).click();
        driver.findElement(By.xpath("//android.view.View[@content-desc='" + option + "']")).click();
    }

    public static void enterText(String fieldXPath, String text) {
        WebElement field = driver.findElement(By.xpath(fieldXPath));
        field.click();
        field.sendKeys(text);
    }

    public static void handleSpecificCase(String key) {
        switch (key) {
            case "Have you or any of the persons proposed for insurance, ever suffered from or taken treatment, or hospitalized for or have been recommended to take investigations / medication / surgery or undergone a surgery for medical conditions specified on Proposal form?":
                selectOption("Name of Disease", "Calculus - Renal/Urinary Tract");
                swipe(driver);
                selectOption("Operative status", "Operated");
                selectOption("Type of surgery", "Bilateral");
                selectOption("Treatment Status", "Cured");
                selectOption("Complication", "No");
                break;

            case "Is any of the insured pregnant currently? If yes, please mention expected date of delivery (EDD). Any history of pregnancy related complications?":
                enterText("//android.view.View[@content-desc='" + key + "']/android.view.View[3]/android.view.View/android.widget.EditText", "25-03-2025");
                break;

            case "Has any application you've made for life, health, or critical illness insurance ever been declined, postponed, loaded, or made subject to any special conditions by any insurance company?":
            case "Has any health or life insurance policy ever been terminated in the past?":
                enterText("//android.view.View[@content-desc='" + key + "']/android.view.View[3]/android.view.View/android.widget.EditText", "description");
                break;

            default:
                System.out.println("Unhandled key: " + key);
        }
        swipe(driver);
    }

    public static void main(String[] args) throws MalformedURLException {
    	DesiredCapabilities dc = new DesiredCapabilities();
        dc.setCapability("appium:platformName", "Android");
        URL url = new URL("http://localhost:4723/wd/hub");
        driver = new AndroidDriver(url, dc);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        System.out.println("app launched");
        
        LinkedHashMap<String, Object> orderedMap = new LinkedHashMap<>();
        Pattern pattern = Pattern.compile("\\\"([^\\\"]+)\\\":(\\[.*?\\]|\\\".*?\\\"),?");

        String jsonStr = "{\n"
                + "\"Have you or any of the persons proposed for insurance, ever suffered from or taken treatment, or hospitalized for or have been recommended to take investigations / medication / surgery or undergone a surgery for medical conditions specified on Proposal form?\":\"Yes\",\n"
                + "\"Any other illness/disease/injury/disability in the past other than childbirth, flu, or minor injuries, that has/have completely healed?\":\"Yes\",\n"
                + "\"Are you or any persons proposed on regular medication (including any Ayurvedic treatment) or awaiting any procedure/treatment?\":\"Yes\",\n"
                + "\"Have you ever been diagnosed with any of these medical conditions with or without any follow-up tests/medications? \u2014 Elevated Blood Sugar/ Diabetes/ Elevated Blood Pressure/ Hypertension/High Cholesterol/ Hypothyroidism?\":\"Yes\",\n"
                + "\"Is any of the insured pregnant currently? If yes, please mention expected date of delivery (EDD). Any history of pregnancy related complications?\":\"Yes\",\n"
                + "\"Has any application you've made for life, health, or critical illness insurance ever been declined, postponed, loaded, or made subject to any special conditions by any insurance company?\":\"Yes\",\n"
                + "\"Has any health or life insurance policy ever been terminated in the past?\":\"Yes\"\n"
                + "}";

        Matcher matcher = pattern.matcher(jsonStr);
        while (matcher.find()) {
            String key = matcher.group(1);
            String value = matcher.group(2);
            orderedMap.put(key, value);
        }

        List<String> nameList = Arrays.asList("Self", "Father");

        for (String name : nameList) {
            System.out.println("Processing for: " + name);

            for (Map.Entry<String, Object> entry : orderedMap.entrySet()) {
                String key = entry.getKey();
                driver.findElement(By.xpath("//android.view.View[@content-desc='" + name + "']/descendant::android.view.View[@content-desc='" + key + "']/android.view.View[@content-desc=\"Yes\"]/preceding-sibling::android.widget.RadioButton"))
                        .click();

                handleSpecificCase(key);
            }
        }
    }
}
