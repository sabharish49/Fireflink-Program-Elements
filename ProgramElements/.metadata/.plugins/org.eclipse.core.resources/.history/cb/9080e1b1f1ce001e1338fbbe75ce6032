package bussiness_logic;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import com.tyss.optimize.nlp.util.annotation.InputParam;
import com.tyss.optimize.nlp.util.annotation.InputParams;
import com.tyss.optimize.nlp.util.annotation.ReturnType;


import org.springframework.stereotype.Component;

@Component("LIC14952_PJT1001_PE_NLPab1de73f-9bb0-4e5d-b749-9b2d80b8a181")
public class GCBulkTemplate implements Nlp {
		
		    @InputParams({@InputParam(name = "csvFilePath", type = "java.lang.String"),})
		    @ReturnType(name = "result", type = "java.lang.Boolean")

		      @Override
		      public List<String> getTestParameters() throws NlpException {
		        List<String> params = new ArrayList<>();
		        return params;
		      }

		      @Override
		      public StringBuilder getTestCode() throws NlpException {
		        StringBuilder sb = new StringBuilder();
		        return sb;
		      }
		      @Override
		      public NlpResponseModel execute(NlpRequestModel nlpRequestModel) throws NlpException {
		        
		          NlpResponseModel nlpResponseModel = new NlpResponseModel();
		          Map<String, Object> attributes = nlpRequestModel.getAttributes();
		          String  csvFilePath= (String) attributes.get("csvFilePath");
		          boolean result=true;
		          
		          WebDriver driver=nlpRequestModel.getWebDriver();
		          
		          
		          try {
		        	Actions action = new Actions(driver);
		        	String orgId = driver.findElement(By.xpath("//div[contains(text(),'Business Id')]//span//button/preceding-sibling::span")).getText();
		      		String cardProgramId = driver.findElement(By.xpath("//div[contains(text(),'Card Program Id')]//span//button/preceding-sibling::span")).getText();

		      		List<WebElement> rows = driver
		    				.findElements(By.xpath("//h3[contains(text(),'Kit Details')]/following-sibling::div[2]//tbody//tr"));
		    		int numberOfRows = rows.size();

		    		List<WebElement> columns = driver.findElements(
		    				By.xpath("//h3[contains(text(),'Kit Details')]/following-sibling::div[2]//tbody//tr[1]//td"));
		    		int numberOfColumns = columns.size();
		        	  
		    		Random random = new Random();
		    		StringBuilder randomString = new StringBuilder();
		    		
		    		String[][] data = new String[numberOfRows][7];
		      		
		    		for (int i = 0; i < data.length; i++) {
		    			
		    			
		    			String Alphabets="ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvxyz";
		    			String randomName = null;
		    			StringBuilder s = new StringBuilder();
		    			   for (int j=0; j<5; j++) {
		    				   int ch = (int)(Alphabets.length() * Math.random());
		    				   s.append(Alphabets.charAt(ch));
		    				   randomName=s.toString();
		    				  }
		    				 
		    				  long lowerBound = (long) Math.pow(10, 9 - 1);
		    			      long upperBound = (long) Math.pow(10, 9) - 1;

		    			      long randmNum =lowerBound + random.nextInt((int) (upperBound - lowerBound + 1));
		    			      
		    			      String randomNumber="9"+randmNum;
		                      
		    			data[i][0] = orgId;
		    			data[i][1] = cardProgramId;
		    			
		                
		    			int j=i+1;
		    			if(j>data.length) {
		    				break;
		    			}
		    			String xpath2="//h3[contains(text(),'Kit Details')]/following-sibling::div[2]//tbody//tr["+j+"]//td[5]";
		    			String xpath3="//h3[contains(text(),'Kit Details')]/following-sibling::div[2]//tbody//tr["+j+"]//td[2]";

		    			data[i][2] = "100";
		    			data[i][3] = driver.findElement(By.xpath(xpath2)).getText();
		    			data[i][4] = driver.findElement(By.xpath(xpath3)).getText();
		    			data[i][5] =randomName;
		                data[i][6] =randomNumber;
		                
		    		}
		    		String[] headers = { "ORG_ID", "CARD_PROGRAM_ID", "LOAD_AMOUNT", "LAST_FOUR_DIGIT", "KIT_NUMBER","FULL_NAME", "MOBILE" };

		    		writeCSV(headers, data, csvFilePath);

		        	  result=true;
		        	  nlpResponseModel.setStatus(CommonConstants.pass);
		  			  nlpResponseModel.setMessage("file created successfully");
		        	  		        	  
		          }
		          catch(Exception e) {
		        	  result=false;
		        	  nlpResponseModel.setStatus(CommonConstants.fail);
		  			  nlpResponseModel.setMessage("failed to create file"+e);
		          }
		         
		          nlpResponseModel.getAttributes().put("result", result);
		          return nlpResponseModel;
		      }
		      private static void writeCSV(String[] headers, String[][] data, String filePath) {
		  		try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
		  			
		  			writeLine(writer, headers);

		  			
		  			for (String[] row : data) {
		  				writeLine(writer, row);
		  			}
		  		} catch (IOException e) {
		  			e.printStackTrace();
		  		}
		  	}
		      
		      private static void writeLine(BufferedWriter writer, String[] data) throws IOException {
		    		
		  		for (int i = 0; i < data.length; i++) {
		  			writer.write(data[i]);
		  			
		  			if (i < data.length - 1) {
		  				writer.write(",");
		  			}
		  		}
		  	
		  		writer.write("\n");
		  	}
	
	}


