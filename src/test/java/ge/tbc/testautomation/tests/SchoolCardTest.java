package ge.tbc.testautomation.tests;

import ge.tbc.testautomation.data.Constants;
import ge.tbc.testautomation.steps.SchoolCardSteps;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SchoolCardTest extends BaseTest {
    SchoolCardSteps schoolCardSteps;

    @BeforeMethod
    public void setUpBM() {
        schoolCardSteps = new SchoolCardSteps(page);
    }

    @Test(priority = 1, description = "მოსწავლის ბარათის გვერდის გახსნა")
    public void schoolCardPageTest() {
        schoolCardSteps
                .navigateToForMe()
                .navigateToSchoolCard()
                .clickOnSchoolCard();
    }

    @Test(priority = 2, description = "ბარათის შეკვეთის მოთხოვნა")
    public void orderButtonTest() {
        schoolCardSteps
                .orderButtonIsDisplayed()
                .clickOnOrder();
    }

    @Test(priority = 3, description = "პირადი ნომრის შეყვანა")
    public void idNumberTest() {
        schoolCardSteps
                .idNumberFieldIsDisplayed()
                .enterIDNumber(Constants.TEST_ID)
                .continueButtonIsDisplayed()
                .clickContinue();
    }

    @Test(priority = 4, description = "ტელეფონის ნომრის შეყვანა, ნომრის დადასტურება")
    public void mobileNumberTest() {
        schoolCardSteps
                .waitForMobileNumberFieldVisibility()
                .mobileNumberFieldIsDisplayed()
                .enterMobileNumber(Constants.TEST_MOBILE);

        schoolCardSteps
                .clickContinue();
    }

    @Test(priority = 5, description = "არასწორი კოდის შეყვანა")
    public void invalidCodeValidationTest() {
        schoolCardSteps
                .waitForCodeFieldVisibility()
                .codeFieldIsDisplayed()
                .enterCode(Constants.TEST_INVALID_CODE);
    }

    @Test(priority = 6, description = "კოდის დადასტურების მცდელობა")
    public void errorMessageValidationTest() {
        schoolCardSteps
                .clickContinue()
                .invalidCodeFieldBorderTurnsRed(Constants.ERRORED_CLASSNAME)
                .errorMessageIsDisplayed()
                .errorMessageTextIsRed(Constants.ERRORED_CLASSNAME)
                .errorMessageTextIsValid(Constants.ERROR_MESSAGE_TEXT);
    }
}
