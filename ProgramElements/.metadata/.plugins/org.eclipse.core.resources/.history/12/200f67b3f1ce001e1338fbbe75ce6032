package bussiness_logic;

	import com.tyss.optimize.common.util.CommonConstants;
	import com.tyss.optimize.nlp.util.Nlp;
	import com.tyss.optimize.nlp.util.NlpException;
	import com.tyss.optimize.nlp.util.NlpRequestModel;
	import com.tyss.optimize.nlp.util.NlpResponseModel;
	import com.tyss.optimize.nlp.util.annotation.InputParam;
	import com.tyss.optimize.nlp.util.annotation.InputParams;
	import com.tyss.optimize.nlp.util.annotation.ReturnType;
	import java.util.Map;
	import java.io.FileInputStream;
	import java.io.FileOutputStream;
	import java.io.IOException;
	import java.io.InputStream;
	import java.util.ArrayList;
	import java.util.List;

	import org.apache.poi.ss.usermodel.ClientAnchor;
	import org.apache.poi.ss.usermodel.CreationHelper;
	import org.apache.poi.ss.usermodel.Drawing;
	import org.apache.poi.ss.usermodel.Sheet;
	import org.apache.poi.ss.usermodel.Workbook;
	import org.apache.poi.util.IOUtils;
	import org.apache.poi.xssf.usermodel.XSSFPicture;
	import org.apache.poi.xssf.usermodel.XSSFWorkbook;
	import org.springframework.stereotype.Component;
	
	@Component("LIC14952_PJT1001_PE_NLP2908c46a-9f6a-46bc-854e-ae0b384f5f76")
public class AddImageToExcel implements Nlp {
	    @InputParams({@InputParam(name = "imageFilepath", type = "java.lang.String"),
	    @InputParam(name = "excelFilepath", type = "java.lang.String"),
	    @InputParam(name = "sheetName", type = "java.lang.String")})
	    @ReturnType(name = "result", type = "java.lang.Boolean")

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
	          String imageFilepath = (String) attributes.get("imageFilepath");
	          String excelFilepath = (String) attributes.get("excelFilepath");
	          String sheetName = (String) attributes.get("sheetName");
	          boolean result=true;

	          // Your program element business logic goes here ...
	          try {
	        	  
	        	// Create a new Excel workbook and sheet
	              Workbook workbook = new XSSFWorkbook();
	              Sheet sheet = workbook.createSheet(sheetName);

	              // Load the image file from the file system
	              InputStream inputStream = new FileInputStream(imageFilepath);
	              byte[] bytes = IOUtils.toByteArray(inputStream);
	              int pictureIndex = workbook.addPicture(bytes, Workbook.PICTURE_TYPE_JPEG);

	              // Create a drawing object and anchor it to a cell
	              CreationHelper helper = workbook.getCreationHelper();
	              Drawing<?> drawing = sheet.createDrawingPatriarch();
	              ClientAnchor anchor = helper.createClientAnchor();
	              anchor.setCol1(0);  // Column index where the image will be placed
	              anchor.setRow1(0);  // Row index where the image will be placed

	              // Create a picture and add it to the sheet
	              XSSFPicture picture = (XSSFPicture) drawing.createPicture(anchor, pictureIndex);
	              picture.resize();  // Automatically resize the image to fit the cell

	              // Save the Excel workbook to a file
	              FileOutputStream fileOut = new FileOutputStream(excelFilepath);
	              workbook.write(fileOut);
	              fileOut.close();
	              result=true;
	       		  nlpResponseModel.setStatus(CommonConstants.pass);
	       		  nlpResponseModel.setMessage("Image added to Excel sheet successfully.");

	          }catch (Exception e) {
	        	  result=false;
	       		  nlpResponseModel.setStatus(CommonConstants.fail);
	       		  nlpResponseModel.setMessage("Adding Image to Excel sheet failed.");		}
	          //String string3 = "Return Value";
	          nlpResponseModel.getAttributes().put("result", result);
	          return nlpResponseModel;
	      }
	  } 




