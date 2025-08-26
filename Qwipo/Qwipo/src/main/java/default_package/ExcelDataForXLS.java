package default_package;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;  // Import WorkbookFactory

public class ExcelDataForXLS {

    public static void fillExcelData(String filePath, String[][] data) {
        try (FileInputStream fis = new FileInputStream(filePath)) {
            // Create the Workbook using WorkbookFactory (handles both .xls and .xlsx files)
            Workbook workbook = WorkbookFactory.create(fis);  // Automatically detects file type

            // Get the first sheet from the workbook
            Sheet sheet = workbook.getSheetAt(0);

            // Get the header row (first row of the sheet)
            Row headerRow = sheet.getRow(0);
            if (headerRow == null) {
                System.out.println("No headers found!");
                return;
            }

            // Map to store the column index for each header
            Map<String, Integer> headerMap = new HashMap<>();
            for (int i = 0; i < headerRow.getPhysicalNumberOfCells(); i++) {
                String header = headerRow.getCell(i).getStringCellValue().trim();
                headerMap.put(header, i);  // Store header and its column index
            }

            // Iterate over the data to fill the Excel sheet
            for (int i = 0; i < data.length; i++) {
                Row dataRow = sheet.createRow(i + 1);  // Create a new row for each data entry
                for (int j = 0; j < data[i].length; j++) {
                    String header = headerRow.getCell(j).getStringCellValue().trim();

                    // If the header exists in the map, write data into the corresponding cell
                    if (headerMap.containsKey(header)) {
                        int columnIndex = headerMap.get(header);
                        Cell cell = dataRow.createCell(columnIndex);
                        cell.setCellValue(data[i][j]);  // Set the value of the cell
                    }
                }
            }

            // Save the modified workbook back to the file
            try (FileOutputStream fos = new FileOutputStream(filePath)) {
                workbook.write(fos);
            }
            System.out.println("Excel file updated successfully!");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // Sample data for the invoice
        Random random = new Random();
        int randomNum = random.nextInt(100) + 1;  // Random invoice number
        String invoiceAmount = "10000";  // Input 1: Invoice amount
        double invoiceAmnt = Double.parseDouble(invoiceAmount); 
        int gstPercentage = 18;  // Input 2: GST percentage
        double gstAmount = invoiceAmnt * (gstPercentage / 100.0);
        double totalAmount = invoiceAmnt + gstAmount;
        LocalDate currentDate = LocalDate.now();  // Current date for the invoice
        
        String buyerGst = "37AAACC1206D2ZE";  // Input 3: Buyer's GST number
        String sellerGst = "09AAACC1206D5ZA";  // Input 4: Seller's GST number

        // Data to be written to Excel
        String[][] data = {
            {"HIND-" + randomNum, "", "", "IN", buyerGst, sellerGst, invoiceAmount, 
             String.format("%.2f", gstAmount), String.format("%.2f", totalAmount), 
             currentDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))}
        };

        // Excel file path (adjust path as needed)
        String filePath = "C:/Users/User/Downloads/Invoice.xls";  // Input 5

        // Call the method to fill the Excel data
        fillExcelData(filePath, data);
    }
}
