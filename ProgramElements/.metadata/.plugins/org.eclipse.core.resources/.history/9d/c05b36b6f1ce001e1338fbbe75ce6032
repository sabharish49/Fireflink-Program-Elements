package bussiness_logic;


	import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import com.tyss.optimize.nlp.util.annotation.InputParam;
import com.tyss.optimize.nlp.util.annotation.InputParams;
import com.tyss.optimize.nlp.util.annotation.ReturnType;

import org.springframework.stereotype.Component;

@Component("LIC14952_PJT1001_PE_NLPb6a91ab6-6846-440e-b7a4-869aa599adab")
public class EditDataInExcelSheetBasedOnRowAndColumnNumber implements Nlp {
	
	    @InputParams({@InputParam(name = "excelPath", type = "java.lang.String"),
	    @InputParam(name = "newValue", type = "java.lang.String"),
	    @InputParam(name = "sheetNumberToEdit", type = "java.lang.Integer"),
	    @InputParam(name = "rowNumberToEdit", type = "java.lang.Integer"),
	    @InputParam(name = "cellNumberToEdit", type = "java.lang.Integer")})
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
	          String excelPath = (String) attributes.get("excelPath");
	          String newValue = (String) attributes.get("newValue");
	          Integer sheetNumberToEdit = (Integer) attributes.get("sheetNumberToEdit");
	          Integer rowNumberToEdit = (Integer) attributes.get("rowNumberToEdit");
	          Integer cellNumberToEdit = (Integer) attributes.get("cellNumberToEdit");

	          // Your program element business logic goes here ...
	          
	           Boolean result=true;
	           
	           try {
	        	   FileInputStream inputStream = new FileInputStream(excelPath);
		             Workbook workbook = new XSSFWorkbook(inputStream); 
                    
		             Sheet sheet = workbook.getSheetAt(sheetNumberToEdit);

		 	            
		 	            Row row = sheet.getRow(rowNumberToEdit);
		 	            Cell cell = row.getCell(cellNumberToEdit, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                    
		 	            cell.setCellValue(newValue);
		           
		 	           FileOutputStream outputStream = new FileOutputStream(excelPath); 
			           workbook.write(outputStream);
	
	        		   result=true;
	        		   nlpResponseModel.setStatus(CommonConstants.pass);
	        		   nlpResponseModel.setMessage("excel generated successfully");
	        	   
	        		   
	        		   
			} 
	           catch (Exception e) {
				result=false;
				nlpResponseModel.setStatus(CommonConstants.fail);
	 		   nlpResponseModel.setMessage("excel not generated" +e);
			}
	         
	          nlpResponseModel.getAttributes().put("result", result);
	          return nlpResponseModel;
	      }
	  } 








