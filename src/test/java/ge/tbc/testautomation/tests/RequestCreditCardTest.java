package ge.tbc.testautomation.tests;

import ge.tbc.testautomation.steps.BaseSteps;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static ge.tbc.testautomation.data.Constants.BASE_URL;

public class RequestCreditCardTest extends BaseTest {
    BaseSteps baseSteps;

    @BeforeClass
    public void initializeSteps() {
        page.navigate(BASE_URL);
        baseSteps = new BaseSteps(page);
    }

    @Test(description = "Request a call for a credit card creation [KAN-T4]")
    public void requestCallToOrderCreditCard() {
        baseSteps
                .hoverOnForMeLink()
                .navigateToCreditCardPage()
                .validateCreditCardComparisonContainerVisible()
                .validateCreditCardComparisonColumnsVisible()
                .requestToMakeACall()
                .validateSideFormDisplayed()
                .validateFormInputsVisible()
                .fillFormInputs();
    }
}
