package Practice;

public class switch_to_recent_tyoe {
    public static void main(String[] args) {
        String packageName = "com.facebook.katana";
        String activityName = "com.facebook.katana.LoginActivity";

        // Create the command string
        String command = String.format("adb shell am start -a android.intent.action.MAIN -c android.intent.category.LAUNCHER -n %s/%s", packageName, activityName);
        try {
            Runtime.getRuntime().exec(command);
            System.out.println("Command executed successfully.");
        } catch (Exception e) {
            System.out.println("Error executing command: " + e.getMessage());
        }
    }
}
