package default_package;
public class MainScript {
    public static void main(String[] args) {
        FirstScript first = new FirstScript();
        SecondScript second = new SecondScript();

        try {
            first.execute();
            second.execute();
        } finally {
            WebDriverSingleton.quitDriver(); // Close the browser after both scripts
        }
    }
}
