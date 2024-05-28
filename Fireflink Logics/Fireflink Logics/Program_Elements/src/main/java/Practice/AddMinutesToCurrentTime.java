package Practice;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class AddMinutesToCurrentTime {
    public static void main(String[] args) {
   
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of minutes to add: ");
        int minutesToAdd = scanner.nextInt();
        LocalDateTime currentTime = LocalDateTime.now();
        LocalDateTime newTime = currentTime.plusMinutes(minutesToAdd);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        String formattedNewTime = newTime.format(formatter);
        System.out.println("New Time after adding " + minutesToAdd + " minutes: " + formattedNewTime);
    }
}