package stepdefinitions;

import io.cucumber.java.en.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.SignUpPage;
import pages.SignInPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class SignUpAndSignInSteps {

    WebDriver driver;
    SignUpPage signUp;
    SignInPage signIn;

    // Store credentials for reuse
    String generatedEmail = "shifa.qa+" + System.currentTimeMillis() + "@example.com";
    String password = "Test@1234";

    @Given("user launch the Magento website")
    public void launchMagento() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://magento.softwaretestingboard.com/customer/account/create/");
        driver.manage().window().maximize();
    }

    @When("user navigate to the Create Account page")
    public void navigateToCreateAccount() {
        signUp = new SignUpPage(driver);
    }

    @When("user enter valid user details")
    public void enterUserDetails() {
        signUp.enterFirstName("Shifa");
        signUp.enterLastName("QA");
        signUp.enterEmail(generatedEmail);
        signUp.enterPassword(password);
        signUp.confirmPassword(password);
    }

    @When("user submit the registration form")
    public void submitForm() {
        signUp.clickCreateAccount();
    }

    @Then("user should see a successful account creation message")
    public void verifySuccess() {
        String expectedMessage = "Thank you for registering with Main Website Store.";
        String actualMessage = driver.findElement(By.cssSelector(".message-success.success.message")).getText();
        assertEquals("Account creation message mismatch", expectedMessage, actualMessage.trim());
        driver.get("https://magento.softwaretestingboard.com/customer/account/logout/");
    }

    @Given("user launch the Magento login page")
    public void launchLoginPage() {
        driver.get("https://magento.softwaretestingboard.com/customer/account/login/");
        signIn = new SignInPage(driver);
    }

    @When("user enter the same credentials used during sign-up")
    public void enterLoginCredentials() {
        signIn.enterEmail(generatedEmail);
        signIn.enterPassword(password);
    }

    @When("user click the Sign In button")
    public void clickSignInButton() {
        signIn.clickSignIn();
    }

    @Then("user should be logged in successfully")
    public void verifyLoginSuccess() {
        boolean loginSuccess = signIn.isLoginSuccessful();
        assertTrue("Login failed", loginSuccess);
        driver.quit();

    }
}