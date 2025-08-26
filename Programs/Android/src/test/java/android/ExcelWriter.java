package android;

import java.io.FileWriter;
import java.io.IOException;

public class ExcelWriter {

    public static void main(String[] args) {
    	
       // String filePath = "C:/Users/User/Downloads/TestData123.csv"; // Replace this with your desired file path\
        
        String array="61.Click on Edit button\r\n"
        		+ "62.Upload the required file\r\n"
        		+ "63.Click on Send to supervisor button\r\n"
        		+ "64.Verify tthe success message\r\n"
        		+ "65.Logout from the application and Login to application as Supervisor\r\n"
        		+ "66.Click on Claims link\r\n"
        		+ "67.Click on the LAN status\r\n"
        		+ "68.Click on Edit  Button\r\n"
        		+ "69.Approve the documents\r\n"
        		+ "70.Click on send to Finhaat Button\r\n"
        		+ "71.verify the sucvcess message";
        
        String[] stepsArray = array.split("\\n");

        for (int i = 0; i < stepsArray.length; i++) {
        	String con = stepsArray[i].replaceAll("[^a-zA-Z ]", "");  	
            System.out.println("user should be able to "+con);
        }       
        System.out.println("test case---------------------------------------------------------------------------------------------------");
        for (int i = 0; i < stepsArray.length; i++) {
            System.out.print(stepsArray[i]);
        }
//        Test data
//        String[] testData = {
//                "First name:goutham",
//                "Middle name:roy",
//                "Last name:P",
//                "Pincode:313332",
//                "Village:Bagdola",
//                "Employment type:Self employed non Profession",
//                "Industry categyory:Agri-Rice mill",
//                "Lead source:Marketing Activity",
//                "Customer feedback:Interested"
//        };

//        try (FileWriter writer = new FileWriter(filePath)) {
//            // Write test data
//            for (String data : stepsArray) {
//                writer.append(data).append("\n");
//            }
//            
//            writer.flush();
//            System.out.print("CSV file created successfully at:s ," + filePath);
            
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}
