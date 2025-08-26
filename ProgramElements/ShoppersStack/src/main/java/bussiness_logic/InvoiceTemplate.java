package bussiness_logic;
	import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.openqa.selenium.WebDriver;

import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import com.tyss.optimize.nlp.util.annotation.InputParam;
import com.tyss.optimize.nlp.util.annotation.InputParams;
import com.tyss.optimize.nlp.util.annotation.ReturnType;

public class InvoiceTemplate implements Nlp {
			
			    @InputParams({@InputParam(name = "FilePath", type = "java.lang.String"),
			    @InputParam(name = "SellerGst", type = "java.lang.String"),
			    @InputParam(name = "BuyerGst", type = "java.lang.String"),
			    @InputParam(name = "InvoiceAmount", type = "java.lang.String"),
			    @InputParam(name = "NumberOfInvoice", type = "java.lang.Integer")})
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
			          String  csvFilePath= (String) attributes.get("FilePath");
			          String  sellerGst = (String) attributes.get("SellerGst");
			          String  buyerGst = (String) attributes.get("BuyerGst");
			          String  invoiceAmount = (String) attributes.get("InvoiceAmount");
			          Integer  numberOfInvoice = (Integer) attributes.get("NumberOfInvoice");
			          
			          boolean result=true;
			          
			          WebDriver driver=nlpRequestModel.getWebDriver();
			          
			          
			          try {
			        	  
			        	//String orgId = driver.findElement(By.xpath("//span[contains(text(),'Business ID')]/../descendant::div/span/span")).getText();
			      		//WebElement cardProgram = driver.findElement(By.xpath("//h6[contains(text(),'Card Program ID')]/following-sibling::h6/span/span"));
			      		//String cardProgramId=cardProgram.getText();
			      		//cardProgram.click();
			      		Thread.sleep(2000);
			         	//WebElement	wallet =driver.findElement(By.xpath("//button[@class=\"btn m-1 collapse-btn p-0 my-0 btn-light\"]//following-sibling::span"));
			         //	wallet.click();
			         	//String walletId = driver.findElement(By.xpath("//div[contains(text(),'Wallet')]/following::span/span/span")).getText();
			         	Random random = new Random();
			    		StringBuilder randomString = new StringBuilder();
			    		String[][] data = new String[numberOfInvoice][9];
			    		for (int i = 0; i < data.length; i++) {
			    			
			    			String Alphabets="123456789";
			    			String randomName = null;
			    			StringBuilder s = new StringBuilder();
			    			for (int j=0; j<2; j++) {
			    				int ch = (int)(Alphabets.length() * Math.random());
			    				s.append(Alphabets.charAt(ch));
			    				randomName=s.toString();
			    			}
			    			long lowerBound = (long) Math.pow(10, 9 - 1);
			    			long upperBound = (long) Math.pow(10, 9) - 1;

			    			long randmNum =lowerBound + random.nextInt((int) (upperBound - lowerBound + 1));
			    			String randomNumber="9"+randmNum;
			    			data[i][0] = randomName;
			    			data[i][1] = randomNumber;
			    			data[i][2] = randomNumber;
			    			data[i][3] = randomNumber;
			    			data[i][4] = buyerGst;
			    			data[i][5] =sellerGst;
			    			data[i][6] =invoiceAmount;
			    			Double invoiceAmnt = Double.parseDouble(invoiceAmount);
			    			Double gstAmount=invoiceAmnt * 0.18;
			    			Double totalAmount=invoiceAmnt+gstAmount;
			    			String gstAmountStr = String.valueOf(gstAmount);
			    			data[i][7] =gstAmountStr;
			    			String totalAmountStr = String.valueOf(totalAmount);
			    			data[i][8] =totalAmountStr;
			    			LocalDate currentDate = LocalDate.now();
			    	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			    	        String formattedDate = currentDate.format(formatter);
			    			//data[i][9] =formattedDate;
			    			
			    		}
			    		String[] headers = { "invoice_number", "doc_no", "invoice_file", "invoice_type", "buyer_gst","seller_gst", "invoice_amount", "gst_amount" ,"Total_Invoice_Amt","invoice_date"};
			        	  
			    		writeCSV(headers, data, csvFilePath);
                         System.out.println("data Written");
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
