package Logics;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class test {

	public static void main(String[] args) throws InterruptedException {
	//	WebDriver driver = new ChromeDriver();
		List<String> admissinDate= new ArrayList();
		admissinDate.add("02 Jun 2024");
		admissinDate.add("03 Jun 2024");
		admissinDate.add("01 Jun 2024");
		admissinDate.add("31 May 2024");
		admissinDate.add("30 May 2024");
		admissinDate.add("29 May 2024");
		admissinDate.add("27 May 2024");
		admissinDate.add("26 May 2024");
		admissinDate.add("04 Jun 2024");
		admissinDate.add("25 May 2024");
		admissinDate.add("24 May 2024");
		admissinDate.add("23 May 2024");
		admissinDate.add("22 May 2024");
		admissinDate.add("21 May 2024");
		admissinDate.add("19 May 2024");
		admissinDate.add("18 May 2024");
		admissinDate.add("16 May 2024");
		admissinDate.add("28 May 2024");
		admissinDate.add("17 May 2024");
		
		List<String> dischargeDate= new ArrayList();
		dischargeDate.add("03 Jun 2024");
		dischargeDate.add("04 Jun 2024");
		dischargeDate.add("02 Jun 2024");
		dischargeDate.add("01 Jun 2024");
		dischargeDate.add("31 May 2024");
		dischargeDate.add("30 May 2024");
		dischargeDate.add("28 May 2024");
		dischargeDate.add("27 May 2024");
		dischargeDate.add("05 Jun 2024");
		dischargeDate.add("26 May 2024");
		dischargeDate.add("25 May 2024");
		dischargeDate.add("24 May 2024");
		dischargeDate.add("23 May 2024");
		dischargeDate.add("22 May 2024");
		dischargeDate.add("21 May 2024");
		dischargeDate.add("20 May 2024");
		dischargeDate.add("19 May 2024");
		dischargeDate.add("17 May 2024");
		dischargeDate.add("29 May 2024");
		dischargeDate.add("18 May 2024");

			
			
			Integer admisionMinusDate = 2;
			Integer dischargeMinusDate = 4;
			Integer difference=admisionMinusDate-dischargeMinusDate;


			String str1 = admisionMinusDate.toString();
			String substringToReplace = "-";
			String str2 = dischargeMinusDate.toString();
			
			String replacedstr1 = str1.replace(substringToReplace, "");	
			admisionMinusDate = Integer.parseInt(replacedstr1);
			
			String replacedstr2 = str2.replace(substringToReplace, "");
			dischargeMinusDate = Integer.parseInt(replacedstr2);
			
			
			DateTimeFormatter format=DateTimeFormatter.ofPattern("dd MMM yyyy");
	        LinkedHashMap<String,Integer> map=new LinkedHashMap<String, Integer>();
			
			
			LocalDate day = LocalDate.now();

			LocalDate userStartDate = LocalDate.now().minusDays(admisionMinusDate);
	        LocalDate userEndDate = LocalDate.now().minusDays(dischargeMinusDate);
	        System.out.println(userStartDate);
	        System.out.println(userEndDate);
	        
	        
			while (isDateOverlap(userStartDate, userEndDate, admissinDate, dischargeDate, format)) {
				
				admisionMinusDate++;
				dischargeMinusDate++;
                userStartDate = LocalDate.now().minusDays(admisionMinusDate);
                userEndDate = LocalDate.now().minusDays(dischargeMinusDate);
            }

            map.put("Date of Admission", admisionMinusDate);
            map.put("Date of Discharge", admisionMinusDate-difference);
            System.out.println(map);
            System.out.println(admisionMinusDate);
			System.out.println(dischargeMinusDate);
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
