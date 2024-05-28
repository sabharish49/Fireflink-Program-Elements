package Practice;

import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;

public class generate_random_pdf {
    public static void main(String[] args) {
        // Specify the path where you want to save the empty PDF file
    	String string = generateFixedString(4);
        String filePath = "C:\\Users\\User\\Downloads\\"+string+".pdf";
        try {
            PDDocument document = new PDDocument();
            document.save(filePath);
            document.close();
            System.out.println("Empty PDF file created successfully at: " + filePath);
        } catch (IOException e) {
            System.err.println("Error occurred while creating the PDF file: " + e.getMessage());
        }
        
    }
    public static String generateFixedString(int length) {
    	String Alphabets="ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvxyz";
    	   StringBuilder s = new StringBuilder(length);
    	   for (int i=0; i<length; i++) {
    		   int ch = (int)(Alphabets.length() * Math.random());
    		   s.append(Alphabets.charAt(ch));
    		  }
    		    return s.toString();
    }
}
