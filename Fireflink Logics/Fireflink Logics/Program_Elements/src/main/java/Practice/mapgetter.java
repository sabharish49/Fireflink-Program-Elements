package Practice;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class mapgetter {
    public static void main(String[] args) {
        // Your input string
        String input = "{TC_ID=CR001, "
                + "TC_Name=Verify user is able to create a new request with a single query and database, "
                + "Title=FF Query\\E9, Queries_Count=1,"
                + " Query1=vault,vault_credit_notes:UPDATE slab_task_detail SET net_sale_amount = 200.5,  discount_percent = 10 ,updated_on = now() WHERE slab_task_id = 12 AND client = 'B2C' AND partner_id = '1235';, Request_Status_Verification=PENDING}\r\n";
        String query1Value = extractQuery1Value(input);
        System.out.println("Value of Query1: " + query1Value);
    }

    private static String extractQuery1Value(String input) {
        Pattern pattern = Pattern.compile("Query1=.*?:(.*?;)");

        // Create a matcher for the input string
        Matcher matcher = pattern.matcher(input);

        // Find the Query1 value
        if (matcher.find()) {
            return matcher.group(1).trim();
        }
        return null;
    }
}
