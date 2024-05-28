package Practice;

public class NumberVerification {
    
    public static String verifyNumber(int number) {
        if (number > 0) {
            return "Positive";
        } else if (number < 0) {
            return "Negative";
        } else {
            return "Zero";
        }
    }
   
    public static void main(String[] args) {
        // Example usage:
        int testNumber = -50; // Replace with the actual number you want to verify

        String result = verifyNumber(testNumber);
        System.out.println("The number " + testNumber + " is " + result + ".");
    }
}
