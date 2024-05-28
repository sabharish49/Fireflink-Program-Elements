package Practice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Query_Iterate {
    public static void main(String[] args) throws InterruptedException {
    	ChromeDriver driver=new ChromeDriver();
    	driver.get("https://staging.gatekeeper.scm.gomercury.in/app/requests/all");
    	driver.manage().window().maximize();
    	Scanner sc=new Scanner(System.in);
    	int key = sc.nextInt();
    	
    	switch (key) {
		case 1:
			 String parameter = "{TC_ID=CR002, TC_Name=Verify user is able create new request with multiple queries and databases, Title=FF Query1!z, Queries_Count=2, Query1=vault:UPDATE slab_task_detail SET net_sale_amount = 200.5 , discount_percent = 10, updated_on = now() WHERE slab_task_id = 12 AND client = 'B2C' AND partner_id = '1235';, Query2=vault,vault_credit_notes:UPDATE slab_task_detail SET net_sale_amount = 200.5 , discount_percent = 10, updated_on = now() WHERE slab_task_id = 12 AND client = 'B2C' AND partner_id = '1235';, Request_Status_Verification=REJECTED}";
		        Map<String, String> paramMap = convertStringToMap(parameter);
		        int Queries_Count = Integer.parseInt(paramMap.get("Queries_Count"));
		       // String dataBase = paramMap.get("Databases");	        
		        System.out.println("save button found");
		        WebElement Addquery = driver.findElement(By.xpath("//button[contains(text(),'Add Query')]"));
		        System.out.println("addquery button found");
		              
		        for (int i = 1; i <= Queries_Count; i++) {		        
		        	WebElement SelectDataBase = driver.findElement(By.xpath("(//div[@class='select-input selectbox multiple']//input[@placeholder='Select Databases'])["+i+"]"));	        	
		            WebElement QueryTextField = driver.findElement(By.xpath("(//textarea[contains(@placeholder,'Enter your sql queries eg:')])["+i+"]"));
		            System.out.println("queryTextfield button ");
		            JavascriptExecutor js = (JavascriptExecutor) driver;   
					js.executeScript("arguments[0].scrollIntoView(false);", QueryTextField);
		            String queryKey = "Query"+i;    
		            String DBname = paramMap.get(queryKey);	
		            System.out.println();
		            Pattern pattern = Pattern.compile(""+queryKey+"=.*?:(.*?;)");
		            Matcher matcher = pattern.matcher(parameter);
		            String queryValue="";
		            if (matcher.find()) {
		            	queryValue  = matcher.group(1).trim();
		            }
		
		
		            List<String> resultList = storeSubstringBeforeColon(DBname);
		            System.out.println("Result List: " + resultList);
		            List DBList = new ArrayList();		        
			        for (String value : resultList) {
			            DBList.add(value.trim()); 
			        }
			        int DBsize = DBList.size();
		            System.out.println("Result List: " + resultList);
		            SelectDataBase.click();
		            if(DBsize>=1) {
		            	for(int k=0;k<DBsize;k++) {
		            		System.out.println(DBList.get(k));
		            		driver.findElement(By.xpath("//span[contains(text(),'"+DBList.get(k)+"')]")).click();
		            	}
		            }
		            
		            WebElement SaveButton = driver.findElement(By.xpath("//button[text()='Save']"));
		            SaveButton.click();
		            System.out.println("click on savebutton");
		           Thread.sleep(2000);
		            QueryTextField.sendKeys(queryValue);
		            System.out.println("result"+queryValue);
		            if(i<Queries_Count) {
		            Addquery.click();
		            }
		        }
		    
			break;

		default:
			break;
		}
    }
   
    private static Map<String, String> convertStringToMap(String parameter) {
        Map paramMap = new HashMap();
        String[] pairs = parameter.replaceAll("[{}]", "").split(", ");
        for (String pair : pairs) {
            String[] keyValue = pair.split("=", 2);
            paramMap.put(keyValue[0], keyValue[1]);
        }

        return paramMap;
    } 
    public static List<String> storeSubstringBeforeColon(String input) {
        List<String> resultList = new ArrayList<>();
        int colonIndex = input.indexOf(':');
        if (colonIndex != -1) {
            String substringBeforeColon = input.substring(0, colonIndex).trim();
            
            String[] parts = substringBeforeColon.split(",");

            for (String part : parts) {
                String trimmedPart = part.trim();
                if (!trimmedPart.isEmpty()) {
                    resultList.add(trimmedPart);
                }
            }
        }

        return resultList;
    }
    public static String extractSubstringAfterColon(String input) {
        int colonIndex = input.indexOf(':');
        if (colonIndex != -1) {
            System.out.println( input.substring(colonIndex + 1).trim());
            return input.substring(colonIndex + 1).trim();
        }

        return "";
    }

}


