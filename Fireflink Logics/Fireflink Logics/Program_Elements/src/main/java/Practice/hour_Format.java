package Practice;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class hour_Format {
    public static void main(String[] args) {
        String timeString = "15:54:30";
        
        // Define input and output time format
        SimpleDateFormat inputFormat = new SimpleDateFormat("HH:mm:ss");
        SimpleDateFormat outputFormat = new SimpleDateFormat("hh:mm:ss a");

        try {
            Date date = inputFormat.parse(timeString);
            String formattedTime = outputFormat.format(date);
            
            System.out.println("Time in 12-hour format: " + formattedTime);
        } catch (ParseException e) {
            System.out.println("Error parsing time: " + e.getMessage());
        }
    }
}
