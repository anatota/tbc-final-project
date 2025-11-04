package ge.tbc.testautomation.steps;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import ge.tbc.testautomation.pages.AutoInstallmentPage;
import io.qameta.allure.Step;

import static ge.tbc.testautomation.helper.Helper.calculateMonthlyContribution;

public class AutoInstallmentSteps extends BaseSteps {
    AutoInstallmentPage autoInstallmentPage;

    public AutoInstallmentSteps(Page page) {
        super(page);
        this.autoInstallmentPage = new AutoInstallmentPage(page);
    }

    @Step("Click on 'Auto Installment' button")
    public AutoInstallmentSteps clickOnAutoInstallmentButton() {
        autoInstallmentPage.autoInstallment.click();
        return this;
    }

    @Step("Validate Auto Installment page is opened")
    public AutoInstallmentSteps validateAutoInstallmentPageIsOpened() {
        PlaywrightAssertions.assertThat(autoInstallmentPage.autoInstallmentTitle).isVisible();
        return this;
    }

    @Step("Click on 'Calculate' button")
    public AutoInstallmentSteps clickOnIncomeCalculatorBtn() {
        autoInstallmentPage.incomeCalculatorBtn.click();
        return this;
    }

    @Step("Validate Income Calculator is opened")
    public AutoInstallmentSteps validateIncomeCalculatorIsOpened() {
        PlaywrightAssertions.assertThat(autoInstallmentPage.incomeInputField).isVisible();
        return this;
    }

    @Step("Validate intereset rate: {expectedRate}")
    public AutoInstallmentSteps validateInterestRate(String expectedRate) {
        PlaywrightAssertions.assertThat(autoInstallmentPage.interestRateField).hasText(expectedRate);
        return this;
    }

    @Step("Validate effective interest rate: {expectedEffectiveRate}")
    public AutoInstallmentSteps validateEffectiveInterestRate(String expectedEffectiveRate) {
        PlaywrightAssertions.assertThat(autoInstallmentPage.effectiveInterestRateField).hasText(expectedEffectiveRate);
        return this;
    }

    @Step("Enter income: {income}")
    public AutoInstallmentSteps enterIncome(String income) {
        autoInstallmentPage.incomeInputField.fill(income);
        return this;
    }

    @Step("Enter loan period: {period}")
    public AutoInstallmentSteps enterLoanPeriod(String period) {
        autoInstallmentPage.loanPeriodInputField.fill(period);
        return this;
    }

    @Step("Validate installment amount")
    public AutoInstallmentSteps validateInstallmentAmount(String expectedAmount) {
        PlaywrightAssertions.assertThat(autoInstallmentPage.installmentAmountField).containsText(expectedAmount);
        return this;
    }

    @Step("Validate monthly contribution")
    public AutoInstallmentSteps validateMonthlyContribution(String income) {
        String expectedContribution = calculateMonthlyContribution(income);
        PlaywrightAssertions.assertThat(autoInstallmentPage.monthlyContributionField).containsText(expectedContribution);
        return this;
    }

    @Step("Click on 'Take Loan' button")
    public AutoInstallmentSteps clickOnTakeLoanBtn() {
        autoInstallmentPage.takeLoanBtn.click();
        return this;
    }

    @Step("Validate loan form is visible")
    public AutoInstallmentSteps validateFormIsVisible() {
        PlaywrightAssertions.assertThat(autoInstallmentPage.loanForm).isVisible();
        return this;
    }
}
