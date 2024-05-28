package Practice;

import java.io.FileWriter;
import java.io.IOException;

public class ExcelWriter {

    public static void main(String[] args) {
    	
       // String filePath = "C:/Users/User/Downloads/TestData123.csv"; // Replace this with your desired file path\
        
        String array="\"1 Open Vetic Application\r\n"
        		+ "\"\r\n"
        		+ "2 Verify the vetic logo is display\r\n"
        		+ "3 Tap on Shop icon\r\n"
        		+ "4 Verify that profile icon is display\r\n"
        		+ "5 Tap on Search for textfield\r\n"
        		+ "6 Verify that Trending Searches text is display\r\n"
        		+ "7  Tap on search textfield\r\n"
        		+ "8 Enter item in search textfield \r\n"
        		+ "9 Tap on first suggestion\r\n"
        		+ "10 Tap on filters\r\n"
        		+ "11 Verify that filter text is display\r\n"
        		+ "12 Select the options from pet type\r\n"
        		+ "13 Select the option from category\r\n"
        		+ "15 Tap on Apply button\r\n"
        		+ "16 Tap on Go To Cart button\r\n"
        		+ "17 Verify that Review Cart Text is display\r\n"
        		+ "18 Verify that product name  is display\r\n"
        		+ "19 Tap on place order button\r\n"
        		+ "20 Select the payment options\r\n"
        		+ "21 Verify that pay now button is display";
        
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
