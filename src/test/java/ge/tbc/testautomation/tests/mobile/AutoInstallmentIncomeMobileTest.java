package ge.tbc.testautomation.tests.mobile;

import ge.tbc.testautomation.steps.AutoInstallmentSteps;
import ge.tbc.testautomation.steps.BaseSteps;
import ge.tbc.testautomation.steps.HeaderSteps;
import ge.tbc.testautomation.tests.BaseTest;
import ge.tbc.testautomation.tests.allurescreenshotlistener;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static ge.tbc.testautomation.data.Constants.BASE_URL;

@Listeners(allurescreenshotlistener.class)
public class AutoInstallmentIncomeMobileTest extends BaseTest {
    HeaderSteps headerSteps;
    AutoInstallmentSteps autoInstallmentSteps;
    BaseSteps baseSteps;

    @BeforeClass
    public void initializeSteps() {
        page.navigate(BASE_URL);
        headerSteps = new HeaderSteps(page);
        autoInstallmentSteps = new AutoInstallmentSteps(page);
        baseSteps = new BaseSteps(page);
        baseSteps
                .acceptAllCookies()
                .realoadPage()
                .ckeckforGoogleAnalytics()
                .clickOnLanguageSwitcherBtnMobile();
    }

    @Test(description = "Auto Installment Income Verification Mobile ", groups = "Auto Installment Income Verification Mobile")
    public void verifyAutoInstallmentIncomeMobile() {
        headerSteps.clickOnBurgerMenuBtn()
                .clickLoanCategoryHamburgerMenu()
                .clickAutoLoansMobile();
        autoInstallmentSteps.clickOnAutoInstallmentButton()
                .fillInAmmount("2500")
                .fillInPeriod("30")
                .verifycalculateExpectedMonthlyPayment(2500, 30);
    }
}
