package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import page.object.ConfProperties;
import page.object.LoginPage;
import page.object.PatientsPage;

import java.time.Duration;

public class TestCase1 {
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
    public void loginWithCorrectCredentials() {
        mLoginPage.inputUsername(ConfProperties.getProperty("page.login.username"));
        mLoginPage.inputPassword(ConfProperties.getProperty("page.login.password"));
        mLoginPage.clickSignInButton();

        String expectedURL = ConfProperties.getProperty("page.patients.url");

        WebDriverWait wait = new WebDriverWait(mDriver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlToBe(expectedURL));
    }

    @AfterClass
    public void terminate() {
        mPatientsPage.openSettingsMenu();
        mPatientsPage.clickLogoutLink();
        mDriver.quit();
    }
}