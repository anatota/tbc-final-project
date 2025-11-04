package ge.tbc.testautomation.tests;

import ge.tbc.testautomation.steps.AutoInstallmentSteps;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
@Listeners(ge.tbc.testautomation.tests.allurescreenshotlistener.class)
public class AutoInstallmentIncomeTest extends BaseTest {
    AutoInstallmentSteps autoInstallmentSteps;

    @BeforeClass
    public void initializeSteps() {
        autoInstallmentSteps = new AutoInstallmentSteps(page);
    }

    @Test(priority = 1, description = "ავტოგანვადების გვერდზე ნავიგაცია")
    public void enterToAutoInstalmentPageTest() {
        autoInstallmentSteps
                .hoverOnForMeLink()
                .clickOnAutoLoanSectionLink();
        autoInstallmentSteps.clickOnAutoInstallmentButton()
                .validateAutoInstallmentPageIsOpened()
                .validateInterestRate("15.5% დან")
                .validateEffectiveInterestRate("26.13% დან");
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
                .enterIncome("1000")
                .enterLoanPeriod("12")
                .validateInstallmentAmount("2,762.60₾")
                .validateMonthlyContribution("1000");
    }

    @Test(priority = 4, description = "სესხის აღება")
    public void takeLoanTest() {
        autoInstallmentSteps
                .clickOnTakeLoanBtn()
                .validateFormIsVisible();
    }
}
