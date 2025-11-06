package ge.tbc.testautomation.tests;

import ge.tbc.testautomation.steps.AutoInstallmentSteps;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static ge.tbc.testautomation.data.Constants.*;

@Listeners(allurescreenshotlistener.class)
public class AutoInstallmentIncomeTest extends BaseTest {
    AutoInstallmentSteps autoInstallmentSteps;

    @BeforeClass
    public void initializeSteps() {
        page.navigate(BASE_URL);
        autoInstallmentSteps = new AutoInstallmentSteps(page);
    }

    @Test(priority = 1, description = "ავტოგანვადების გვერდზე ნავიგაცია")
    public void enterToAutoInstalmentPageTest() {
        autoInstallmentSteps
                .hoverOnForMeLink()
                .clickOnAutoLoanSectionLink();
        autoInstallmentSteps.clickOnAutoInstallmentButton()
                .validateAutoInstallmentPageIsOpened()
                .validateInterestRate(EXPECTED_INTEREST_RATE)
                .validateEffectiveInterestRate(EXPECTED_EFFECTIVE_INTEREST_RATE);
    }

    @Test(priority = 2, description = "შემოსავლის გამოთვლის არჩევა")
    public void selectIncomeCalculationTest() {
        autoInstallmentSteps
                .clickOnIncomeCalculatorBtn()
                .validateIncomeCalculatorIsOpened();
    }

    @Test(priority = 3, description = "თანხის და ვადის მითითება")
    public void validateIncomeAndPeriodTest() {
        autoInstallmentSteps
                .enterIncome(INCOME)
                .enterLoanPeriod(LOAN_PERIOD)
                .validateInstallmentAmount(EXPECTED_INSTALLMENT_AMOUNT)
                .validateMonthlyContribution(INCOME);
    }

    @Test(priority = 4, description = "სესხის აღება")
    public void takeLoanTest() {
        autoInstallmentSteps
                .clickOnTakeLoanBtn()
                .validateFormIsVisible();
    }
}
