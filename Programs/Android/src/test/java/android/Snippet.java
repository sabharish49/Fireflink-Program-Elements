package android;

import io.appium.java_client.AppiumDriver;

public class Snippet {
	
	public static void main(String[] args) {
		
	
	 if (isKeyboardOpen(driver)) {
	                driver.hideKeyboard();
	                System.out.println("Keyboard was open and is now hidden.");
	            } else {
	                System.out.println("Keyboard was not open.");
	            }
	}
	private static boolean isKeyboardOpen(AppiumDriver<MobileElement> driver) {
	        try {
	            driver.hideKeyboard();
	            return true;  // Keyboard was open and is now hidden
	        } catch (Exception e) {
	            return false; // Keyboard was not open
	        }
	    }
	
	
	
	
	
	
}

