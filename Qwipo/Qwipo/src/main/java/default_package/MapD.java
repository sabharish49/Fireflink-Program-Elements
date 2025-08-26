package default_package;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class MapD {
	static String excelFile = "C:\\Users\\User\\Downloads\\ABHI_TestData.xlsx";
	static String sheetName = "NXT_TestInputs";
	static String uniqueColumn = "Test Case Id";
	static String UniqueValue = "NXT_20";
	static String targetFile = "C:\\Users\\User\\Downloads\\Activ One Calculators\\Activ One Calculators\\1.10 Activ One - MAX_NXT.xlsm";
	static String TargetSheet = "MI";
	public static  void fillExcel() throws IOException {
	        FileInputStream fis = new FileInputStream(excelFile);
	        Workbook workbook = new XSSFWorkbook(fis);
	        Sheet sheet = workbook.getSheet(sheetName);
	        Map dataMap = new LinkedHashMap<>();
	
	        if (sheet == null) {
	            System.out.println("Sheet '" + sheetName + "' not found.");
	            workbook.close();
	            System.out.println(dataMap);
	        }
	
	        Row headerRow = sheet.getRow(0);
	        if (headerRow == null) {
	            System.out.println("Header row is missing!");
	            workbook.close();
	            System.out.println(dataMap);
	        }
	
	        int uniqueColIndex = -1;
	        Map<Integer, String> headers = new HashMap<>();
	
	        for (Cell cell : headerRow) {
	            String header = cell.getStringCellValue().trim();
	            headers.put(cell.getColumnIndex(), header);
	            if (header.equalsIgnoreCase(uniqueColumn)) {
	                uniqueColIndex = cell.getColumnIndex();
	            }
	        }
	
	        if (uniqueColIndex == -1) {
	            System.out.println("Unique column '" + uniqueColumn + "' not found.");
	            workbook.close();
	            System.out.println(dataMap);
	        }
	
	        Row targetRow = null;
	
	        for (Row row : sheet) {
	            Cell srNoCell = row.getCell(uniqueColIndex);
	            if (srNoCell != null) {
	                String cellValue = "";
	
	                if (srNoCell.getCellType() == CellType.NUMERIC) {
	                    cellValue = String.valueOf((long) srNoCell.getNumericCellValue());
	                } else if (srNoCell.getCellType() == CellType.STRING) {
	                    cellValue = srNoCell.getStringCellValue().trim();
	                }
	
	                if (cellValue.equals(UniqueValue)) {
	                    targetRow = row;
	                    break;
	                }
	            }
	        }
	
	        if (targetRow == null) {
	            System.out.println("No data found for Sr.No. = " + UniqueValue);
	            workbook.close();
	            System.out.println(dataMap);
	        }
	
	        for (Cell cell : targetRow) {
	            String key = headers.get(cell.getColumnIndex());
	            if (key != null) {
	                String value;
	                if (cell.getCellType() == CellType.NUMERIC) {
	                    value = String.valueOf(cell.getNumericCellValue());
	                } else {
	                    value = cell.getStringCellValue();
	                }
	
	                if (value.contains("||")) {
	                    dataMap.put(key, Arrays.asList(value.split("\\|\\|")));
	                } else {
	                    dataMap.put(key, value);
	                }
	            }
	        }
	
	        workbook.close();
	        System.out.println(dataMap);
	    }
}

