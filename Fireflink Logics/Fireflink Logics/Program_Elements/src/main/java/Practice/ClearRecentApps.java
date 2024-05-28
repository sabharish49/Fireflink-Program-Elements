package Practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ClearRecentApps {

    public static void main(String[] args) {
        try {
            Process process = Runtime.getRuntime().exec("adb shell dumpsys activity recents");
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            boolean inRecentTasksSection = false;
            while ((line = reader.readLine()) != null) {
                
                if (line.contains("Recent tasks:")) {
                    inRecentTasksSection = true;
                    continue;
                }              
                if (inRecentTasksSection && line.contains("RecentTaskInfo")) {
               
                    int startIndex = line.indexOf("baseIntent={act=android.intent.action.MAIN cmp=");
                    int endIndex = line.indexOf(" flags=");
                    if (startIndex != -1 && endIndex != -1) {
                        String packageName = line.substring(startIndex + 48, endIndex);
                        Runtime.getRuntime().exec("adb shell am task removeTask " + packageName);
                    }
                }
            }
            reader.close();
            process.waitFor();
            System.out.println("Recent apps cleared successfully.");

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
