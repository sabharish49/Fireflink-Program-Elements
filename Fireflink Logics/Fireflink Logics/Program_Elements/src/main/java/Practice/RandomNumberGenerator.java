package Practice;
import java.util.Random;

public class RandomNumberGenerator {
    public static void main(String[] args) {
        int min = 300000; 
        int max = 3500000;
        
        // Create a Random object
        Random random = new Random();

        int randomNumber = random.nextInt(max - min + 1) + min;
        System.out.println(randomNumber);
        }
}
