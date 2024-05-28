package Practice;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class StringToJsonConversion {
    public static void main(String[] args) {
        String jsonString = "{\"\r\n"
                + "  \"id\": \"RAN>>NUM>>3\",\r\n"
                + "  \"title\": \"RAN>>STR>>4\",\r\n"
                + "  \"componentId\": 1,\r\n"
                + "  \"bugId\": \"fgfg\",\r\n"
                + "  \"items\": [\r\n"
                + "    {\r\n"
                + "      \"id\": \"RAN>>NUM>>3\",\r\n"
                + "      \"datasource\": \"eks-dev\",\r\n"
                + "      \"databases\": \"vault\",\r\n"
                + "      \"query\": \"UPDATE slab_task_detail SET net_sale_amount = 200.5 , discount_percent = 10, updated_on = now() WHERE slab_task_id = 12 AND client = 'B2C' AND partner_id = '1235';\\n\",\r\n"
                + "      \"params\": {}\r\n"
                + "    }\r\n"
                + "  ],\r\n"
                + "  \"status\": \"CREATED\",\r\n"
                + "  \"runAt\": null,\r\n"
                + "  \"requesterId\": \"b2fb06b9-c528-4b01-8dc2-8baa1e94bf15\",\r\n"
                + "  \"requesterName\": \"\",\r\n"
                + "  \"approverId\": \"e7fa800d-93cb-4d26-b12b-5d79dc228d3a\",\r\n"
                + "  \"approverName\": \"Abuzar\",\r\n"
                + "  \"approvedOn\": null,\r\n"
                + "  \"createdOn\": \"2024-02-28T13:08:21.828374\",\r\n"
                + "  \"templateId\": null,\r\n"
                + "  \"autoApproved\": false,\r\n"
                + "  \"maxRowsImpact\": null\r\n"
                + "}";

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(jsonString);
            String formattedJson = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonNode);
            System.out.println(formattedJson);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
