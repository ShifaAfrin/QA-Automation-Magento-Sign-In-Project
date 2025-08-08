package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class SignInPage {

    WebDriver driver;
    WebDriverWait wait;

    //Locators
    private By emailField = By.id("email");
    private By passwordField = By.id("pass");
    private By signInButton = By.id("send2");
    private By loggedInBanner = By.cssSelector(".logged-in");

    //Constructor
    public SignInPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    //Actions
    public void enterEmail(String email) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(emailField));
        driver.findElement(emailField).clear();
        driver.findElement(emailField).sendKeys(email);
    }

    public void enterPassword(String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField));
        driver.findElement(passwordField).clear();
        driver.findElement(passwordField).sendKeys(password);
    }

    public void clickSignIn() {
        wait.until(ExpectedConditions.elementToBeClickable(signInButton));
        driver.findElement(signInButton).click();
    }

    public boolean isLoginSuccessful() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(loggedInBanner));
        String welcomeText = driver.findElement(loggedInBanner).getText();
        return welcomeText.contains("Welcome");
    }
}