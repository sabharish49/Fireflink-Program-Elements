
package Logics;

import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import com.tyss.optimize.nlp.util.annotation.InputParam;
import com.tyss.optimize.nlp.util.annotation.InputParams;
import com.tyss.optimize.nlp.util.annotation.ReturnType;
import java.util.Map;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.stereotype.Component;

public class VerifyTheDatesArePreviouslyClaimed implements Nlp {
    @InputParams({@InputParam(name = "listOfAdmissionDate", type = "java.util.List"),@InputParam(name = "listOfDischargeDate", type = "java.util.List"),@InputParam(name = "dateOfAdmission", type = "java.lang.Integer"),@InputParam(name = "dateOfDischarge", type = "java.lang.Integer")})
    @ReturnType(name = "map", type = "java.util.LinkedHashMap")

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
          List<String> listOfAdmissionDate = (List<String>) attributes.get("listOfAdmissionDate");
          List<String> listOfDischargeDate= (List<String>) attributes.get("listOfDischargeDate");
          Integer dateOfAdmission = (Integer) attributes.get("dateOfAdmission");
          Integer dateOfDischarge = (Integer) attributes.get("dateOfAdmission");
          
          DateTimeFormatter format=DateTimeFormatter.ofPattern("dd MMM yyyy");
          LinkedHashMap<String,Integer> map=new LinkedHashMap<String, Integer>();
         String str1 = dateOfAdmission.toString();
     	 String str2 = dateOfDischarge.toString();
      	 String substringToReplace = "-";
      	 
      	String replacedstr1 = str1.replace(substringToReplace, "");
      	String replacedstr2 = str2.replace(substringToReplace, "");
      	dateOfAdmission = Integer.parseInt(replacedstr1);
      	dateOfDischarge = Integer.parseInt(replacedstr2);
          
          
         try {
        	 
        	 LocalDate day = LocalDate.now();

 			LocalDate userStartDate = LocalDate.now().minusDays(dateOfAdmission);
 	        LocalDate userEndDate = LocalDate.now().minusDays(dateOfDischarge);
 	        
 			while (isDateOverlap(userStartDate, userEndDate, listOfAdmissionDate, listOfDischargeDate, format)) {
 				dateOfAdmission++;
 				dateOfDischarge++;
                 userStartDate = LocalDate.now().minusDays(dateOfAdmission);
                 userEndDate = LocalDate.now().minusDays(dateOfDischarge);
             }

             map.put("Date of Admission", dateOfAdmission);
             map.put("Date of Discharge", dateOfDischarge);
 			
 		
        	   nlpResponseModel.setMessage("The date has been verified and does not match the previously claimed date.");
        	   nlpResponseModel.setStatus(CommonConstants.pass);
		} catch (Exception e) {
			  nlpResponseModel.setMessage("Unable to verify the date"+e.getMessage());
       	   nlpResponseModel.setStatus(CommonConstants.fail);
		
          nlpResponseModel.getAttributes().put("map",map);
          return nlpResponseModel;
      }
            
      public static String date(int dateOfAdmission,int dateOfDischarge,DateTimeFormatter format) {
  		LocalDate alocalDate=LocalDate.now().minusDays(dateOfAdmission);
  		LocalDate dlocalDate=LocalDate.now().minusDays(dateOfDischarge);
  		String adateformat = alocalDate.format(format);
  		String ddateformat = dlocalDate.format(format);
  		
  		return adateformat;
  	}
      private static boolean isDateOverlap(LocalDate startDate, LocalDate endDate, List<String> admissionDates, List<String> dischargeDates, DateTimeFormatter format) {
          for (int i = 0; i < admissionDates.size(); i++) {
              LocalDate admissionDate = LocalDate.parse(admissionDates.get(i).trim(), format);
              LocalDate dischargeDate = LocalDate.parse(dischargeDates.get(i).trim(), format);

              boolean overlap =  (startDate.equals(admissionDate) || startDate.equals(dischargeDate) || 
                                  endDate.equals(admissionDate) || endDate.equals(dischargeDate) ||
                                 (startDate.isAfter(admissionDate) && startDate.isBefore(dischargeDate)) || 
                                 (endDate.isAfter(admissionDate) && endDate.isBefore(dischargeDate)) || 
                                 (admissionDate.isAfter(startDate) && admissionDate.isBefore(endDate)) || 
                                 (dischargeDate.isAfter(startDate) && dischargeDate.isBefore(endDate)));

              if (overlap) {
                  return true;
              }
          }
          return false;
      }
  } 