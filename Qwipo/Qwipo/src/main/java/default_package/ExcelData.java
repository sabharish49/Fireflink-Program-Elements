package default_package;

import java.io.*;
import java.nio.file.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class ExcelData {

    public static void fillExcelData(String filePath, Map<String, String> data) {
        Workbook workbook = null;
        Path originalPath = Paths.get(filePath);
        Path tempPath = Paths.get(filePath + ".temp");
        FileInputStream fis = null;

        try {
            // Try to read existing file
            try {
                fis = new FileInputStream(originalPath.toFile());
                workbook = WorkbookFactory.create(fis);
                System.out.println("Successfully opened existing workbook");
            } catch (Exception e) {
                System.out.println("Creating new workbook due to: " + e.getMessage());
                // Create new workbook if reading fails
                workbook = new HSSFWorkbook();
                
                // Create and setup new sheet with headers (if file doesn't exist)
                Sheet sheet = workbook.createSheet("Sheet1");
                Row headerRow = sheet.createRow(0);
                
                String[] headers = {
                    "invoice_number", "doc_no", "invoice_file", 
                    "invoice_type", "buyer_gst", "seller_gst", "invoice_amount", "gst_amount","Total_Invoice_Amt","invoice_date"
                };
                
                // Create headers
                for (int i = 0; i < headers.length; i++) {
                    Cell cell = headerRow.createCell(i);
                    cell.setCellValue(headers[i]);
                }
                System.out.println("Created new sheet with headers");
            } finally {
                if (fis != null) {
                    fis.close();
                }
            }

            // Get the first sheet
            Sheet sheet = workbook.getSheetAt(0);
            Row headerRow = sheet.getRow(0);

            // Read the headers dynamically from the first row of the sheet
            Map<String, Integer> headerIndexMap = new HashMap<>();
            for (int i = 0; i < headerRow.getPhysicalNumberOfCells(); i++) {
                String headerName = headerRow.getCell(i).getStringCellValue();
                headerIndexMap.put(headerName, i);
            }

            // Get last row number (next empty row for data insertion)
            int lastRowNum = sheet.getLastRowNum();
            Row newRow = sheet.createRow(lastRowNum + 1);

            // Iterate through the data map and write to the appropriate columns
            for (Map.Entry<String, String> entry : data.entrySet()) {
                String columnName = entry.getKey();
                String value = entry.getValue();
                
                // Find the column index for the given header
                Integer columnIndex = headerIndexMap.get(columnName);
                
                // If column exists in the header, fill the data
                if (columnIndex != null) {
                    Cell cell = newRow.createCell(columnIndex);
                    cell.setCellValue(value);
                } else {
                    System.out.println("Header not found for: " + columnName);
                }
            }

            // Auto-size columns
            for (int i = 0; i < headerRow.getLastCellNum(); i++) {
                sheet.autoSizeColumn(i);
            }

            // Save to temp file first
            try (FileOutputStream fos = new FileOutputStream(tempPath.toFile())) {
                workbook.write(fos);
            }

            // Replace original file
            Files.move(tempPath, originalPath, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Excel file updated successfully!");

        } catch (Exception e) {
            System.err.println("Error processing Excel file: " + e.getMessage());
            e.printStackTrace();
            
            // Cleanup temp file if exists
            try {
                Files.deleteIfExists(tempPath);
            } catch (IOException cleanupError) {
                System.err.println("Error cleaning up temp file: " + cleanupError.getMessage());
            }
        } finally {
            try {
                if (workbook != null) {
                    workbook.close();
                }
            } catch (IOException e) {
                System.err.println("Error closing workbook: " + e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        try {
            // Input parameters
            String invoiceAmount = "10000";
            int gstPercentage = 18;
            String buyerGst = "10AABCU9355J1Z9";
            String sellerGst = "10AAAFG3767G1ZT";
            
            // File path
            String filePath = "C:/Users/User/Downloads/Invoice (5).xls";
            System.out.println("Writing to file: " + filePath);

            // Calculate values
            double invoiceAmnt = Double.parseDouble(invoiceAmount);
            double gstAmount = invoiceAmnt * (gstPercentage / 100.0);
            double totalAmount = invoiceAmnt + gstAmount;
            
            Random random = new Random();
            int randomNum = random.nextInt(100) + 1;
            
            LocalDate currentDate = LocalDate.now();
            String formattedDate = currentDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

            // Create a map where keys are the column names (headers) and values are the data
            Map<String, String> data = new HashMap<>();
            data.put("invoice_number", "HIND-" + randomNum);
            data.put("invoice_type", "IN");
           // data.put("invoice_file", "");
            data.put("buyer_gst", buyerGst);
            data.put("seller_gst", sellerGst);
            data.put("invoice_amount", invoiceAmount);
            data.put("gst_amount", String.format("%.2f", gstAmount));
            data.put("Total_Invoice_Amt", String.format("%.2f", totalAmount));
            data.put("invoice_date", formattedDate);

            // Call the method to fill the Excel file with data
            fillExcelData(filePath, data);

        } catch (Exception e) {
            System.err.println("Error in main method: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
