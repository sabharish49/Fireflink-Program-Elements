
package Business_Logics;

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import com.tyss.optimize.nlp.util.annotation.InputParam;
import com.tyss.optimize.nlp.util.annotation.InputParams;
import com.tyss.optimize.nlp.util.annotation.ReturnType;

import io.appium.java_client.android.AndroidDriver;




public class ValidatingStockFeed implements Nlp {
    @InputParams({@InputParam(name = "StockName", type = "java.lang.String"), @InputParam(name = "Section", type = "java.lang.String"),@InputParam(name = "Time", type = "java.lang.String"),@InputParam(name = "Component", type = "java.lang.String")})
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
        
          String StockName = (String) attributes.get("StockName");
          String Section = (String) attributes.get("Section");
          String Time =(String) attributes.get("Time");
          String Component = (String) attributes.get("Component");

          AndroidDriver driver = nlpRequestModel.getAndroidDriver();
          Duration implicitWait = driver.manage().timeouts().getImplicitWaitTimeout();
          
          
          boolean result=false;
          String price = null;
	  	  String value = null;
          
          
          System.out.println("============== action started===================");
  		
          long startTimeforLogic = System.currentTimeMillis();
  		
  		try {
  			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
  			WebElement element =null;
  	  		
  			String section=Section;
  			String scriptName = StockName;
  			String time = Time;
  			String component = Component;

  			
  			
  			String delimiter = "-";
  			String[] scriptArray = scriptName.split(delimiter);
  			String stockName = scriptArray[0];
  			
  			String stockExchange = "";
  			if(section.contains("Watchlist")|section.contains("Position")|section.contains("Orders")) {
  				 stockExchange = scriptArray[1];

  			}
  				
  			String attribute = "content-desc";

  			

  				String s = time.substring(0, time.length() - 1);
  				int number = Integer.parseInt(s);
  				//System.out.println(number);
  				String type = time.substring(time.length() - 1);
  				//System.out.println(type);

  				switch (type) {
  				case "s":
  					number = number * 1000;
  					break;
  				case "m":
  					number = number * 60 * 1000;
  					break;
  				case "h":
  					number = number * 60 * 60 * 1000;
  					break;
  				default:
  					//System.out.println("enter proper input");
  					break;
  				}

  				// System.out.println(number);
  				long startTime = System.currentTimeMillis();

  				switch (component) {
  				case "LTP": {
  					for (int i = 0; i < 50; i++) {
  						try {
  							String xpath="";
  							switch (section) {
  								case "Watchlist" :{
  									xpath="//android.widget.TextView[contains(@text,'"+stockName+"')]/following-sibling::android.widget.TextView[@text='"+stockExchange+"']/preceding-sibling::android.view.View[@resource-id='com.choiceequitybroking.jiffy:id/tvLtp']";
  									
  								}
  								break;
  								case "Option Chain" :{
  									xpath="//android.widget.TextView[@text='"+stockName+"']/following-sibling::android.view.View[@resource-id='com.choiceequitybroking.jiffy:id/tvValue1']";
  								}
  								break;
  								case "Portfolio" :{
  									xpath="//android.widget.TextView[contains(@text,'"+stockName+"')]/following-sibling::android.view.View[@resource-id='com.choiceequitybroking.jiffy:id/tvLtp']";
  								}
  								break;
  								case "Position" :{
  									xpath="//android.widget.TextView[contains(@text,'"+stockName+"')]/following-sibling::android.widget.TextView[@text='"+stockExchange+"']/following-sibling::android.view.View[@resource-id='com.choiceequitybroking.jiffy:id/tvLtp']";			
  								}
  								break;
  								case "Orders" :{
  									xpath="//android.widget.TextView[contains(@text,'"+stockName+"')]/following-sibling::android.widget.TextView[@text='"+stockExchange+"']/preceding-sibling::android.view.View[@resource-id='com.choiceequitybroking.jiffy:id/tvLtp']";				
  								}
  								break;
  								
  							}
  							
  							
  							element=driver.findElement(By.xpath(xpath));
  							price = element.getAttribute(attribute);
  							price = price.replaceAll(",", "");
  						} catch (Exception e) {
  							e.printStackTrace();
  						}
  						if (!(price.equals(null))) {
  							break;
  						}
  					}
  				}
  					break;
  				case "P/L": {
  					for (int i = 0; i < 50; i++) {
  						try {
  							
  							String xpath="";
  							switch (section) {
  								case "Watchlist" :{
  									xpath="//android.widget.TextView[contains(@text,'"+stockName+"')]/following-sibling::android.widget.TextView[@text='"+stockExchange+"']/following-sibling::android.widget.TextView[@resource-id='com.choiceequitybroking.jiffy:id/tvCcp']";						
  								}
  								break;
  								case "Option Chain" :{
  									xpath="//android.widget.TextView[@text='"+stockName+"']/following-sibling::android.widget.TextView[@resource-id='com.choiceequitybroking.jiffy:id/tvValue2']";
  								}
  								break;
  								case "Portfolio" :{
  									xpath="//android.widget.TextView[contains(@text,'"+stockName+"')]/following-sibling::android.widget.TextView[contains(@resource-id,'com.choiceequitybroking.jiffy:id/tvCcp')]";
  								}
  								break;
  								case "Position" :{
  									xpath="//android.widget.TextView[contains(@text,'"+stockName+"')]/following-sibling::android.widget.TextView[@text='"+stockExchange+"']/preceding-sibling::android.widget.TextView[@resource-id='com.choiceequitybroking.jiffy:id/tvPnlValue']";			
  								}
  								break;
  								case "Orders" :{
  									xpath="//android.widget.TextView[contains(@text,'"+stockName+"')]/following-sibling::android.widget.TextView[@text='"+stockExchange+"']/following-sibling::android.widget.TextView[@resource-id='com.choiceequitybroking.jiffy:id/tvCcp']";						
  								}
  								break;
  							}
  							
  							element=driver.findElement(By.xpath(xpath));				
  							if(section.contains("Option")) {
  								while(true) {
  									if (!(element.getText().contains("("))) {
  										price = element.getText();
  										if(!(price.contains("("))) {
  											break;
  										}
  									}
  								}
  							}
  							else {
  								price = element.getText();
  								if(section.contains("Position")) {
  									price = element.getText();
  								}
  								else {
  									int endIndex = price.indexOf('(') - 1;
  									price = price.substring(0, endIndex);
  								}						
  							}
  							
  						} catch (Exception e) {
  							System.out.println(e+"at case p/l");
  						}
  						if (!(price.equals(null))) {
  							break;
  						}
  					}
  				}
  					break;
  				case "PercentageChange": {
  					for (int i = 0; i < 50; i++) {
  						try {
  							String xpath="";
  							switch (section) {
  								case "Watchlist" :{
  									xpath="//android.widget.TextView[contains(@text,'"+stockName+"')]/following-sibling::android.widget.TextView[@text='"+stockExchange+"']/following-sibling::android.widget.TextView[@resource-id='com.choiceequitybroking.jiffy:id/tvCcp']";						
  								}
  								break;
  								case "Option Chain" :{
  									xpath="//android.widget.TextView[@text='"+stockName+"']/following-sibling::android.widget.TextView[@resource-id='com.choiceequitybroking.jiffy:id/tvValue2']";
  								}
  								break;
  								case "Portfolio" :{
  									xpath="//android.widget.TextView[contains(@text,'"+stockName+"')]/following-sibling::android.widget.TextView[contains(@resource-id,'com.choiceequitybroking.jiffy:id/tvCcp')]";
  								}
  								break;
  								case "Position" :{
  									xpath="//android.widget.TextView[contains(@text,'"+stockName+"')]/following-sibling::android.widget.TextView[@text='"+stockExchange+"']/following-sibling::android.widget.TextView[contains(@resource-id,'com.choiceequitybroking.jiffy:id/tvPnlCcpValue')]";			
  								}
  								break;
  								case "Orders" :{
  									xpath="//android.widget.TextView[contains(@text,'"+stockName+"')]/following-sibling::android.widget.TextView[@text='"+stockExchange+"']/following-sibling::android.widget.TextView[@resource-id='com.choiceequitybroking.jiffy:id/tvCcp']";						
  								}
  								break;
  								
  							}
  							
  							element=driver.findElement(By.xpath(xpath));
  							price = element.getText();
  							
  							if(section.contains("Option")) {
  								while(true) {
  									if ((element.getText().contains("("))) {
  										price = element.getText();
  										if((price.contains("("))) {
  											break;
  										}
  									}
  								}
  							}
  							
  							int startIndex = price.indexOf('(')+1;
  							int endIndex = price.indexOf('%');
  							price = price.substring(startIndex, endIndex);
  						}
  						catch (Exception e) {
  							e.getMessage();
  						}
  						if (!(price.equals(null))) {
  							break;
  						}
  					}
  				}
  					break;
  				}

  				System.out.println(price);
  				value = modify(price, startTime, number, driver, element, component, attribute,section);
  				System.out.println(value);
  				
  				LocalTime currentTime = LocalTime.now();

  	  	        LocalTime marketOpenTime = LocalTime.of(9, 30);
  	  	        LocalTime marketCloseTime = LocalTime.of(15, 30);

	    	       
	    	        if (currentTime.isAfter(marketOpenTime) && currentTime.isBefore(marketCloseTime)) {
	    	        	if(!(price.equals(value))) {  	
	    	        		result=true;
	    	        		nlpResponseModel.setStatus(CommonConstants.pass);
	    	        		nlpResponseModel.setMessage("Successfully validated change in value from Initial Value " + price + " " + "updated as:" + value +"in inside market hours,within specified time");
	        			}
	        			else {  				
	        				nlpResponseModel.setStatus(CommonConstants.fail);
	    	        		nlpResponseModel.setMessage("The values of the expected element are not getting autorefreshed for the specified duration"+ price +"in inside market hours");		
	        			}     	
	    	        	
	    	        } else {
	    	        	if(value!=null && (!(price.equals(value)))) {
	    	        		result=true;
	    	        		nlpResponseModel.setStatus(CommonConstants.pass);
	    	        		nlpResponseModel.setMessage("Successfully validated change in value from Initial Value " + price + " " + "updated as:" + value +"in inside market hours,within specified time");	        			}
	        			else {
	        				nlpResponseModel.setStatus(CommonConstants.pass);
	    	        		nlpResponseModel.setMessage("The values of the expected element are not getting autorefreshed for the specified duration from "+ price +" due to outside market hours");		
	        			}    
	    	        }
	  			
	  		
	  		long endTime = System.currentTimeMillis();
	  		System.out.println((endTime-startTimeforLogic)/1000);
  			
  		}
  		catch (Exception e) {
  			result=false;
  			nlpResponseModel.setStatus(CommonConstants.fail);
    		nlpResponseModel.setMessage("The values of the expected element are not getting autorefreshed for the specified duration from "+ price +" due to outside market hours");
			
		}
  		finally {
			driver.manage().timeouts().implicitlyWait(implicitWait);
		}
  		
  		nlpResponseModel.getAttributes().put("result", result);
        return nlpResponseModel;
  		

  	}

  	private static String modify(String price, long startTime, int number, AndroidDriver driver, WebElement element,
  			String component, String attribute, String section) {
  		
  		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
  		
  		boolean result = false;
  		String value = null ;
  		
  		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
  		
  		try {
  			String modifiedPrice = "";
  			while (System.currentTimeMillis() - startTime <= number) {				
  				
  				try {
  					switch (component) {
  						case "LTP": {
  							
  								String currentPrice = element.getAttribute(attribute);
  								currentPrice = currentPrice.replaceAll(",", "");
  		
  								if (!(price.equals(currentPrice)) && (currentPrice!=null | currentPrice!="")) {
  									result = true;
  									modifiedPrice = currentPrice;
  									value = modifiedPrice;
  									System.out.println("modifiedPrice : " + modifiedPrice);
  									System.out.println("Values modified,Stopping the program.");
  								}
  	
  						}
  						break;
  						case "P/L": {
  							
  							String currentPrice = element.getText();
  							if (section.contains("Option")) {
  								while (true) {
  									if (!(element.getText().contains("("))) {
  										currentPrice = element.getText();
  										if(!currentPrice.contains("(")) {
  											break;
  										}
  									}
  								}
  							}
  							else {
  								currentPrice = element.getText();
  								if (section.contains("Position")) {
  									currentPrice = element.getText();
  								} else {
  									int endIndex = price.indexOf('(') - 1;
  									currentPrice = price.substring(0, endIndex);
  								}
  							}
  		
  							if (!(price.equals(currentPrice)) && (currentPrice!=null | currentPrice!="")) {
  								result = true;
  								modifiedPrice = currentPrice;
  								value = modifiedPrice;
  								System.out.println("modifiedPrice : " + modifiedPrice);
  								System.out.println("Values modified,Stopping the program.");
  							}
  	
  						}
  						break;
  						case "PercentageChange": {
  							
  							
  							String currentPrice = element.getText();
  							
  							if(section.contains("Option")) {
  								while(true) {
  									while (true) {
  										if ((element.getText().contains("("))) {
  											currentPrice = element.getText();
  											if(!currentPrice.contains("(")) {
  												break;
  											}
  										}
  									}
  								}
  							}
  							
  							int startIndex = currentPrice.indexOf('(')+1;
  							int endIndex = currentPrice.indexOf('%');
  							currentPrice = currentPrice.substring(startIndex, endIndex);
  							
  							if (!(price.equals(currentPrice)) && (currentPrice!=null | currentPrice!="")) {
  								result = true;
  								modifiedPrice = currentPrice;
  								value = modifiedPrice;
  								System.out.println("modifiedPrice : " + modifiedPrice);
  								System.out.println("Values modified,Stopping the program.");
  							}
  	
  						}
  						break;
  					}
  				} catch (Exception e) {
  					 modify(price, startTime, number, driver, element, component, attribute,section);
  				}
  				if (result == true) {
  					break;
  				}		
  			}
  			if (result == true) {
  				System.out.println("Initial Value " + price + " " + "updated as:" + modifiedPrice);
  			} else {
  				System.out.println(
  						"The values of the expected element are not getting autorefreshed for the specified duration from"
  								+ price );
  			}
  		} catch (Exception e) {
  			 modify(price, startTime, number, driver, element, component, attribute,section);
  		}
  		
  		return value;

  	}


  } 