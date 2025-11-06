package ge.tbc.testautomation.tests;

import ge.tbc.testautomation.steps.AutoInstallmentSteps;
import ge.tbc.testautomation.steps.BaseSteps;
import ge.tbc.testautomation.steps.HeaderSteps;
import org.testng.annotations.BeforeClass;
 import org.testng.annotations.Listeners;

 import org.testng.annotations.Test;

import static ge.tbc.testautomation.data.Constants.BASE_URL;


@Listeners(allurescreenshotlistener.class)

 public class AutoInstallmentIncomeTestModeB extends BaseTest {

     HeaderSteps headerSteps;

     AutoInstallmentSteps autoInstallmentSteps;

     BaseSteps baseSteps;


     @BeforeClass
     public void initializeSteps() {
         page.navigate(BASE_URL);
         headerSteps = new HeaderSteps(page);
         autoInstallmentSteps = new AutoInstallmentSteps(page);
         baseSteps = new BaseSteps(page);
         baseSteps.clickOnLanguageSwitcherBtn()
                 .CheckCookieCollorsForNonGPRCountries()
                 .acceptAllCookies()
                 .realoadPage()
                 .ckeckforGoogleAnalytics();
                    }
                    @Test(priority = 1, description = "Navigate to Auto Loan Category")
                    public void navigateToAutoLoanCategory() {
                        headerSteps
                                .hoverOverPersonalNavItem()
                                .clickOnAutoLoanCategoryBtn();
                    }

                    @Test(priority = 2, description = "Open Auto Installment Page")
                    public void openAutoInstallmentPage() {
                        autoInstallmentSteps.clickOnAutoInstallmentButton();
                    }

                    @Test(priority = 3, description = "Fill in Amount and Period")
                    public void fillInAmountAndPeriod() {
                        autoInstallmentSteps
                                .fillInAmmount("1900")
                                .fillInPeriod("40");
                    }

                    @Test(priority = 4, description = "Verify Monthly Payment Calculation")
                    public void verifyMonthlyPaymentCalculation() {
                        autoInstallmentSteps
                                .verifycalculateExpectedMonthlyPayment(1900, 40);
                    }

                    @Test(priority = 5, description = "Take Loan and Validate Form")
                    public void takeLoanAndValidateForm() {
                        autoInstallmentSteps
                                .clickOnTakeLoanBtn()
                                .validateFormIsVisible();
                    }
                }