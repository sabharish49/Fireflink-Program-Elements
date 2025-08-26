package bussiness_logic;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
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

@Component("LIC14952_PJT1001_PE_NLP677987f6-fb89-42ea-a298-617480c4d3a6")
public class AddColourToTheUserDefinedCell implements Nlp {
	
	    @InputParams({@InputParam(name = "excelPath", type = "java.lang.String"),
	    @InputParam(name = "cellValue", type = "java.lang.String"),
	    @InputParam(name = "sheetName", type = "java.lang.String"),
	    @InputParam(name = "rowIndex", type = "java.lang.Integer"),
	    @InputParam(name = "columnIndex", type = "java.lang.Integer")})
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
	          String cellValue = (String) attributes.get("cellValue");
	          String sheetName = (String) attributes.get("sheetName");
	          Integer rowIndex = (Integer) attributes.get("rowIndex");
	          Integer columnIndex = (Integer) attributes.get("columnIndex");

	          // Your program element business logic goes here ...
	          
	           Boolean result=true;
	           
	           try {
	        	   Workbook workbook = new XSSFWorkbook();
	        	   Sheet sheet = workbook.createSheet(sheetName);
	        	   Row row = sheet.createRow(rowIndex);

	    	       
	   	           Cell cell = row.createCell(columnIndex);

	   	     
	   	           cell.setCellValue(cellValue);
	   	        CellStyle style = workbook.createCellStyle();
		        style.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
		        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		        cell.setCellStyle(style);
		        FileOutputStream fos = new FileOutputStream(excelPath);
		            workbook.write(fos);
		         

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











