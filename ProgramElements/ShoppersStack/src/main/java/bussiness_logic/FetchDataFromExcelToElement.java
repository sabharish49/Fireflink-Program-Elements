package bussiness_logic;

	import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import com.tyss.optimize.nlp.util.annotation.InputParam;
import com.tyss.optimize.nlp.util.annotation.InputParams;
import com.tyss.optimize.nlp.util.annotation.ReturnType;

import org.springframework.stereotype.Component;

@Component("LIC14952_PJT1001_PE_NLP6ebe50b1-f847-4ede-98d8-2490818a3c7d")
public class FetchDataFromExcelToElement implements Nlp {
			
			    @InputParams({@InputParam(name = "excelFilePath", type = "java.lang.String"),
			    @InputParam(name = "headerName", type = "java.lang.String"),
			    @InputParam(name = "sheetNumber", type = "java.lang.Integer")})
			    
			    @ReturnType(name = "excelValue", type = "java.lang.String")

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
			          String headerName = (String) attributes.get("headerName");
			          Integer sheetNumber = (Integer) attributes.get("sheetNumber");
			          

			          // Your program element business logic goes here ...
			          
			          String value="";
			           
			           try {
			        	   FileInputStream excelFile = new FileInputStream(new File(excelFilePath));
				            Workbook workbook = WorkbookFactory.create(excelFile);
				            Sheet sheet = workbook.getSheetAt(sheetNumber);
				            int columnIndex = -1;
				            Row headerRow = sheet.getRow(0);
				            Iterator<Cell> cellIterator = headerRow.cellIterator();
				            while (cellIterator.hasNext()) {
				                Cell cell = cellIterator.next();
				                if (cell.getStringCellValue().equalsIgnoreCase(headerName)) {
				                    columnIndex = cell.getColumnIndex();
				                    break;
				                }
				            }

				            if (columnIndex != -1) {
			
				                Iterator<Row> rowIterator = sheet.iterator();
				                rowIterator.next(); 
				                while (rowIterator.hasNext()) {
				                    Row row = rowIterator.next();
				                    Cell cell = row.getCell(columnIndex);
				                    if (cell != null) {
				                        
				                        value=cell.toString();
				                 
					        		   nlpResponseModel.setStatus(CommonConstants.pass);
					        		   nlpResponseModel.setMessage("successfully fetched the value");
					                 
					        }
				             }
			           }
			           }
				   
			        		  
			           catch (Exception e) {
						
						nlpResponseModel.setStatus(CommonConstants.fail);
			 		   nlpResponseModel.setMessage("failed to fetch the value" +e);
					}
			         
			          nlpResponseModel.getAttributes().put("excelValue", value);
			          return nlpResponseModel;
			      }
			  } 

