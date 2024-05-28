package Practice;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringSplitter {
    public static void main(String[] args) {
        String input = "\"Authorization\":\"bearer eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJiWEdCZVVodTVpWDJ3R0R5RVlsT1NwUFYydlhTY1VhME4wMVI4ckR3aVVVIn0.eyJleHAiOjE3MDkyMzQwMjEsImlhdCI6MTcwOTIwNTIyMSwiYXV0aF90aW1lIjoxNzA5MjA1MjE5LCJqdGkiOiIxMDdhNGI2Mi0yY2E4LTQ4NDItOTUyOC1iMjJmNjE3MjI4ZGEiLCJpc3MiOiJodHRwczovL3N0YWdpbmctaWRlbnRpdHlzZXJ2aWNlLmRldi5waGFybWVhc3kuaW4vYXV0aC9yZWHM6Ly9xYS52YXVsdC5zY20uZ29tZXJjdXJ5LmluIl0sInJlYWxtX2FjY2VzcyI6eyJyb2xlcyKfDITg\"";

        // Define the regex pattern
        Pattern pattern = Pattern.compile("bearer\\s(.*?)\"");

        // Create a matcher with the input string
        Matcher matcher = pattern.matcher(input);

        // Check if the pattern is found
        if (matcher.find()) {
            // Extract the matched group
            String bearerToken = matcher.group(1);
            System.out.println(bearerToken);
        } else {
            System.out.println("Bearer token not found.");
        }
    }
}
