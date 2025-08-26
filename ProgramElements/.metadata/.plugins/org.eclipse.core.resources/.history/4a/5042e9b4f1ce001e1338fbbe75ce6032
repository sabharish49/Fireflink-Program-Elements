package bussiness_logic;
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

@Component("LIC14952_PJT1001_PE_NLPce4e27c2-ee18-47f5-bb3b-727fc974203c")
public class CreateAnEmptyExcelFile implements Nlp {
	
	    @InputParams({@InputParam(name = "excelPath", type = "java.lang.String"),
	    @InputParam(name = "sheetName", type = "java.lang.String"),
	    @InputParam(name = "numbnerOfRows", type = "java.lang.Integer"),
	    @InputParam(name = "numberOfColumns", type = "java.lang.Integer")})
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
	          String sheetName = (String) attributes.get("sheetName");
	          Integer numbnerOfRows = (Integer) attributes.get("numbnerOfRows");
	          Integer numberOfColumns = (Integer) attributes.get("numberOfColumns");
	          

	          // Your program element business logic goes here ...
	          
	           Boolean result=true;
	           
	           try {
	        	   Workbook workbook = new XSSFWorkbook();
		           FileOutputStream fos = new FileOutputStream(excelPath);

		            
		            Sheet sheet = workbook.createSheet(sheetName);
		            for (int rowNum = 0; rowNum < numbnerOfRows; rowNum++) {
		                Row row = sheet.createRow(rowNum);
		                for (int colNum = 0; colNum < numberOfColumns; colNum++) {
		                    Cell cell = row.createCell(colNum);
		                    cell.setCellValue("");
		                    workbook.write(fos);
		           
	        		   result=true;
	        		   nlpResponseModel.setStatus(CommonConstants.pass);
	        		   nlpResponseModel.setMessage("excel generated successfully");
	        	   
		                }	   
	    		   
			} 
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
