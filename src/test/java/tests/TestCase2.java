package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import page.object.ConfProperties;
import page.object.LoginPage;
import page.object.PatientsPage;

import java.time.Duration;

public class TestCase2 {
    public static WebDriver mDriver;

    public static LoginPage mLoginPage;
    public static PatientsPage mPatientsPage;

    @BeforeClass
    @Parameters("browser")
    public void setup(String browser) throws Exception {
        if(browser.equalsIgnoreCase("chrome")){
            System.setProperty("webdriver.chrome.driver", ConfProperties.getProperty("path.chromedriver"));
            mDriver = new ChromeDriver();
        } else if(browser.equalsIgnoreCase("firefox")){
            System.setProperty("webdriver.gecko.driver", ConfProperties.getProperty("path.geckodriver"));
            mDriver = new FirefoxDriver();
        } else {
            throw new Exception("Wrong browser...");
        }

        mLoginPage = new LoginPage(mDriver);
        mPatientsPage = new PatientsPage(mDriver);

        mDriver.manage().window().maximize();
        mDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        mDriver.get(ConfProperties.getProperty("page.login.url"));
    }

    @Test
    public void loginWithIncorrectCredentials() {
        String expectedURL = mDriver.getCurrentUrl();

        mLoginPage.inputUsername("incorrect username");
        mLoginPage.inputPassword("incorrect password");
        mLoginPage.clickSignInButton();

        WebDriverWait wait = new WebDriverWait(mDriver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.urlToBe(expectedURL));

        Assert.assertTrue(mLoginPage.errorMessageIsDisplayed());
    }

    @AfterClass
    public void terminate() {
        mDriver.quit();
    }
}