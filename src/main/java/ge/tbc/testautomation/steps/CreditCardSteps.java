package ge.tbc.testautomation.steps;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import ge.tbc.testautomation.data.Constants;
import ge.tbc.testautomation.pages.CreditCardPage;
import io.qameta.allure.Step;

public class CreditCardSteps {
    Page page;

    CreditCardPage creditCardPage;


    public CreditCardSteps(Page page) {
        this.page = page;
        creditCardPage = new CreditCardPage(page);

    }

    @Step("Verify whether credit card comparison container is visible")
    public CreditCardSteps validateCreditCardComparisonContainerVisible() {
        PlaywrightAssertions.assertThat(creditCardPage.creditCardComparisonContainer).isVisible();
        return this;
    }

    @Step("Verify whether all the columns of the comparison table are " +
            "displayed")
    public CreditCardSteps validateCreditCardComparisonColumnsVisible() {
        PlaywrightAssertions.assertThat(creditCardPage.tableRows).hasCount(17);
        return this;
    }

    @Step("Request a call to order a credit card")
    public CreditCardSteps requestToMakeACall() {
        creditCardPage.requestACallBtn.click();
        return this;
    }

    @Step("Verify whether the side form is displayed")
    public CreditCardSteps validateSideFormDisplayed() {
        PlaywrightAssertions.assertThat(creditCardPage.formContainer).isVisible();
        return this;
    }

    @Step("Verify whether the inputs are visible inside the form")
    public CreditCardSteps validateFormInputsVisible() {
        PlaywrightAssertions.assertThat(creditCardPage.fullNameNameInput).isVisible();
        PlaywrightAssertions.assertThat(creditCardPage.idInput).isVisible();
        PlaywrightAssertions.assertThat(creditCardPage.phoneNumberInput).isVisible();
        return this;
    }

    @Step("Filling of the form")
    public void fillFormInputs() {
        creditCardPage.fullNameNameInput.fill(Constants.DUMMY_FULL_NAME);
        creditCardPage.idInput.fill(Constants.DUMMY_ID);
        creditCardPage.phoneNumberInput.fill(Constants.DUMMY_PHONE_NUMBER);
    }
}
