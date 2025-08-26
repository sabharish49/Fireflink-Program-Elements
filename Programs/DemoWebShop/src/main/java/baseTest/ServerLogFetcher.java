package baseTest;

import java.util.Optional;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v115.network.Network;

public class ServerLogFetcher {

    public static void main(String[] args) {
        // Set the path to your ChromeDriver executable
        //System.setProperty("webdriver.chrome.driver", "/path/to/chromedriver");

        // Create a new WebDriver instance
        WebDriver driver = new ChromeDriver();

        // Get the DevTools interface
        DevTools devTools = ((ChromeDriver) driver).getDevTools();

        // Create a new session
        devTools.createSession();

        // Enable network tracking
        devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));

        // Print intercepted network requests
        devTools.addListener(Network.requestWillBeSent(), entry -> {
            System.out.println("Request URL: " + entry.getRequest().getUrl());
        });

        // Open a URL
        driver.get("https://staging-identityservice.dev.pharmeasy.in/auth/realms/scm/protocol/openid-connect/auth?client_id=gatekeeper-ui&redirect_uri=https%3A%2F%2Fstaging.gatekeeper.scm.gomercury.in%2Frequests%2Fall&state=8ce06091-e9d8-4c0c-88f2-988fdf94288f&response_mode=fragment&response_type=code&scope=openid&nonce=936ebf03-7180-40fe-8d74-80edc9944266&code_challenge=PKR7r702DM9MwGZvjnb7sTUk4n46DUzBXtCEr31noi4&code_challenge_method=S256");

        // Close the WebDriver instance
        driver.quit();
    }
}
