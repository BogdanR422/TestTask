package page.object;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    public WebDriver mDriver;

    @FindBy(css = "input[placeholder='Username']")
    private WebElement mUsernameField;

    @FindBy(css = "input[placeholder='Password']")
    private WebElement mPasswordField;

    @FindBy(xpath = "//button[text()='Sign in']")
    private WebElement mSignInButton;


    public LoginPage(WebDriver driver){
        PageFactory.initElements(driver, this);
        mDriver = driver;
    }


    @Step("Fill Username field with '{username}'")
    public void inputUsername(String username) {
        mUsernameField.sendKeys(username);
    }

    @Step("Fill Password field with '{password}'")
    public void inputPassword(String password) {
        mPasswordField.sendKeys(password);
    }

    @Step("Click Sign In button")
    public void clickSignInButton() {
        mSignInButton.click();
    }

    @Step("Check if the error message is displayed")
    public boolean errorMessageIsDisplayed() {
        WebDriverWait wait = new WebDriverWait(mDriver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[role='alert']")));

        return !mDriver.findElements(By.cssSelector("div[role='alert']")).isEmpty();
    }
}