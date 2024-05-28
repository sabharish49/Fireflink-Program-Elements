package Practice;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TimePicker {
	public static void main(String[] args) throws InterruptedException {
		String date= "7:30:am";
		String[] d=date.split(":");
		WebDriver driver = new ChromeDriver();
		driver.get("https://admin.stage.gfrsd.aws.direct.randstad.ca/");
		Thread.sleep(50000);
		driver.findElement(By.xpath("//label[text()='Start Time']/..//input")).click();
	    String hours = driver.findElement(By.xpath("//label[text()='Start Time']/following-sibling::div//div[@class='rdtCounter']//div")).getText();
	    String min = driver.findElement(By.xpath("//label[text()='Start Time']/following-sibling::div//div[@class='rdtCounter'][2]//div")).getText();
	    String format=driver.findElement(By.xpath("//label[text()='Start Time']/following-sibling::div//div[@class='rdtCounter'][3]//div")).getText();
	    int actualHour = Integer.parseInt(hours);
	    System.out.println(actualHour);
	    int actualMin= Integer.parseInt(min);
	    System.out.println(actualHour);
	    if (d.length >= 1) {   	
	    	String hour = d[0];
	    	int ExpectedHour = Integer.parseInt(hour);
	    	System.out.println(ExpectedHour);
	   
	    	if(actualHour<=ExpectedHour) {
	    		System.out.println("inside if");
	    		int i=1;
	         while(i<=12) {
	        	hours = driver.findElement(By.xpath("//label[text()='Start Time']/following-sibling::div//div[@class='rdtCounter']//div")).getText();
	     	    int currenthour = Integer.parseInt(hours);
	        	i++;
	     	    if(currenthour==ExpectedHour) {
	     	   System.out.println("break");
	     	    	break;
	     	    }
	     	    else {
	     	    	driver.findElement(By.xpath("//label[text()='Start Time']/following-sibling::div//div[@class='rdtCounter']//span[2]")).click();
		        	System.out.println("click successfull");
	     	    }
	         }
	    	}
	    	
		    else {
		    	int i=1;
		         while( i<=12) {
		        	hours = driver.findElement(By.xpath("//label[text()='Start Time']/following-sibling::div//div[@class='rdtCounter']//div")).getText();
		     	    int currenthour = Integer.parseInt(hours);
		        	i++;
		     	    if(currenthour==ExpectedHour) {
		     	    	System.out.println("break2");
		     	    	break;
		     	    }
		     	    else {
		     	    	driver.findElement(By.xpath("//label[text()='Start Time']/following-sibling::div//div[@class='rdtCounter']//span[2]")).click();
			        	System.out.println("click successfull");
		     	    }
		         }
		    	}
	    }
	
	    if (d.length >= 1) {   	
	    	String Min = d[1];
	    	int ExpectedMin = Integer.parseInt(Min);
	    	System.out.println(ExpectedMin);
	    	System.out.println(actualMin);
	    	if(actualMin<=ExpectedMin) {
	    		System.out.println("inside if");
	    		int i=1;
	         while(i<=12) {
	        	  min = driver.findElement(By.xpath("(//label[text()='Start Time']/following-sibling::div//div[@class='rdtCounter'])[2]//div")).getText();	    
	     	    int CurrentMin = Integer.parseInt(min);
	     	    System.out.println(CurrentMin);
	        	i++;      	
	     	    if(CurrentMin==ExpectedMin) {
	     	   System.out.println("break");
	     	    	break;
	     	    }
	     	    else {
	     	    	driver.findElement(By.xpath("//label[text()='Start Time']/following-sibling::div//div[@class='rdtCounter'][2]//span[2]")).click();
		        	System.out.println("click successfull");
	     	    }
	         }
	    	}
		    else {
		    	int i=1;
		         while( i<=12) {
		        	 min = driver.findElement(By.xpath("//label[text()='Start Time']/following-sibling::div//div[@class='rdtCounter'][2]//div")).getText();	    
		        	int CurrentMin = Integer.parseInt(min);
		        	i++;
		     	    if(CurrentMin==ExpectedMin) {
		     	    	System.out.println("break2");
		     	    	break;
		     	    }
		     	    else {
		     	    	driver.findElement(By.xpath("//label[text()='Start Time']/following-sibling::div//div[@class='rdtCounter'][2]//span")).click();
			        	System.out.println("click successfull");
		     	    }
		         }
		    	}
	    }
	    String expectedFormat  = d[2];
	    System.out.println(expectedFormat);
	    System.out.println(format);
	    if(format.equals(expectedFormat)) {
	    	System.out.println("matches");
	    	driver.findElement(By.xpath("//label[text()='Start Time']")).click();
	    }
	    else {
 	    	driver.findElement(By.xpath("//label[text()='Start Time']/following-sibling::div//div[@class='rdtCounter'][3]//span")).click();
 	    	driver.findElement(By.xpath("//label[text()='Start Time']")).click();
	    }
	   
	    
	 
	 
	}
}
