package bussiness_logics;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
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

public class InvoiceTemplate implements Nlp {

    @InputParams({ 
        @InputParam(name = "FilePath", type = "java.lang.String"),
        @InputParam(name = "SellerGst", type = "java.lang.String"),
        @InputParam(name = "BuyerGst", type = "java.lang.String"),
        @InputParam(name = "InvoiceAmount", type = "java.lang.String"),
        @InputParam(name = "gstPercentage", type = "java.lang.Integer")
    })
    @ReturnType(name = "result", type = "java.lang.Boolean")
    @Override
    public List<String> getTestParameters() throws NlpException {
        return new ArrayList<>();
    }

    @Override
    public StringBuilder getTestCode() throws NlpException {
        return new StringBuilder();
    }

    @Override
    public NlpResponseModel execute(NlpRequestModel nlpRequestModel) throws NlpException {

        NlpResponseModel nlpResponseModel = new NlpResponseModel();
        Map<String, Object> attributes = nlpRequestModel.getAttributes();
        String filePath = (String) attributes.get("FilePath");
        String sellerGst = (String) attributes.get("SellerGst");
        String buyerGst = (String) attributes.get("BuyerGst");
        String invoiceAmount = (String) attributes.get("InvoiceAmount");
        Integer gstPercentage = (Integer) attributes.get("gstPercentage");
       
        
        boolean result = true;

        try {
        	double invoiceAmnt = Double.parseDouble(invoiceAmount);
            double gstAmount = invoiceAmnt * (gstPercentage / 100.0);
            double totalAmount = invoiceAmnt + gstAmount;
            
            Random random = new Random();
            int randomNum = random.nextInt(100) + 1;
            
            LocalDate currentDate = LocalDate.now();
            String formattedDate = currentDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

           
            Map<String, String> data = new HashMap<>();
            data.put("invoice_number", "HIND-" + randomNum);
            data.put("invoice_type", "IN");
            data.put("buyer_gst", buyerGst);
            data.put("seller_gst", sellerGst);
            data.put("invoice_amount", invoiceAmount);
            data.put("gst_amount", String.format("%.2f", gstAmount));
            data.put("Total_Invoice_Amt", String.format("%.2f", totalAmount));
            data.put("invoice_date", formattedDate);

          
            fillExcelData(filePath, data);

            nlpResponseModel.setStatus(CommonConstants.pass);
            nlpResponseModel.setMessage("data written successfully");

        } catch (Exception e) {
            result = false;
            e.printStackTrace();
            nlpResponseModel.setStatus(CommonConstants.fail);
            nlpResponseModel.setMessage("Failed to write data: " + e.getMessage());
        }

        nlpResponseModel.getAttributes().put("result", result);
        return nlpResponseModel;
    }

    public static void fillExcelData(String filePath, Map<String, String> data) {
        Workbook workbook = null;
        Path originalPath = Paths.get(filePath);
        Path tempPath = Paths.get(filePath + ".temp");
        FileInputStream fis = null;

        try {
           
            try {
                fis = new FileInputStream(originalPath.toFile());
                workbook = WorkbookFactory.create(fis);
               
            } catch (Exception e) {
               
                workbook = new HSSFWorkbook();
                
                
                Sheet sheet = workbook.createSheet("Sheet1");
                Row headerRow = sheet.createRow(0);
                
                String[] headers = {
                    "invoice_number", "doc_no", "invoice_file", 
                    "invoice_type", "buyer_gst", "seller_gst", "invoice_amount", "gst_amount","Total_Invoice_Amt","invoice_date"
                };
                
               
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

            
            Sheet sheet = workbook.getSheetAt(0);
            Row headerRow = sheet.getRow(0);

            
            Map<String, Integer> headerIndexMap = new HashMap<>();
            for (int i = 0; i < headerRow.getPhysicalNumberOfCells(); i++) {
                String headerName = headerRow.getCell(i).getStringCellValue();
                headerIndexMap.put(headerName, i);
            }

            
            int lastRowNum = sheet.getLastRowNum();
            Row newRow = sheet.createRow(lastRowNum + 1);

           
            for (Map.Entry<String, String> entry : data.entrySet()) {
                String columnName = entry.getKey();
                String value = entry.getValue();
                
                
                Integer columnIndex = headerIndexMap.get(columnName);
                
               
                if (columnIndex != null) {
                    Cell cell = newRow.createCell(columnIndex);
                    cell.setCellValue(value);
                } else {
                    System.out.println("Header not found for: " + columnName);
                }
            }

           
            for (int i = 0; i < headerRow.getLastCellNum(); i++) {
                sheet.autoSizeColumn(i);
            }

            
            try (FileOutputStream fos = new FileOutputStream(tempPath.toFile())) {
                workbook.write(fos);
            }

           
            Files.move(tempPath, originalPath, StandardCopyOption.REPLACE_EXISTING);
            

        } catch (Exception e) {
            System.err.println("Error processing Excel file: " + e.getMessage());
            e.printStackTrace();
            
           
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



}
