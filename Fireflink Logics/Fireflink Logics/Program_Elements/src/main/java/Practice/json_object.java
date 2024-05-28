package Practice;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.Map;

import org.json.JSONObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.fasterxml.jackson.databind.ObjectMapper;

public class json_object {
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();

        try {
            String jsonContent = new String(Files.readAllBytes(Paths.get("C:\\Users\\User\\Downloads\\AdityaBirla.json")));
            JSONObject jsonObject = new JSONObject(jsonContent);
            JSONObject questionsObject = jsonObject.getJSONObject("questions");
            System.out.println("Questions Object: " + questionsObject.toString());
            String questions = questionsObject.toString();
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, Object> map = objectMapper.readValue(questions, LinkedHashMap.class);
            // Print the map
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                System.out.println(entry.getKey() + " : " + entry.getValue());
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}
