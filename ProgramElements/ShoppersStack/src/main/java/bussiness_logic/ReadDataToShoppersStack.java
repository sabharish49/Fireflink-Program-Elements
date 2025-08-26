package bussiness_logic;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.WebDriver;

import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import com.tyss.optimize.nlp.util.annotation.InputParam;
import com.tyss.optimize.nlp.util.annotation.InputParams;
import com.tyss.optimize.nlp.util.annotation.ReturnType;


	

	
	import org.springframework.stereotype.Component;

@Component("LIC14952_PJT1001_PE_NLPafa62aa5-51ff-4c7d-a9b9-4fe5eefaa5ae")
public class ReadDataToShoppersStack implements Nlp {
	    @InputParams({@InputParam(name = "filePath", type = "java.lang.String"),@InputParam(name = "sheetName", type = "java.lang.String"), @InputParam(name = "row", type = "java.lang.Integer"),@InputParam(name = "cell", type = "java.lang.Integer")})
	    @ReturnType(name = "value", type = "java.lang.String")

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
	          String filePath = (String) attributes.get("filePath");
	          Integer row = (Integer) attributes.get("row");
	          Integer cell = (Integer) attributes.get("cell");
	          String sheetName = (String) attributes.get("sheetName");
	          WebDriver driver=nlpRequestModel.getWebDriver();
	          
	          String value="";
	          // Your program element business logic goes here ...

	          try {
	        	  FileInputStream fis=new FileInputStream(filePath);
	     		 Workbook book=WorkbookFactory.create(fis);
	     		 Sheet sh=book.getSheet(sheetName);
	     		DataFormatter format=new DataFormatter();
	     		value=format.formatCellValue(sh.getRow(row).getCell(cell));
	     		
	     		nlpResponseModel.setStatus(CommonConstants.pass);
	     		nlpResponseModel.setMessage("The data is fetched from excel");

	          }
	          catch(Exception e)
	          {
	        	 
	       		nlpResponseModel.setStatus(CommonConstants.fail);
	       		nlpResponseModel.setMessage("Fetching data from excel failed "+e);;
	          }
	         // String string3 = "Return Value";
	          nlpResponseModel.getAttributes().put("value", value);
	          return nlpResponseModel;
	      }
	  } 


	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	    




