package Practice;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ElementRepository {
    private WebDriver driver;


    public ElementRepository(WebDriver driver) {
        this.driver = driver;
    }
    private By usernameLocator = By.id("username");
    private By passwordLocator = By.id("password");
    private By loginButtonLocator = By.id("loginButton");
    
    public void enterUsername(String username) {
        WebElement usernameElement = driver.findElement(usernameLocator);
        usernameElement.sendKeys(username);
    }

    public void enterPassword(String password) {
        WebElement passwordElement = driver.findElement(passwordLocator);
        passwordElement.sendKeys(password);
    }

    public void clickLoginButton() {
        WebElement loginButtonElement = driver.findElement(loginButtonLocator);
        loginButtonElement.click();
    }

    // Add more methods for interacting with other elements
}