package bussiness_logic;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
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

import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Component;

@Component("LIC14952_PJT1001_PE_NLPa32efc69-029c-48c7-9d33-e62a97e982d3")

public class StoreHeaderAndItsValueToMap implements Nlp {
	
		    @InputParams({@InputParam(name = "excelPath", type = "java.lang.String"),
		    @InputParam(name = "sheetNumber", type = "java.lang.Integer"),
		    @InputParam(name = "headerRow", type = "java.lang.Integer"),
		    @InputParam(name = "cellIndex", type = "java.lang.Integer")})
		    @ReturnType(name = "result", type = "java.lang.String")

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
	
		          Integer sheetNumber = (Integer) attributes.get("sheetNumber");
		          Integer rowNumber = (Integer) attributes.get("headerRow");
		          Integer cellIndex = (Integer) attributes.get("cellIndex");
		          

		          // Your program element business logic goes here ...
		          
		          String values="";
		           
		           try {
		        	   
		        	   FileInputStream fis = new FileInputStream(excelPath);
			             Workbook workbook = new XSSFWorkbook(fis);

			            
			            Sheet sheet = workbook.getSheetAt(sheetNumber);

			           
			            Map<String, String> headerValueMap = new HashMap<>();

			           
			            Row headerRow = sheet.getRow(rowNumber);

			            
			            Iterator<Cell> headerCellIterator = headerRow.cellIterator();
			            while (headerCellIterator.hasNext()) {
			                Cell cell = headerCellIterator.next();
			                String header = cell.getStringCellValue();
			                headerValueMap.put(header, ""); 
			            }

			            
			            Iterator<Row> rowIterator = sheet.iterator();
			            while (rowIterator.hasNext()) {
			                Row row = rowIterator.next();

			               
			                if (row.getRowNum() == 0)
			                    continue;

			                
			                Iterator<Cell> cellIterator = row.cellIterator();
			               
			                while (cellIterator.hasNext()) {
			                    Cell cell = cellIterator.next();
			                    String header = headerRow.getCell(cellIndex).getStringCellValue();
			                    String value = cell.getStringCellValue();
			                    headerValueMap.put(header, value);
			                    cellIndex++;
			                }
			            }

			            
			            for (Map.Entry<String, String> entry : headerValueMap.entrySet()) {
			                values= entry.getKey() + ": " + entry.getValue();
			            }
			            
		        		   
		        		   nlpResponseModel.setStatus(CommonConstants.pass);
		        		   nlpResponseModel.setMessage(" data stored successfully");
		        	   	   
				} 
		           catch (Exception e) {
				
					nlpResponseModel.setStatus(CommonConstants.fail);
		 		   nlpResponseModel.setMessage("failed to store data" +e);
				}
		         
		          nlpResponseModel.getAttributes().put("result", values);
		          return nlpResponseModel;
		      }
		  } 
