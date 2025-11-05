package ge.tbc.testautomation.steps;

import com.microsoft.playwright.Page;
import ge.tbc.testautomation.pages.BasePage;
import ge.tbc.testautomation.pages.OTPValidationPage;
import ge.tbc.testautomation.pages.SchoolCardPage;
import ge.tbc.testautomation.pages.YouthWizardPage;
import io.qameta.allure.Step;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class SchoolCardSteps {
    Page page;
    BasePage basePage;
    BaseSteps baseSteps;
    SchoolCardPage schoolCardPage;
    YouthWizardPage youthWizardPage;
    OTPValidationPage otpValidationPage;

    public SchoolCardSteps(Page page) {
        this.page = page;
        basePage = new BasePage(page);
        baseSteps = new BaseSteps(page);
        schoolCardPage = new SchoolCardPage(page);
        youthWizardPage = new YouthWizardPage(page);
        otpValidationPage = new OTPValidationPage(page);
    }

    @Step("Navigate to 'For Me' section")
    public SchoolCardSteps navigateToForMe() {
        baseSteps.navigateToItem(basePage.forMeLink);
        return this;
    }

    @Step("Navigate to 'School Card' page")
    public SchoolCardSteps navigateToSchoolCard() {
        baseSteps.navigateToItem(basePage.schoolCardNavigation);
        return this;
    }

    @Step("Click on 'School Card' page")
    public SchoolCardSteps clickOnSchoolCard() {
        baseSteps.clickOnItem(basePage.schoolCardNavigation);
        return this;
    }

    @Step("Order button is displayed")
    public SchoolCardSteps orderButtonIsDisplayed() {
        baseSteps.assertItemVisibility(schoolCardPage.orderBtn);
        return this;
    }

    @Step("Click on 'Order' button")
    public SchoolCardSteps clickOnOrder() {
        baseSteps.clickOnItem(schoolCardPage.orderBtn);
        return this;
    }

    @Step("Wait for ID number field visibility")
    public SchoolCardSteps idNumberFieldIsDisplayed() {
        baseSteps.assertItemVisibility(youthWizardPage.idNumberField);
        return this;
    }

    @Step("Enter ID number: {id}")
    public SchoolCardSteps enterIDNumber(String id) {
        baseSteps.fillItemField(youthWizardPage.idNumberField, id);
        return this;
    }

    @Step("Continue button is displayed")
    public SchoolCardSteps continueButtonIsDisplayed() {
        baseSteps.assertItemVisibility(youthWizardPage.continueBtn);
        return this;
    }

    @Step("Click on 'Continue' button")
    public SchoolCardSteps clickContinue() {
        baseSteps.clickOnItem(youthWizardPage.continueBtn);
        return this;
    }

    @Step("Wait for mobile number field visibility")
    public SchoolCardSteps waitForMobileNumberFieldVisibility() {
        baseSteps.waitForLocator(youthWizardPage.mobileNumberField);
        return this;
    }

    @Step("Mobile number field is displayed")
    public SchoolCardSteps mobileNumberFieldIsDisplayed() {
        baseSteps.assertItemVisibility(youthWizardPage.mobileNumberField);
        return this;
    }

    @Step("Enter mobile number: {mobile}")
    public SchoolCardSteps enterMobileNumber(String mobile) {
        baseSteps.fillItemField(youthWizardPage.mobileNumberField, mobile);
        return this;
    }

    @Step("Wait for code field visibility")
    public SchoolCardSteps waitForCodeFieldVisibility() {
        baseSteps.waitForLocator(otpValidationPage.codeField);
        return this;
    }

    @Step("Code field is displayed")
    public SchoolCardSteps codeFieldIsDisplayed() {
        baseSteps.assertItemVisibility(otpValidationPage.codeField);
        return this;
    }

    @Step("Enter code: {code}")
    public SchoolCardSteps enterCode(String code) {
        baseSteps.fillItemField(otpValidationPage.codeField, code);
        return this;
    }

    @Step("Invalid code field border turns red")
    public SchoolCardSteps invalidCodeFieldBorderTurnsRed(String className) {
        baseSteps.itemHasClass(otpValidationPage.codeFieldContainer, className);
        return this;
    }

    @Step("Error message is displayed")
    public SchoolCardSteps errorMessageIsDisplayed() {
        baseSteps.assertItemVisibility(otpValidationPage.errorMessage);
        return this;
    }

    @Step("Error message text is red")
    public SchoolCardSteps errorMessageTextIsRed(String className) {
        baseSteps.itemHasClass(otpValidationPage.errorMessage, className);
        return this;
    }

    @Step("Error message text is valid")
    public SchoolCardSteps errorMessageTextIsValid(String text) {
        baseSteps.assertText(otpValidationPage.errorMessage, text);
        return this;
    }
}
