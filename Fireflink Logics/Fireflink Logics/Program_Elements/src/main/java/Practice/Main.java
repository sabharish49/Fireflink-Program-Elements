package Practice;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Main {
    public static void main(String[] args) {
        String jsonString = "{\n" +
                "    \"lead\": {\n" +
                "        \"firstName\": \"Fireflink\",\n" +
                "        \"pincode\": \"313332\",\n" +
                "        \"village\": \"asan\",\n" +
                "        \"employmentType\": \"Cash Salaried\",\n" +
                "        \"industryCategory\": \"Agri - Coconut Trading\",\n" +
                "        \"leadSource\": \"Cold Calling\",\n" +
                "        \"customerFeedback\": \"Interested\"\n" +
                "    },\n" +
                "    \"application\": {\n" +
                "        \"personalDetails\": {\n" +
                "            \"documentCategory\": \"Identity\",\n" +
                "            \"documentType\": \"Pan\",\n" +
                "            \"fatherName\": \"Sample\",\n" +
                "            \"motherMaidenName\": \"MotherName\",\n" +
                "            \"gender\": \"Male\",\n" +
                "            \"maritalStatus\": \"Single\",\n" +
                "            \"religion\": \"Hindu\",\n" +
                "            \"socialCategory\": \"General\",\n" +
                "            \"drivingLicence\": \"Sample\",\n" +
                "            \"votingId\": \"AGHT\",\n" +
                "            \"coApplicant\": \"coApplicant\",\n" +
                "            \"lastName\": \"t\"\n" +
                "        },\n" +
                "        \"addressDetails\": {\n" +
                "            \"documentType\": \"Passport\",\n" +
                "            \"nameOfOffice\": \"HGC\",\n" +
                "            \"gstNumber\": \"ABC23\",\n" +
                "            \"village\": \"Mad B.O\",\n" +
                "            \"residenceOccupancy\": \"Self Occupied\",\n" +
                "            \"location\": \"BUSINESS\"\n" +
                "        }\n" +
                "    },\n" +
                "    \"loanDetails\": {\n" +
                "        \"productPurpose\": \"Business\",\n" +
                "        \"endUse\": \"Purchase of stock\",\n" +
                "        \"endUseRemark\": \"Remarks\",\n" +
                "        \"affordableEmi\": \"300000\",\n" +
                "        \"collateralType\": \"Residential\",\n" +
                "        \"sidbiClassification\": \"1201-DAIRYING\",\n" +
                "        \"loanTenure\": \"48\",\n" +
                "        \"roi\": \"24.5\",\n" +
                "        \"paymentType\": \"UIP\"\n" +
                "    },\n" +
                "    \"BCM Verification\": {\n" +
                "        \"residenceVerification\": {\n" +
                "            \"houseDetails\": {\n" +
                "                \"residenceOccupancyStatus\": \"Govt. Accomodation\",\n" +
                "                \"residenceInPresentHouse\": \"45\",\n" +
                "                \"residenceInPresentArea\": \"45\",\n" +
                "                \"residencyType\": \"Flat\",\n" +
                "                \"noOfFloors\": \"6\",\n" +
                "                \"sizeInSqFt\": \"1000\",\n" +
                "                \"totalNoOfFloors\": \"6\",\n" +
                "                \"roofType\": \"Tilled\",\n" +
                "                \"accessToResidence\": \"Difficult To Access\",\n" +
                "                \"remark\": \"Residence Verification\"\n" +
                "            },\n" +
                "            \"familyDetails\": {\n" +
                "                \"familyName\": \"Stephen\",\n" +
                "                \"relationshipWithMainApplicant\": \"SON\",\n" +
                "                \"age\": \"13\",\n" +
                "                \"mobileNumber\": \"9876543234\",\n" +
                "                \"qualification\": \"Graduate\",\n" +
                "                \"status\": \"Studying\"\n" +
                "            },\n" +
                "            \"houseHoldExpense\": {\n" +
                "                \"rent\": \"10000\",\n" +
                "                \"mobile\": \"7000\",\n" +
                "                \"food\": \"3000\",\n" +
                "                \"travelExpense\": \"2500\",\n" +
                "                \"education\": \"6000\",\n" +
                "                \"misc\": \"1000\"\n" +
                "            },\n" +
                "            \"neighbourCheck\": {\n" +
                "                \"nameOfNeighbour\": \"Oppenheimer\",\n" +
                "                \"address1\": \"Indiqube South Summit\",\n" +
                "                \"address2\": \"South end circle\",\n" +
                "                \"pincode\": \"313332\",\n" +
                "                \"village\": \"Tikar B.O\",\n" +
                "                \"relationshipWithCustomer\": \"Good\",\n" +
                "                \"applicantBusinessDailyFootFall\": \"Low\",\n" +
                "                \"finalVerificationStatus\": \"Positive\",\n" +
                "                \"remark\": \"just a Neighbour\"\n" +
                "            }\n" +
                "        },\n" +
                "        \"businessVerification\": {\n" +
                "            \"employmentDetails\": {\n" +
                "                \"employmentInfo\": {\n" +
                "                    \"industryType\": \"Manufacturing\",\n" +
                "                    \"employmentType\": \"Self Employed Professional\",\n" +
                "                    \"industryCategory\": \"Agri - Flour Mill\",\n" +
                "                    \"designation\": \"Pensioner\",\n" +
                "                    \"organizationName\": \"TestYantra\",\n" +
                "                    \"address1\": \"Indiqube South Summit\",\n" +
                "                    \"address2\": \"South end circle\",\n" +
                "                    \"pincode\": \"313332\",\n" +
                "                    \"village\": \"Tikar B.O\",\n" +
                "                    \"remark\": \"No remarks\"\n" +
                "                },\n" +
                "                \"pdQuestions\": {\n" +
                "                    \"additionalDocuments\": \"Bank details\",\n" +
                "                    \"pdCategory\": \"Bank Account\",\n" +
                "                    \"documentType\": \"Salary Slip\"\n" +
                "                }\n" +
                "            },\n" +
                "            \"udyam\": {\n" +
                "                \"udyamName\": \"Meera R\",\n" +
                "                \"industryCategory\": \"Agri - Rice Mill\",\n" +
                "                \"investmentInPlantAndMachine\": \"200000\",\n" +
                "                \"yearlyIncome\": \"600000\",\n" +
                "                \"udyamNumberIsAvialable\": \"Yes\",\n" +
                "                \"udyamNumber\": \"UDYAM-MH-23-1287887\"\n" +
                "            },\n" +
                "            \"bankAccount\": {\n" +
                "                \"applicantType\": \"Applicant\",\n" +
                "                \"applicantName\": \"MEERA ASOPA R\",\n" +
                "                \"ifsc\": \"UTIB0001496\",\n" +
                "                \"acHolderName\": \"Meera\",\n" +
                "                \"acNumber\": \"98765434567\",\n" +
                "                \"accountType\": \"Savings\",\n" +
                "                \"netBankingMobileBanking\": \"Yes\",\n" +
                "                \"pleaseSelectAccountFunctionType\": \"Repayment Account Only\"\n" +
                "            },\n" +
                "            \"bankDocument\": {\n" +
                "                \"applicantType\": \"Applicant\",\n" +
                "                \"applicantName\": \"SAMPLE VVG\",\n" +
                "                \"documentType\": \"Bank Statement\"\n" +
                "            }\n" +
                "        },\n" +
                "        \"referenceVerification\": {\n" +
                "            \"supplier\": {\n" +
                "                \"nameOfReference\": \"chandan\",\n" +
                "                \"address1\": \"Indiqube South Summit\",\n" +
                "                \"address2\": \"South end circle\",\n" +
                "                \"pincode\": \"313332\",\n" +
                "                \"village\": \"Tikar B.O\",\n" +
                "                \"typeOfInteraction\": \"Call Centre Lead\",\n" +
                "                \"knowCustomerSince\": \"4\",\n" +
                "                \"purchaseVloumeInLast3Months\": \"200000\",\n" +
                "                \"noOfDelayInPaymentsInLast6Months\": \"10\",\n" +
                "                \"creditFacilityAvailable\": \"Yes\",\n" +
                "                \"creditPeriod\": \"5\",\n" +
                "                \"monthlySalesBasisSuppliersFeedback\": \"Reference\",\n" +
                "                \"supplierCheckStatus\": \"Positive\",\n" +
                "                \"feedbackOnFinanacialStability\": \"Finance\"\n" +
                "            },\n" +
                "            \"clientCustomer\": {\n" +
                "                \"nameOfReference\": \"chandan\",\n" +
                "                \"address1\": \"Indiqube South Summit\",\n" +
                "                \"address2\": \"South end circle\",\n" +
                "                \"pincode\": \"313332\",\n" +
                "                \"village\": \"Tikar B.O\",\n" +
                "                \"typeOfInteraction\": \"Call Centre Lead\",\n" +
                "                \"knowCustomerSince\": \"4\",\n" +
                "                \"salesVloumeInLast3Months\": \"200000\",\n" +
                "                \"creditFacilityAvailable\": \"Yes\",\n" +
                "                \"creditPeriod\": \"5\",\n" +
                "                \"clientCustomerCheckStatus\": \"Positive\",\n" +
                "                \"feedbackOnFinanacialStability\": \"Finance\"\n" +
                "            },\n" +
                "            \"neighbour\": {\n" +
                "                \"nameOfReference\": \"chandan\",\n" +
                "                \"address1\": \"Indiqube South Summit\",\n" +
                "                \"address2\": \"South end circle\",\n" +
                "                \"pincode\": \"313332\",\n" +
                "                \"village\": \"Tikar B.O\",\n" +
                "                \"typeOfInteraction\": \"Call Centre Lead\",\n" +
                "                \"knowCustomerSince\": \"4\",\n" +
                "                \"salesVloumeInLast3Months\": \"200000\",\n" +
                "                \"creditFacilityAvailable\": \"Yes\",\n" +
                "                \"creditPeriod\": \"5\",\n" +
                "                \"clientCustomerCheckStatus\": \"Positive\",\n" +
                "                \"feedbackOnFinanacialStability\": \"Finance\"\n" +
                "            }\n" +
                "        },\n" +
                "        \"collateralVerification\": {\n" +
                "            \"collateralVerification\": {\n" +
                "                \"nameOfTheOwnerOfTheProperty\": \"Sample Vvg\",\n" +
                "                \"address1\": \"Indiqube South Summit\",\n" +
                "                \"address2\": \"South end circle\",\n" +
                "                \"pincode\": \"313332\",\n" +
                "                \"village\": \"Tikar B.O\",\n" +
                "                \"buildingTypes\": \"8\",\n" +
                "                \"typeOfProperty\": \"Residential\",\n" +
                "                \"collateralType\": \"Flat\",\n" +
                "                \"areaInSqFt\": \"1000\",\n" +
                "                \"surveyNumber\": \"5678\",\n" +
                "                \"accessToAddress\": \"Difficult To Access\",\n" +
                "                \"collateralAuthority\": \"Gram Panchayat\",\n" +
                "                \"constructionStage\": \"55\",\n" +
                "                \"marketValue\": \"98760\",\n" +
                "                \"howMuchIsSelfOccupied\": \"65\",\n" +
                "                \"howNuchGivenOnRent\": \"55\",\n" +
                "                \"boundaryRemarksNorth\": \"No remarks\",\n" +
                "                \"boundaryRemarksSouth\": \"No remarks\",\n" +
                "                \"boundaryRemarksEast\": \"No remarks\",\n" +
                "                \"boundaryRemarksWest\": \"No remarks\",\n" +
                "                \"documentType\": \"Sale Deed\"\n" +
                "            }\n" +
                "        },\n" +
                "        \"notePad\": {\n" +
                "            \"pdDeetails\": \"pdDetails\"\n" +
                "        }\n" +
                "    }\n" +
                "}";

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode jsonNode = objectMapper.readTree(jsonString);
            JsonNode leadNode = jsonNode.get("lead");
            if (leadNode != null) {
                System.out.println("Lead Object:");
                System.out.println(leadNode.toPrettyString());
            } else {
                System.out.println("Lead object not found in JSON.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
