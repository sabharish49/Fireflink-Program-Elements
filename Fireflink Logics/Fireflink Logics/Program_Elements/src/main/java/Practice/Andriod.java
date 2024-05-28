package Practice;

public class Andriod {
    public static void main(String[] args) {
        try {
            Runtime.getRuntime().exec("adb shell input keyevent KEYCODE_APP_SWITCH");
            Thread.sleep(1000);
            Runtime.getRuntime().exec("adb shell input keyevent KEYCODE_DPAD_CENTER");
            Runtime.getRuntime().exec("adb shell input keyevent KEYCODE_DPAD_CENTER");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
