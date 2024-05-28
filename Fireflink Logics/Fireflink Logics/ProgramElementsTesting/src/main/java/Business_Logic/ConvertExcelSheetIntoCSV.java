package Business_Logic;

import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import com.tyss.optimize.nlp.util.annotation.InputParam;
import com.tyss.optimize.nlp.util.annotation.InputParams;
import com.tyss.optimize.nlp.util.annotation.ReturnType;
import java.util.Map;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import com.opencsv.CSVWriter;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

import org.springframework.stereotype.Component;

@Component("LIC12620_PJT1003_PE_NLP44e15011-418f-44cd-8acb-8167fa954b55")
public class ConvertExcelSheetIntoCSV implements Nlp {
	@InputParams({ @InputParam(name = "excelFilePath", type = "java.lang.String"),
			@InputParam(name = "sheetName", type = "java.lang.String"),
			@InputParam(name = "csvFilePath", type = "java.lang.String") })
	@ReturnType(name = "resultantCsvFilePath", type = "java.lang.String")

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
		String csvFilePath = (String) attributes.get("csvFilePath");

		String resultantcsvFilePath=null;
		// Your program element business logic goes here ...
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
			String timestamp = dateFormat.format(new Date());

			csvFilePath = csvFilePath + sheetName + timestamp + ".csv";
			resultantcsvFilePath = sheetName + timestamp + ".csv";

			try {
				FileWriter fileWriter = new FileWriter(csvFilePath);
				CSVWriter csvWriter = new CSVWriter(fileWriter);

				// Write an empty line to create an empty CSV file
				csvWriter.writeNext(new String[] {});

				csvWriter.close();
				fileWriter.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

			convertSheetToCSV(excelFilePath, sheetName, csvFilePath);
			nlpResponseModel.setStatus(CommonConstants.pass);
			nlpResponseModel.setMessage(sheetName + " is succesfully converted into CSV file");

		} catch (Exception e) {
			nlpResponseModel.setStatus(CommonConstants.fail);
			nlpResponseModel.setMessage(sheetName + " is failed to converted into CSV file");
		}

		nlpResponseModel.getAttributes().put("resultantCsvFilePath", resultantcsvFilePath);
		return nlpResponseModel;
	}

	public static void convertSheetToCSV(String excelFilePath, String sheetName, String csvFilePath) {
		try {
			FileInputStream fileInputStream = new FileInputStream(new File(excelFilePath));
			Workbook workbook = new XSSFWorkbook(fileInputStream);

			Sheet sheet = workbook.getSheet(sheetName);

			FileWriter fileWriter = new FileWriter(csvFilePath);
			CSVWriter csvWriter = new CSVWriter(fileWriter);

			Iterator<Row> rowIterator = sheet.iterator();
			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();
				String[] rowData = new String[row.getLastCellNum()];

				Iterator<Cell> cellIterator = row.iterator();
				int i = 0;
				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();
					rowData[i++] = getCellValue(cell);
				}

				csvWriter.writeNext(rowData);
			}

			csvWriter.close();
			fileWriter.close();

			System.out.println("Conversion successful. CSV file created at: " + csvFilePath);

			workbook.close();
			fileInputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static String getCellValue(Cell cell) {
		switch (cell.getCellType()) {
		case STRING:
			return cell.getStringCellValue();
		case NUMERIC:
			if (DateUtil.isCellDateFormatted(cell)) {
				return cell.getDateCellValue().toString();
			} else {
				return String.valueOf(cell.getNumericCellValue());
			}
		case BOOLEAN:
			return String.valueOf(cell.getBooleanCellValue());
		case BLANK:
			return "";
		default:
			return "";
		}
	}

}