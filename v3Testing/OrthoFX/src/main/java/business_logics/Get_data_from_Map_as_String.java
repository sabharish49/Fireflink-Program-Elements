
package business_logics;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.LinkedList;
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




public class Get_data_from_Map_as_String implements Nlp {
	@InputParams({ @InputParam(name = "sheetName", type = "java.lang.String"),
	@InputParam(name = "startRowIndex", type = "java.lang.Integer"),
	@InputParam(name = "endRowIndex", type = "java.lang.Integer"),
	@InputParam(name = "filePath", type = "java.lang.String"),
	@InputParam(name = "columnIndex", type = "java.lang.Integer")})
	
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
		String Sheet = (String) attributes.get("sheetName");
		Integer startRow = (Integer) attributes.get("startRowIndex");
		Integer endRow = (Integer) attributes.get("endRowIndex");
		String file = (String) attributes.get("filePath");
		Integer column = (Integer) attributes.get("columnIndex");
		
		LinkedList<String> data = new LinkedList<>();
		
		String res = null;
		String result = null;
		try (FileInputStream fis = new FileInputStream(file);
	             Workbook workbook = new XSSFWorkbook(fis)){
			
			    Sheet sheet = workbook.getSheet(Sheet);
			    if (sheet == null) {
	                System.out.println("Sheet with name \"" + Sheet + "\" does not exist.");
	              
	            }
			    
			   
			    for (int i = startRow; i <= endRow; i++) {
			    	
	                Row row = sheet.getRow(i);

	                if (row != null) {
	                    Cell cell = row.getCell(column);

	                    if (cell != null) {
	                      
	                    	data.add(cell.toString().trim());
	                      
	                       result = String.join(", ", data);
	                      
	                    }
	                    
	                    else {
	                    	
	                        System.out.println("Empty cell at row " + (i + 1) + ", column " + (column + 1));
	                    }
	                }
	            }
			  
				nlpResponseModel.setStatus(CommonConstants.pass);
				nlpResponseModel.setMessage("Successfully extracted the data from excel");
			

		}
		
		catch (Exception e) {
			
			nlpResponseModel.setStatus(CommonConstants.fail);
			nlpResponseModel.setMessage("Failed to get excel data  "+e);
		}
		result = String.join(", ", data);
		res = result.replace(",", "");
		//System.out.println("final resul***************************"+res);
		nlpResponseModel.getAttributes().put("result", res);
		return nlpResponseModel;
	}

}