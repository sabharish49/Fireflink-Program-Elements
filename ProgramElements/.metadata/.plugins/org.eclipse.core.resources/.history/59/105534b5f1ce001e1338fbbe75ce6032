package bussiness_logic;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
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

@Component("LIC14952_PJT1001_PE_NLP5fb92349-757b-4746-aa72-ffc2f0749bc8")
public class GetSpecificDataFromExcelStoreInTextFile implements Nlp {
		
		    @InputParams({@InputParam(name = "excelFilePath", type = "java.lang.String"),
		    @InputParam(name = "textFilePath", type = "java.lang.String"),
		    @InputParam(name = "sheetNumber", type = "java.lang.Integer"),
		    @InputParam(name = "rowNumber", type = "java.lang.Integer"),
		    @InputParam(name = "cellNumber", type = "java.lang.Integer")})
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
		          String excelFilePath = (String) attributes.get("excelFilePath");
		          String textFilePath = (String) attributes.get("textFilePath");
		          Integer sheetNumber = (Integer) attributes.get("sheetNumber");
		          Integer rowNumber = (Integer) attributes.get("rowNumber");
		          Integer cellNumber = (Integer) attributes.get("cellNumber");

		          // Your program element business logic goes here ...
		          
		           Boolean result=true;
		           
		           try {
		        	   FileInputStream fileInputStream = new FileInputStream(excelFilePath);
			             Workbook workbook = new XSSFWorkbook(fileInputStream);
			             Sheet sheet = workbook.getSheetAt(sheetNumber);
			             
			             if (rowNumber >= 0 && rowNumber <= sheet.getLastRowNum()) {
				                Row row = sheet.getRow(rowNumber);

				                if (cellNumber >= 0 && cellNumber < row.getLastCellNum()) {
				                    Cell cell = row.getCell(cellNumber);

				                    
				                   FileWriter fileWriter = new FileWriter(textFilePath);
				                   BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

				                   bufferedWriter.write(cell.toString());
				                   result=true;
				        		   nlpResponseModel.setStatus(CommonConstants.pass);
				        		   nlpResponseModel.setMessage("successfully added to text file");
				                 
				        }
			             }
		           }
			   
		        		  
		           catch (Exception e) {
					result=false;
					nlpResponseModel.setStatus(CommonConstants.fail);
		 		   nlpResponseModel.setMessage("failed to add to text file" +e);
				}
		         
		          nlpResponseModel.getAttributes().put("result", result);
		          return nlpResponseModel;
		      }
		  } 














