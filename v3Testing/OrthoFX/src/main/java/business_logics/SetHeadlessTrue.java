
package business_logics;

import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.util.Map;

import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import com.tyss.optimize.nlp.util.annotation.InputParam;
import com.tyss.optimize.nlp.util.annotation.InputParams;

import lombok.extern.slf4j.Slf4j;



@Slf4j
public class SetHeadlessTrue implements Nlp {
    @InputParams({@InputParam(name = "Text", type = "java.lang.String")})


      public NlpResponseModel execute(NlpRequestModel nlpRequestModel) throws NlpException {

          Map<String, Object> programElementsInput = nlpRequestModel.getAttributes();
          String string = (String) programElementsInput.get("Text");


          NlpResponseModel nlpResponseModel = new NlpResponseModel();
          try {
          	System.setProperty("java.awt.headless", "false");

        	  if (!GraphicsEnvironment.isHeadless()) {
                  Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
               clipboard.setContents(new StringSelection(string), null);
               log.info("Inside >!GraphicsEnvironment.isHeadless()< block");
              } 
        	System.setProperty("java.awt.headless", "true");
        	  nlpResponseModel.setStatus(CommonConstants.pass);
              nlpResponseModel.setMessage(string+" set as Clipboard content");
          }
          catch(Exception e) {
    
        	  nlpResponseModel.setStatus(CommonConstants.fail);
              nlpResponseModel.setMessage("Failed to set clipboard content");
          }  
          return nlpResponseModel;
      }

  } 