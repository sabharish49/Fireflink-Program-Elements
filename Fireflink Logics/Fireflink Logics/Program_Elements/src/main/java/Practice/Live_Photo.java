package Practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;

public class Live_Photo {

    public static void main(String[] args) throws IOException, InterruptedException {
    	DesiredCapabilities dc=new DesiredCapabilities();
		dc.setCapability("noReset", "true");
		dc.setCapability("platformName", "Android");
		dc.setCapability("noReset", true);
		URL url = new URL("http://localhost:4723/wd/hub");
		AndroidDriver driver = new AndroidDriver(url, dc);
		Thread.sleep(10000);
        try {
            String[] cmd = {"xcrun", "simctl", "addphoto", "C2C451F0-9D33-4872-8615-4DE41D1A1017", "C:/Users/User/Downloads/Sample.jpg"};
            Process process = Runtime.getRuntime().exec(cmd);
            BufferedReader bufferedReader = new BufferedReader(new
                    InputStreamReader(process.getErrorStream()));

            String lsString;
            String output = "";
            while ((lsString = bufferedReader.readLine()) != null) {
                output += lsString;
            }
            System.out.println(output);
        } catch (Exception e) {
            e.printStackTrace();
        }
//        executeADBCommand("adb shell input keyevent KEYCODE_CAMERA");
//        Thread.sleep(5000);
//        executeADBCommand("adb pull /sdcard/DCIM/Camera/test.jpg C:/Users/User/Downloads/sample1.jpg");
 }

//    private static void executeADBCommand(String command) throws IOException {
//        ProcessBuilder processBuilder = new ProcessBuilder(command.split(" "));
//        processBuilder.redirectErrorStream(true);
//        Process process = processBuilder.start();
//        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
//        String line;
//        while ((line = reader.readLine()) != null) {
//            System.out.println(line);
//        }
//        try {
//            process.waitFor();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }
}
