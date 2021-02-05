package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import page.object.ConfProperties;
import page.object.LoginPage;
import page.object.PatientsPage;

import java.time.Duration;

public class TestCase3 {
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

        mLoginPage.inputUsername(ConfProperties.getProperty("page.login.username"));
        mLoginPage.inputPassword(ConfProperties.getProperty("page.login.password"));
        mLoginPage.clickSignInButton();
    }

    @Test
    public void logout() {
        String expectedURL = ConfProperties.getProperty("page.login.url");

        mPatientsPage.openSettingsMenu();
        mPatientsPage.clickLogoutLink();

        WebDriverWait wait = new WebDriverWait(mDriver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlToBe(expectedURL));
    }

    @AfterClass
    public void terminate() {
        mDriver.quit();
    }
}