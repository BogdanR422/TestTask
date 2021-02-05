package page.object;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BasePage {
    public WebDriver mDriver;

    @FindBy(css = "a[class*='settings-trigger']")
    private WebElement mSettingsTrigger;

    @FindBy(xpath = "//a[text()='Logout']")
    private WebElement mLogoutLink;

    @FindBy(xpath = "//a[text()='Patients']")
    private WebElement mPatientsMenuItem;

    @FindBy(xpath = "//a[text()='Patient Listing']")
    private WebElement mPatientsListingMenuSubItem;

    @FindBy(xpath = "//a[text()='Admitted Patients']")
    private WebElement mAdmittedPatientsMenuSubItem;

    @FindBy(xpath = "//a[text()='Outpatient']")
    private WebElement mOutpatientMenuSubItem;

    @FindBy(xpath = "//a[text()='New Patient']")
    private WebElement mNewPatientMenuSubItem;

    @FindBy(xpath = "//a[text()='Reports']")
    private WebElement mReportsMenuSubItem;

    //...

    @FindBy(xpath = "//a[text()='Medication']")
    private WebElement mMedicationMenuItem;

    @FindBy(xpath = "//a[text()='Requests']")
    private WebElement mRequestsMenuSubItem;

    @FindBy(xpath = "//a[text()='Completed']")
    private WebElement mCompletedMenuSubItem;

    @FindBy(xpath = "//a[text()='New Request']")
    private WebElement mNewRequestMenuSubItem;

    @FindBy(xpath = "//a[text()='Dispense']")
    private WebElement mDispenseMenuSubItem;

    @FindBy(xpath = "//a[text()='Return Medication']")
    private WebElement mReturnMedicationMenuSubItem;

    //...


    public BasePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        mDriver = driver;
    }


    @Step("Click the Cogwheel icon")
    public void openSettingsMenu() {
        mSettingsTrigger.click();
    }

    @Step("Click Logout link")
    public void clickLogoutLink() {
        mLogoutLink.click();
    }

    public void clickPatientsMenuItem() {
        mPatientsMenuItem.click();
    }

    public void clickPatientsListingMenuSubItem() {
        mPatientsListingMenuSubItem.click();
    }

    public void clickAdmittedPatientsMenuSubItem() {
        mAdmittedPatientsMenuSubItem.click();
    }

    public void clickOutpatientMenuSubItem() {
        mOutpatientMenuSubItem.click();
    }

    public void clickNewPatientMenuSubItem() {
        mNewPatientMenuSubItem.click();
    }

    public void clickReportsMenuSubItem() {
        mReportsMenuSubItem.click();
    }

    //...

    @Step("Click Medication menu item")
    public void clickMedicationMenuItem() {
        mMedicationMenuItem.click();
    }

    @Step("Make sure the Medication Section is displayed correctly")
    public boolean medicationSectionIsDisplayedCorrectly() {
        boolean item1 = mRequestsMenuSubItem.isDisplayed();
        boolean item2 = mCompletedMenuSubItem.isDisplayed();
        boolean item3 = mNewRequestMenuSubItem.isDisplayed();
        boolean item4 = mDispenseMenuSubItem.isDisplayed();
        boolean item5 = mReturnMedicationMenuSubItem.isDisplayed();

        boolean isDisplayedCorrectly = item1 && item2 && item3 && item4 && item5;

        return isDisplayedCorrectly;
    }

    public void clickRequestsMenuSubItem() {
        mRequestsMenuSubItem.click();
    }

    public void clickCompletedMenuSubItem() {
        mCompletedMenuSubItem.click();
    }

    @Step("Click New Request menu sub-item")
    public void clickNewRequestMenuSubItem() {
        mNewRequestMenuSubItem.click();
    }

    public void clickDispenseMenuSubItem() {
        mDispenseMenuSubItem.click();
    }

    public void clickReturnMedicationMenuSubItem() {
        mReturnMedicationMenuSubItem.click();
    }

    //...
}