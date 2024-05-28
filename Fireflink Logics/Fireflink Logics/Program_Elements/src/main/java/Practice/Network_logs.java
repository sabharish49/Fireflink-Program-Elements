package Practice;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Network_logs {
    public static void main(String[] args) throws Exception {
        String url = "https://backend1.fireflink.com/appmanagement/optimize/v1/public/user/signin";
        String headers = "Accept: application/json\r\n" +
                         "Accept-Language: en-GB,en-US;q=0.9,en;q=0.8\r\n" +
                         "Access-Control-Allow-Origin: *\r\n" +
                         "Connection: keep-alive\r\n" +
                         "Content-Type: application/json\r\n" +
                         "Origin: https://app.fireflink.com\r\n" +
                         "Referer: https://app.fireflink.com/\r\n" +
                         "Sec-Fetch-Dest: empty\r\n" +
                         "Sec-Fetch-Mode: cors\r\n" +
                         "Sec-Fetch-Site: same-site\r\n" +
                         "User-Agent: Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/122.0.0.0 Mobile Safari/537.36\r\n" +
                         "projectId;\r\n" +
                         "projectType: undefined\r\n" +
                         "sec-ch-ua: \"Chromium\";v=\"122\", \"Not(A:Brand\";v=\"24\", \"Google Chrome\";v=\"122\"\r\n" +
                         "sec-ch-ua-mobile: ?1\r\n" +
                         "sec-ch-ua-platform: \"Android\"";
        String requestBody = "{\"emailId\":\"gouthamsam098@outlook.com\",\"password\":\"Abhisam@098\",\"lastSessionData\":\"/signin\"}";
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .headers(headers.split("\r\n"))
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println("Response Code: " + response.statusCode());
        System.out.println("Response Body: " + response.body());
    }
}
