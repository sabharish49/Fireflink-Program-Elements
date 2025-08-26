package default_package;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class date {

    public static void main(String[] args) {
        
        // Create a Scanner to take input from the user
        Scanner scanner = new Scanner(System.in);
        
        // Prompt the user to enter the number of days to add
        System.out.print("Enter the number of days to add to the current date: ");
        int daysToAdd = scanner.nextInt();
        
        // Calculate the future date
        LocalDate currentDate = LocalDate.now();
        LocalDate futureDate = currentDate.plusDays(daysToAdd);
        
        // Extract the day and abbreviated month (e.g., "SEP")
        int futureDay = futureDate.getDayOfMonth();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM");
        String futureMonth = futureDate.format(formatter).toUpperCase();
        
        // Display the future date
        System.out.println("Future Month: " + futureMonth);
        System.out.println("Future Day: " + futureDay);
        
        // Close the scanner to avoid resource leakage
        scanner.close();
    }
}








