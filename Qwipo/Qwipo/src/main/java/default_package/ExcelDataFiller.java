package default_package;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;  // For .xls files
import org.apache.poi.xssf.usermodel.XSSFWorkbook;  // For .xlsx files

public class ExcelDataFiller {

    // Method to fill Excel data dynamically based on headers
    public static void fillExcelData(String filePath, Map<String, String> dataMap) {
        try (FileInputStream fis = new FileInputStream(filePath)) {
            Workbook workbook;

            // Check the file extension and choose the appropriate workbook implementation
            if (filePath.endsWith(".xlsx")) {
                workbook = new XSSFWorkbook(fis);  // For .xlsx files
            } else if (filePath.endsWith(".xls")) {
                workbook = new HSSFWorkbook(fis);  // For .xls files
            } else {
                throw new IllegalArgumentException("Unsupported file format. Please provide a valid .xls or .xlsx file.");
            }

            // Get the first sheet from the workbook
            Sheet sheet = workbook.getSheetAt(0);

            // Get the header row (first row of the sheet)
            Row headerRow = sheet.getRow(0);
            if (headerRow == null) {
                System.out.println("No headers found!");
                return;
            }

            // Map to store the column index of each header
            Map<String, Integer> headerMap = new HashMap<>();
            for (int i = 0; i < headerRow.getPhysicalNumberOfCells(); i++) {
                String header = headerRow.getCell(i).getStringCellValue().trim();
                headerMap.put(header, i);  // Store header and its column index
            }

            // Create a new row to insert data (assuming data starts from row 1)
            Row dataRow = sheet.createRow(1);

            // Loop through each entry in the dataMap and fill corresponding cells
            for (Map.Entry<String, String> entry : dataMap.entrySet()) {
                String header = entry.getKey();
                String value = entry.getValue();

                // If the header exists in the header map, insert the data in the correct cell
                if (headerMap.containsKey(header)) {
                    int columnIndex = headerMap.get(header);
                    Cell cell = dataRow.createCell(columnIndex);
                    cell.setCellValue(value);  // Set the value in the cell
                }
            }

            // Write the updated workbook back to the file
            try (FileOutputStream fos = new FileOutputStream(filePath)) {
                workbook.write(fos);
            }
            System.out.println("Excel file updated successfully!");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // Generate random invoice number
        Random random = new Random();
        int randomNum = random.nextInt(100) + 1;

        // Sample data for the invoice
        String invoiceAmount = "10000";  // Input 1: Invoice amount
        double invoiceAmnt = Double.parseDouble(invoiceAmount);
        int gstPercentage = 18;  // Input 2: GST percentage
        double gstAmount = invoiceAmnt * (gstPercentage / 100.0);
        double totalAmount = invoiceAmnt + gstAmount;
        LocalDate currentDate = LocalDate.now();  // Current date for the invoice

        // Buyer and Seller GST details
        String buyerGst = "37AAACC1206D2ZE";  // Input 3: Buyer's GST number
        String sellerGst = "09AAACC1206D5ZA";  // Input 4: Seller's GST number

        // Create a map to store the header-to-value mapping
        Map<String, String> dataMap = new HashMap<>();
        dataMap.put("Invoice Number", "HIND-" + randomNum);
        dataMap.put("Buyer GST", buyerGst);
        dataMap.put("Seller GST", sellerGst);
        dataMap.put("Invoice Amount", invoiceAmount);
        dataMap.put("GST Amount", String.format("%.2f", gstAmount));
        dataMap.put("Total Amount", String.format("%.2f", totalAmount));
        dataMap.put("Country", "IN");
        dataMap.put("Date", currentDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));

        // Excel file path (adjust path as needed)
        String filePath = "C:/Users/User/Downloads/Invoice.xlsx";  // Input 5

        // Call the method to fill the Excel data
        fillExcelData(filePath, dataMap);
    }
}
