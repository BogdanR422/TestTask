package tests;

import org.testng.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import page.object.*;

import java.time.Duration;

public class TestCase4 {
    public static WebDriver mDriver;

    public static LoginPage mLoginPage;
    public static PatientsPage mPatientsPage;
    public static MedicationPage mMedicationPage;
    public static NewMedicationRequestPage mNewMedicationRequestPage;

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
        mMedicationPage = new MedicationPage(mDriver);
        mNewMedicationRequestPage = new NewMedicationRequestPage(mDriver);

        mDriver.manage().window().maximize();
        mDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        mDriver.get(ConfProperties.getProperty("page.login.url"));

        mLoginPage.inputUsername(ConfProperties.getProperty("page.login.username"));
        mLoginPage.inputPassword(ConfProperties.getProperty("page.login.password"));
        mLoginPage.clickSignInButton();
    }

    @Test
    public void requestANewMedication() {
        mPatientsPage.clickMedicationMenuItem();

        Assert.assertTrue(mMedicationPage.medicationSectionIsDisplayedCorrectly());
        mMedicationPage.clickNewRequestMenuSubItem();

        mNewMedicationRequestPage.inputPatient("Test Patient");
        mNewMedicationRequestPage.clickOptionFromPatientFieldDropdown("P00201");
        mNewMedicationRequestPage.clickVisitDropdown();
        mNewMedicationRequestPage.clickRandomVisitDropdownOption();
        mNewMedicationRequestPage.inputMedication("Pramoxine");
        mNewMedicationRequestPage.clickRandomMedicationDropdownOption();
        mNewMedicationRequestPage.inputPrescription("Testing prescription");
        mNewMedicationRequestPage.inputPrescriptionDateWithDaysOffset(-1);
        mNewMedicationRequestPage.inputRandomValueIntoQuantityRequestedField(1, 5);
        mNewMedicationRequestPage.inputRandomValueIntoRefillsField(5, 10);
        mNewMedicationRequestPage.clickAddMedicationRequestButton();

        Assert.assertTrue(mNewMedicationRequestPage.medicationRequestSavedPopupIsDisplayed());
        Assert.assertTrue(mNewMedicationRequestPage.medicationRequestSavedPopupIsDisplayedCorrectly());
        mNewMedicationRequestPage.clickMedicationRequestSavedPopupOkButton();

        Assert.assertFalse(mNewMedicationRequestPage.medicationRequestSavedPopupIsDisplayed());

        WebDriverWait wait = new WebDriverWait(mDriver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.urlToBe(ConfProperties.getProperty("page.medication.new_request.url")));
    }

    @AfterClass
    public void terminate() {
        mNewMedicationRequestPage.openSettingsMenu();
        mNewMedicationRequestPage.clickLogoutLink();
        mDriver.quit();
    }
}