package logics;
import java.util.Random;
import java.util.Scanner;

public class RandomNumberGenerator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        // Ask the user for the number of digits
        System.out.print("Enter the number of digits: ");
        int numDigits = scanner.nextInt();

        // Generate the random number
        StringBuilder randomNumber = new StringBuilder();
        for (int i = 0; i < numDigits; i++) {
            int digit = random.nextInt(9) + 1; // Generates a number between 1 and 9
            randomNumber.append(digit);
        }

        // Print the random number
        System.out.println("Random Number: " + randomNumber);
    }
}
