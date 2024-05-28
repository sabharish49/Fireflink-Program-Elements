
package Business_Logic;

import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import com.tyss.optimize.nlp.util.annotation.InputParam;
import com.tyss.optimize.nlp.util.annotation.InputParams;
import com.tyss.optimize.nlp.util.annotation.ReturnType;

import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.*;
import java.util.Iterator;

@Slf4j

@Component("LIC12620_PJT1003_PE_NLPcae3cf27-d8cf-484f-aa47-bba06c9b5017")
public class swapFirstExcelSheetWithPaticularExcelSheet implements Nlp {
	@InputParams({ @InputParam(name = "excelFilePath", type = "java.lang.String"),
			@InputParam(name = "sheetName", type = "java.lang.String") })
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
		String excelFilePath = (String) attributes.get("excelFilePath");
		String sheetName = (String) attributes.get("sheetName");

		// Your program element business logic goes here ...
		String result=null;
		try {
			result=swapSheets(excelFilePath, sheetName);
		} catch (Exception e) {

		}

		
		nlpResponseModel.getAttributes().put("result", result);
		return nlpResponseModel;
	}

	public static String swapSheets(String filePath, String sheetName) {
		String message=null;
		try {
			FileInputStream fileInputStream = new FileInputStream(new File(filePath));
			Workbook workbook = new XSSFWorkbook(fileInputStream);

			int firstSheetIndex = 0;
			String firstSheetName = workbook.getSheetAt(firstSheetIndex).getSheetName();
			int sheetIndex = -1;

			Iterator<Sheet> sheetIterator = workbook.sheetIterator();
			int index = 0;
			while (sheetIterator.hasNext()) {
				Sheet sheet = sheetIterator.next();
				if (sheet.getSheetName().equals(sheetName)) {
					sheetIndex = index;
				}
				index++;
			}

			if (sheetIndex != -1) {
				workbook.setSheetOrder(firstSheetName, sheetIndex);
				workbook.setSheetOrder(sheetName, firstSheetIndex);

				FileOutputStream fileOutputStream = new FileOutputStream(new File(filePath));
				workbook.write(fileOutputStream);

				fileOutputStream.close();
				message=sheetName+"swapped successfully.";

			} else {
				message=sheetName+"is not found.";
			}

			workbook.close();
			fileInputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return message;
	}

}