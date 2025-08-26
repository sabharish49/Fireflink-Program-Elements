package bussiness_logic;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.springframework.stereotype.Component;

import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import com.tyss.optimize.nlp.util.annotation.InputParam;
import com.tyss.optimize.nlp.util.annotation.InputParams;
import com.tyss.optimize.nlp.util.annotation.ReturnType;


@Component("LIC14952_PJT1001_PE_NLP581142e1-54a9-43cc-b37f-c9ecc784bf9c")
public class ConvertingPdfToWord implements Nlp {
    @InputParams({@InputParam(name = "pdfFilePath", type = "java.lang.String"), @InputParam(name = "wordFilePath", type = "java.lang.String")})
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
          String pdfFilePath = (String) attributes.get("pdfFilePath");
          String wordFilePath = (String) attributes.get("wordFilePath");
          boolean result=true;

          // Your program element business logic goes here ...
          try {
			
		
			
		
          File file = new File(pdfFilePath);   
	    	PDDocument pdfDocument = PDDocument.load(file);   

	            
	            PDFTextStripper pdfStripper = new PDFTextStripper();

	            
	            String pdfText = pdfStripper.getText(pdfDocument);

	            
	            XWPFDocument wordDocument = new XWPFDocument();

	            
	            XWPFParagraph paragraph = wordDocument.createParagraph();

	            
	            XWPFRun run = paragraph.createRun();
	            run.setText(pdfText);

	            
	            FileOutputStream out = new FileOutputStream(wordFilePath);
	            wordDocument.write(out);
	            out.close();
	            
	            pdfDocument.close();
	            result=true;
	            nlpResponseModel.setStatus(CommonConstants.pass);
	            nlpResponseModel.setMessage("converted successfully");
          }
          catch (Exception e) {
        	  nlpResponseModel.setStatus(CommonConstants.fail);
	            nlpResponseModel.setMessage("not converted");
	            result=false;
			
		}
          

          //String string3 = "Return Value";
          nlpResponseModel.getAttributes().put("result", result);
          return nlpResponseModel;
      }
  } 
