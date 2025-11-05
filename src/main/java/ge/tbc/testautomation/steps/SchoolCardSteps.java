package ge.tbc.testautomation.steps;

import com.microsoft.playwright.Page;
import ge.tbc.testautomation.pages.BasePage;
import ge.tbc.testautomation.pages.OTPValidationPage;
import ge.tbc.testautomation.pages.SchoolCardPage;
import ge.tbc.testautomation.pages.YouthWizardPage;

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

    public SchoolCardSteps navigateToForMe() {
        baseSteps.navigateToItem(basePage.forMeLink);
        return this;
    }

    public SchoolCardSteps navigateToSchoolCard() {
        baseSteps.navigateToItem(basePage.schoolCardNavigation);
        return this;
    }

    public SchoolCardSteps clickOnSchoolCard() {
        baseSteps.clickOnItem(basePage.schoolCardNavigation);
        return this;
    }

    public SchoolCardSteps orderButtonIsDisplayed() {
        baseSteps.assertItemVisibility(schoolCardPage.orderBtn);
        return this;
    }

    public SchoolCardSteps clickOnOrder() {
        baseSteps.clickOnItem(schoolCardPage.orderBtn);
        return this;
    }

    public SchoolCardSteps idNumberFieldIsDisplayed() {
        baseSteps.assertItemVisibility(youthWizardPage.idNumberField);
        return this;
    }

    public SchoolCardSteps enterIDNumber(String id) {
        baseSteps.fillItemField(youthWizardPage.idNumberField, id);
        return this;
    }

    public SchoolCardSteps continueButtonIsDisplayed() {
        baseSteps.assertItemVisibility(youthWizardPage.continueBtn);
        return this;
    }

    public SchoolCardSteps clickContinue() {
        baseSteps.clickOnItem(youthWizardPage.continueBtn);
        return this;
    }

    public SchoolCardSteps waitForMobileNumberFieldVisibility() {
        baseSteps.waitForLocator(youthWizardPage.mobileNumberField);
        return this;
    }

    public SchoolCardSteps mobileNumberFieldIsDisplayed() {
        baseSteps.assertItemVisibility(youthWizardPage.mobileNumberField);
        return this;
    }

    public SchoolCardSteps enterMobileNumber(String mobile) {
        baseSteps.fillItemField(youthWizardPage.mobileNumberField, mobile);
        return this;
    }

    public SchoolCardSteps waitForCodeFieldVisibility() {
        baseSteps.waitForLocator(otpValidationPage.codeField);
        return this;
    }

    public SchoolCardSteps codeFieldIsDisplayed() {
        baseSteps.assertItemVisibility(otpValidationPage.codeField);
        return this;
    }

    public SchoolCardSteps enterCode(String code) {
        baseSteps.fillItemField(otpValidationPage.codeField, code);
        return this;
    }

    public SchoolCardSteps invalidCodeFieldBorderTurnsRed(String className) {
        baseSteps.itemHasClass(otpValidationPage.codeFieldContainer, className);
        return this;
    }

    public SchoolCardSteps errorMessageIsDisplayed() {
        baseSteps.assertItemVisibility(otpValidationPage.errorMessage);
        return this;
    }

    public SchoolCardSteps errorMessageTextIsRed(String className) {
        baseSteps.itemHasClass(otpValidationPage.errorMessage, className);
        return this;
    }

    public SchoolCardSteps errorMessageTextIsValid(String text) {
        baseSteps.assertText(otpValidationPage.errorMessage, text);
        return this;
    }
}
