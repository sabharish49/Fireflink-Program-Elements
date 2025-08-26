package Business_Logics;

import java.time.Duration;
import java.util.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;

import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import com.tyss.optimize.nlp.util.annotation.InputParam;
import com.tyss.optimize.nlp.util.annotation.InputParams;

import io.appium.java_client.android.AndroidDriver;

public class Swipe_till_element_visiable implements Nlp {

    @InputParams({
        @InputParam(name = "productXPath", type = "java.lang.String"),
        @InputParam(name = "targetXPath", type = "java.lang.String"),
        @InputParam(name = "maxSwipeCount", type = "java.lang.String")
    })
    @Override
    public List<String> getTestParameters() throws NlpException {
        return Arrays.asList("productXPath", "targetXPath", "maxSwipeCount");
    }

    @Override
    public StringBuilder getTestCode() throws NlpException {
        return new StringBuilder("Swipes through the screen to find the target element and collect product names.");
    }

    @Override
    public NlpResponseModel execute(NlpRequestModel nlpRequestModel) throws NlpException {
        NlpResponseModel nlpResponseModel = new NlpResponseModel();
        Map<String, Object> attributes = nlpRequestModel.getAttributes();

        String productXPath = (String) attributes.get("productXPath");
        String targetXPath = (String) attributes.get("targetXPath");
        String maxSwipe = (String) attributes.get("maxSwipeCount");
        int maxSwipeCount = Integer.parseInt(maxSwipe);

        AndroidDriver driver = nlpRequestModel.getAndroidDriver();
        Duration impWait = driver.manage().timeouts().getImplicitWaitTimeout();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));

        try {
            Dimension screenSize = driver.manage().window().getSize();
            int screenCenter = screenSize.getWidth() / 2;
            int startPoint = (int) (screenSize.getHeight() * 0.8);
            int endPoint = (int) (screenSize.getHeight() * 0.2);

            int swipeCount = 0;
            Set<String> productSet = new HashSet<>();
            boolean isTargetFound = false;
            boolean hasDuplicate = false;

            PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");

            while (!isTargetFound && swipeCount < maxSwipeCount && !hasDuplicate) {
                // Capture product names
                List<WebElement> products = driver.findElements(By.xpath(productXPath));
                for (WebElement product : products) {
                    String productName = product.getText();
                    if (!productSet.add(productName)) {
                        hasDuplicate = true;
                        System.out.println("Duplicate detected: " + productName);
                        break;
                    }
                }

                // Check if target element is visible
                if (!driver.findElements(By.xpath(targetXPath)).isEmpty()) {
                    isTargetFound = true;
                    System.out.println("Target element found. Stopping swipes.");
                    break;
                }

                // Perform swipe
                Sequence swipe = new Sequence(finger, 1);
                swipe.addAction(finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), screenCenter, startPoint));
                swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
                swipe.addAction(finger.createPointerMove(Duration.ofMillis(700), PointerInput.Origin.viewport(), screenCenter, endPoint));
                swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
                driver.perform(Arrays.asList(swipe));

                swipeCount++;
            }

            if (isTargetFound) {
                nlpResponseModel.setStatus(CommonConstants.pass);
                nlpResponseModel.setMessage("Target element found. Swiping stopped.");
            } else if (hasDuplicate) {
                nlpResponseModel.setStatus(CommonConstants.fail);
                nlpResponseModel.setMessage("Duplicate products detected. Swiping stopped.");
            } else {
                nlpResponseModel.setStatus(CommonConstants.fail);
                nlpResponseModel.setMessage("Max swipe count reached. Target element not found.");
            }

            System.out.println("Captured Products: " + productSet);

        } catch (Exception e) {
            nlpResponseModel.setStatus(CommonConstants.fail);
            nlpResponseModel.setMessage("Error occurred: " + e.getMessage());
        } finally {
            driver.manage().timeouts().implicitlyWait(impWait);
        }

        return nlpResponseModel;
    }
}
