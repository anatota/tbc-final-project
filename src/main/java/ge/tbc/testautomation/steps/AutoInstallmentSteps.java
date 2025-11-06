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
    public AutoInstallmentSteps clickOnTakeLoanBtn() {// so that it can fit bot languages automatically
        if (autoInstallmentPage.takeLoanBtn.isVisible()) {
            autoInstallmentPage.takeLoanBtn.click();
        } else {
            autoInstallmentPage.takeLoanBtnEng.click();
        }
        return this;
    }

    @Step("Validate loan form is visible")
    public AutoInstallmentSteps validateFormIsVisible() {
        PlaywrightAssertions.assertThat(autoInstallmentPage.loanForm).isVisible();
        return this;
    }
    @Step("fill in Ammount ")
    public AutoInstallmentSteps fillInAmmount(String ammount) {
        autoInstallmentPage.calculatorInputFields.nth(0).fill(ammount);
        return this;
    }
    @Step("fill in Period ")
    public AutoInstallmentSteps fillInPeriod(String period) {
        autoInstallmentPage.calculatorInputFields.nth(1).fill(period);
        return this;
    }
    @Step("Calculate monthly payment for {0} GEL at {1}% over {2} months")
    public AutoInstallmentSteps verifycalculateExpectedMonthlyPayment(double principalAmount, int months ) {
        double annualInterestRate = Double.parseDouble(autoInstallmentPage.outputedFields.nth(2).innerText().replaceAll("[^\\d.]", ""));        // 1. Convert annual percentage rate to a monthly decimal rate
        double monthlyRate = (annualInterestRate / 100.0) / 12.0;
        // 2. Calculate the compound interest factor
        double powerTerm = Math.pow(1 + monthlyRate, months);
        // 3. Apply the standard annuity formula
        double monthlyPayment = principalAmount * (monthlyRate * powerTerm) / (powerTerm - 1);
        // 4. Round to 2 decimal places to match financial calculations
        monthlyPayment = Math.round(monthlyPayment * 100.0) / 100.0;
        validateInstallmentAmount(monthlyPayment + "₾");
        return this ;
    }
}
