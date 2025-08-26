package ActionClass;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import javax.swing.JFrame;
public class SetSize {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		WebDriver driver= new ChromeDriver();
//		driver.manage().window().maximize();
//		driver.get("https://staging.gatekeeper.scm.gomercury.in/app/requests/all");
//			
		
		       // JFrame frame = new JFrame("Resizable Window");
//		        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		        frame.setSize(400, 300); // initial size
//		        frame.setResizable(true); // allow resizing
//		        frame.setLocation(100, 100); // set position
//		        
//		        frame.setVisible(true);
		        WebDriver driver= new ChromeDriver();
				driver.manage().window().maximize();
				driver.get("https://staging.gatekeeper.scm.gomercury.in/app/requests/all");
					System.out.println(driver.manage().window().getPosition());
					
					//driver.manage().window().setPosition(p);
		    }
		

			

	}


