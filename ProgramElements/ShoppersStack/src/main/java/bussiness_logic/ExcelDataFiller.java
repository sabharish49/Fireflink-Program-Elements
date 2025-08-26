package bussiness_logic;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.*;

public class ExcelDataFiller {

    // Method to fill data into the existing Excel file based on headers
    public static void fillExcelData(String filePath, String[][] data) {
        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fis);
             FileOutputStream fos = new FileOutputStream(filePath)) {

            // Get the first sheet (assuming data is in the first sheet)
            Sheet sheet = workbook.getSheetAt(0);

            // Read headers from the first row (row 0)
            Row headerRow = sheet.getRow(0);
            if (headerRow == null) {
                System.out.println("No headers found!");
                return;
            }

            // Map column names to their column index
            Map<String, Integer> headerMap = new HashMap<>();
            for (int i = 0; i < headerRow.getPhysicalNumberOfCells(); i++) {
                String header = headerRow.getCell(i).getStringCellValue().trim();
                headerMap.put(header, i);  // Mapping header name to column index
            }

            // Start filling data from row 1 (since row 0 contains headers)
            for (int i = 0; i < data.length; i++) {
                Row dataRow = sheet.createRow(i + 1); // Row index starts from 1 for data
                for (int j = 0; j < data[i].length; j++) {
                    String header = headerRow.getCell(j).getStringCellValue().trim();

                    // Check if the column header exists in the headerMap
                    if (headerMap.containsKey(header)) {
                        // Get the column index for the header
                        int columnIndex = headerMap.get(header);
                        Cell cell = dataRow.createCell(columnIndex);
                        cell.setCellValue(data[i][j]); // Set the value for the corresponding header
                    }
                }
            }

            // Write the updated workbook to the file
            workbook.write(fos);
            System.out.println("Excel file updated successfully!");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // Example data to fill based on the existing headers
        String[][] data = {
            {"John", "30", "New York"}, // Row 1 data
            {"Alice", "25", "London"},  // Row 2 data
            {"Bob", "35", "Paris"}      // Row 3 data
        };

        // Path to the existing Excel file
        String filePath = "C:/Users/User/Downloads/Invoice.xls";

        // Fill the data into the Excel file
        fillExcelData(filePath, data);
    }
}
