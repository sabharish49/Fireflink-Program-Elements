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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.stereotype.Component;

@Component("LIC20128_PJT1001_PE_NLPc85dd3d4-20c1-4f81-ac68-44c8cf5dc190")
public class VerifyTheDatesArePreviouslyClaimed implements Nlp {
    @InputParams({@InputParam(name = "admisionList", type = "java.util.List"), @InputParam(name = "dischargeList", type = "java.util.List"),
    	@InputParam(name = "admisionMinusDate", type = "java.lang.Integer"),@InputParam(name = "dischargeMinusDate", type = "java.lang.Integer")})
    @ReturnType(name = "map", type = "java.util.LinkedHashMap")

      public NlpResponseModel execute(NlpRequestModel nlpRequestModel) throws NlpException {
        
          NlpResponseModel nlpResponseModel = new NlpResponseModel();
          Map<String, Object> programElementsInput = nlpRequestModel.getAttributes();
          
          List<String> admisionList = (List<String>)programElementsInput.get("admisionList");
          List<String> dischargeList = (List<String>)programElementsInput.get("dischargeList");
          Integer admisionMinusDate = (Integer) programElementsInput.get("admisionMinusDate");
          Integer dischargeMinusDate = (Integer) programElementsInput.get("dischargeMinusDate");
          
          LinkedHashMap<String,Integer> map=new LinkedHashMap<String, Integer>();
          try {
        	
  			


  			String str1 = admisionMinusDate.toString();
  			String substringToReplace = "-";
  			String str2 = dischargeMinusDate.toString();
  			
  			String replacedstr1 = str1.replace(substringToReplace, "");	
  			admisionMinusDate = Integer.parseInt(replacedstr1);
  			
  			String replacedstr2 = str2.replace(substringToReplace, "");
  			dischargeMinusDate = Integer.parseInt(replacedstr2);
  			
  			
  			DateTimeFormatter format=DateTimeFormatter.ofPattern("dd MMM yyyy");
  	        
  			
  			
  			LocalDate day = LocalDate.now();

  			LocalDate userStartDate = LocalDate.now().minusDays(admisionMinusDate);
  	        LocalDate userEndDate = LocalDate.now().minusDays(dischargeMinusDate);
  	      
  	        
  	        
  			while (isDateOverlap(userStartDate, userEndDate, admisionList, dischargeList, format)) {
  				
  				admisionMinusDate++;
  				dischargeMinusDate++;
                  userStartDate = LocalDate.now().minusDays(admisionMinusDate);
                  userEndDate = LocalDate.now().minusDays(dischargeMinusDate);
              }

              map.put("Date of Admission", admisionMinusDate);
              map.put("Date of Discharge", dischargeMinusDate);
              System.out.println(map);
              
  	
              nlpResponseModel.setMessage("The date has been verified and does not match the previously claimed date.");
              nlpResponseModel.setStatus(CommonConstants.pass);
              }
          catch(Exception e) {
           // Your program element Exception goes here ...
              nlpResponseModel.setMessage("Unable to verify the date "+e.getMessage());
              nlpResponseModel.setStatus(CommonConstants.fail);
              }

          // Your program element business logic ends here ...
          nlpResponseModel.getAttributes().put("map", map);
          return nlpResponseModel;
      }
    
    
    public static String date(int admissionMinusDate,int dischargeMinusDate,DateTimeFormatter format) {
		LocalDate alocalDate=LocalDate.now().minusDays(admissionMinusDate);
		LocalDate dlocalDate=LocalDate.now().minusDays(dischargeMinusDate);
		String adateformat = alocalDate.format(format);
		String ddateformat = dlocalDate.format(format);
		
		return adateformat;
	}
    private static boolean isDateOverlap(LocalDate startDate, LocalDate endDate, List<String> admissionDates, List<String> dischargeDates, DateTimeFormatter format) {
		for (int i = 0; i < admissionDates.size(); i++) {
			LocalDate admissionDate = LocalDate.parse(admissionDates.get(i).trim(), format);
			LocalDate dischargeDate = LocalDate.parse(dischargeDates.get(i).trim(), format);

			boolean overlap = (startDate.equals(admissionDate) || startDate.equals(dischargeDate) || 
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