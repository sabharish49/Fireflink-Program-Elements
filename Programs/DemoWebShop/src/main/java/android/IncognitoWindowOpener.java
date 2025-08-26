package android;
import java.awt.*;
import java.awt.event.KeyEvent;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class IncognitoWindowOpener {

    public static void main(String[] args) {
        try {
            // Start Google Chrome
            WebDriver driver = new ChromeDriver();

            // Wait for a brief moment for Chrome to start
            Thread.sleep(2000);

            // Use Robot class to send keyboard shortcut for opening incognito window
            Robot robot = new Robot();

            // Press Ctrl + Shift + N to open incognito window
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_SHIFT);
            robot.keyPress(KeyEvent.VK_N);

            // Release the keys
            robot.keyRelease(KeyEvent.VK_N);
            robot.keyRelease(KeyEvent.VK_SHIFT);
            robot.keyRelease(KeyEvent.VK_CONTROL);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
