package Logics;

import java.io.IOException;

public class Snippet {
	public static void main(String[] args) {
		
	
	 String url = "https://mail.google.com/mail/u/0/#inbox";
	
	        
	        String profileDirectory = "Default"; // Replace this with your profile directory name
	
	        try {
	            // Use runtime to execute the command to open the URL in a new window with the specified profile
	            Runtime.getRuntime().exec(new String[]{"cmd", "/c", "start chrome --new-window --profile-directory=\"" + profileDirectory + "\" " + url});
	            System.out.println("Chrome launched with specified profile in a new window successfully.");
	        } catch (IOException e) {
	            e.printStackTrace();
	            System.out.println("Failed to launch Chrome with the specified profile.");
	        }
}
}

