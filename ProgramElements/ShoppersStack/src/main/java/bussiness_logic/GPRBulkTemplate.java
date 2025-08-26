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

	import com.tyss.optimize.common.util.CommonConstants;
	import com.tyss.optimize.nlp.util.Nlp;
	import com.tyss.optimize.nlp.util.NlpException;
	import com.tyss.optimize.nlp.util.NlpRequestModel;
	import com.tyss.optimize.nlp.util.NlpResponseModel;
	import com.tyss.optimize.nlp.util.annotation.InputParam;
	import com.tyss.optimize.nlp.util.annotation.InputParams;
	import com.tyss.optimize.nlp.util.annotation.ReturnType;


	import org.springframework.stereotype.Component;

@Component("LIC14952_PJT1001_PE_NLPb09ea8e8-8c6d-4f53-81e0-e6d477513f79")
public class GPRBulkTemplate implements Nlp {
			
			    @InputParams({@InputParam(name = "csvFilePath", type = "java.lang.String"),
			    @InputParam(name = "numberOfCards", type = "java.lang.Integer")})
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
			          Integer  numberOfCards = (Integer) attributes.get("numberOfCards");
			          boolean result=true;
			          
			          WebDriver driver=nlpRequestModel.getWebDriver();
			          
			          
			          try {
			        	  
			        	String orgId = driver.findElement(By.xpath("//span[contains(text(),'Business ID')]/../descendant::div/span/span")).getText();
			      		WebElement cardProgram = driver.findElement(By.xpath("//h6[contains(text(),'Card Program ID')]/following-sibling::h6/span/span"));
			      		String cardProgramId=cardProgram.getText();
			      		cardProgram.click();
			      		Thread.sleep(2000);
			         	WebElement	wallet =driver.findElement(By.xpath("//button[@class=\"btn m-1 collapse-btn p-0 my-0 btn-light\"]//following-sibling::span"));
			         	wallet.click();
			         	String walletId = driver.findElement(By.xpath("//div[contains(text(),'Wallet')]/following::span/span/span")).getText();
			         	Random random = new Random();
			    		StringBuilder randomString = new StringBuilder();
			    		String[][] data = new String[numberOfCards][13];
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
			    			data[i][0] = randomName;
			    			data[i][1] = randomName;
			    			data[i][2] = randomNumber;
			    			data[i][3] = "fireflink";
			    			data[i][4] = "Banglore";
			    			data[i][5] ="562123";
			    			data[i][6] ="Karnataka";
			    			data[i][7] ="IND";
			    			data[i][8] =orgId;
			    			data[i][9] =cardProgramId;
			    			data[i][10]=walletId;
			    			data[i][11]="100";
			    			data[i][12]="PHYSICAL_NAMED_CARD";
			    		}
			    		String[] headers = { "FULL_NAME ", "NAME_ON_CARD", "MOBILE", "DELIVERY_ADDRESS_1", "DELIVERY_ADDRESS_CITY","DELIVERY_ADDRESS_PINCODE", "DELIVERY_ADDRESS_STATE", "DELIVERY_ADDRESS_COUNTRY" ,"ORG_ID","CARD_PROGRAM_ID","WALLET_ID_1","WALLET_1_LIMIT","CARD_MODE"};
			        	  
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
