package android;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import java.io.IOException;

public class ServerLogsFetcher {

    public static void main(String[] args) {
        String serverUrl = "http://example.com/logs"; // Replace with your server URL

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(serverUrl)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                ResponseBody body = response.body();
                if (body != null) {
                    String logs = body.string();
                    System.out.println(logs); // Print the logs
                }
            } else {
                System.err.println("Failed to fetch logs: " + response.code());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
