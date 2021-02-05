package page.object;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class NewMedicationRequestPage extends BasePage{

    @FindBy(css = "div[class*='test-patient-input'] input[class*='input']")
    private WebElement mPatientField;

    @FindBy(css = "select[id*='visit']")
    private WebElement mVisitDropdownList;

    @FindBy(css = "input[type='text'][id^=inventoryItemTypeAhead]")
    private WebElement mMedicationField;

    @FindBy(css = "div[class*='test-medication-prescription'] textarea")
    private WebElement mPrescriptionField;

    @FindBy(css = "input[id*='prescriptionDate']")
    private WebElement mPrescriptionDateField;

    @FindBy(css = "div[class*='test-quantity-input'] input")
    private WebElement mQuantityRequestedField;

    @FindBy(css = "input[id*='refills']")
    private WebElement mRefillsField;

    @FindBy(xpath = "//button[text()='Add']")
    private WebElement mAddMedicationRequestButton;


    public NewMedicationRequestPage(WebDriver driver) {
        super(driver);
    }


    @Step("Fill Patient field with '{patient}'")
    public void inputPatient(String patient) {
        mPatientField.sendKeys(patient);

        //Wait until the dropdown menu will be able to... drop down.
        WebDriverWait wait = new WebDriverWait(mDriver, Duration.ofSeconds(10));
        wait.until( mDriver -> {
            mPatientField.sendKeys(Keys.SPACE);

            boolean menuIsDisplayed = mDriver
                    .findElement(By.cssSelector("div[class*='test-patient-input'] div[class*='tt-menu']")).isDisplayed();
            if (menuIsDisplayed) { return true; }

            try {
                Thread.sleep(500);
            } catch (Exception e) {}

            mPatientField.sendKeys(Keys.BACK_SPACE);

            return false;
        });
    }

    @Step("Click Patient field dropdown option containing '{text}'")
    public void clickOptionFromPatientFieldDropdown(String text) {
        List<WebElement> options = mDriver.findElements(By
                .xpath("//div[contains(@class, 'test-patient-input')]//div[@class='tt-suggestion tt-selectable']"));

        for (WebElement option : options) {
            if(option.getText().contains(text)) {
                option.click();
                break;
            }
        }
    }

    @Step("Click Visit dropdown list")
    public void clickVisitDropdown() {
        WebDriverWait wait = new WebDriverWait(mDriver, Duration.ofSeconds(5));
        wait.until( mDriver -> {
            Select select = new Select(mDriver.findElement(By.cssSelector("select[id*='visit']")));
            return (select.getOptions().size() > 1);
        });

        mVisitDropdownList.click();
    }

    @Step("Select random option from Visit dropdown list")
    public void clickRandomVisitDropdownOption() {
        List<WebElement> options = mDriver.findElements(By.xpath("//select[contains(@id, 'visit')]/option"));

        int randomOptionNumber = new Random().nextInt(options.size() - 1) + 1;
        WebElement randomOption = options.get(randomOptionNumber);

        randomOption.click();
    }

    @Step("Fill Medication field with '{medication}'")
    public void inputMedication(String medication) {
        mMedicationField.sendKeys(medication);
    }

    @Step("Select random option from Medication dropdown list")
    public void clickRandomMedicationDropdownOption() {
        List<WebElement> options = mDriver.findElements(By.cssSelector("div[class*='test-medication-input'] div[class*='tt-suggestion']"));

        int randomOptionNumber = new Random().nextInt(options.size());
        WebElement randomOption = options.get(randomOptionNumber);

        randomOption.click();
    }

    @Step("Fill Prescription field with '{prescription}'")
    public void inputPrescription(String prescription) {
        mPrescriptionField.sendKeys(prescription);
    }

    @Step("Input Prescription Date with {daysOffset} days offset")
    public void inputPrescriptionDateWithDaysOffset(int daysOffset) {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH);

        cal.add(Calendar.DATE, daysOffset);

        String prescriptionDateWithOffset = sdf.format(cal.getTime());

        mPrescriptionDateField.clear();
        mPrescriptionDateField.sendKeys(prescriptionDateWithOffset);
        mPrescriptionDateField.sendKeys(Keys.ENTER);
    }

    public void inputPrescriptionDate(String prescriptionDate) {
        mPrescriptionDateField.sendKeys(prescriptionDate);
    }

    @Step("Fill Quantity Requested field with random number between {min} and {max}")
    public void inputRandomValueIntoQuantityRequestedField(int min, int max) {
        int randomNumber = new Random().nextInt(max - min) + min;
        mQuantityRequestedField.sendKeys(randomNumber + "");
    }

    @Step("Fill Refills field with random number between {min} and {max}")
    public void inputRandomValueIntoRefillsField(int min, int max) {
        int randomNumber = new Random().nextInt(max - min) + min;
        mRefillsField.sendKeys(randomNumber + "");
    }

    @Step("Click Add button")
    public void clickAddMedicationRequestButton() {
        try {
            Thread.sleep(500);
        } catch (Exception e) {}

        mAddMedicationRequestButton.click();
    }

    @Step("Make sure Medication Request Saved popup is displayed")
    public boolean medicationRequestSavedPopupIsDisplayed() {
        boolean isDisplayed = !mDriver.findElements(By.xpath("//div[@class='modal-dialog']//*[text()='Medication Request Saved']")).isEmpty();
        return isDisplayed;
    }

    @Step("Make sure Medication Request Saved popup is displayed correctly")
    public boolean medicationRequestSavedPopupIsDisplayedCorrectly() {
        boolean element1 = !mDriver.findElements(By.xpath("//div[@class='modal-dialog']//button[@class='close']")).isEmpty();
        boolean element2 = !mDriver.findElements(By.xpath("//div[@class='modal-dialog']//button[text()='Ok']")).isEmpty();

        boolean isDisplayedCorrectly = element1 && element2;

        return isDisplayedCorrectly;
    }

    @Step("Click Ok button in Medication Request Saved popup")
    public void clickMedicationRequestSavedPopupOkButton() {
        WebElement okButton = mDriver.findElement(By.xpath("//div[@class='modal-dialog']//button[text()='Ok']"));

        WebDriverWait wait = new WebDriverWait(mDriver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(okButton));

        okButton.click();
    }
}