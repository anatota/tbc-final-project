package ge.tbc.testautomation.tests;

import ge.tbc.testautomation.steps.BaseSteps;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RequestCreditCardTest extends BaseTest {
    BaseSteps baseSteps;

    @BeforeMethod
    public void initializeSteps() {
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
