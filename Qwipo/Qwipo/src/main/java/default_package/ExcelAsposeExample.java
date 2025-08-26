package default_package;
import com.aspose.cells.*;

import jxl.Workbook;

public class ExcelAsposeExample {
    public static void writeExcelData(String filePath, String[][] data) {
        try {
            // Create a new workbook
            Workbook workbook = new Workbook();
            Worksheet worksheet = workbook.getWorksheets().get(0);

            // Write data to the worksheet
            for (int row = 0; row < data.length; row++) {
                for (int col = 0; col < data[row].length; col++) {
                    worksheet.getCells().get(row, col).setValue(data[row][col]);
                }
            }

            // Save the workbook to the file
            workbook.save(filePath);

            System.out.println("Excel file created successfully using Aspose.Cells!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // Example data
        String[][] data = {
            {"Invoice Number", "Buyer GST", "Seller GST", "Amount"},
            {"HIND-123", "37AAACC1206D2ZE", "09AAACC1206D5ZA", "10000"}
        };

        // File path to save the Excel file
        String filePath = "path_to_your_output_file.xls";
        writeExcelData(filePath, data);
    }
}
