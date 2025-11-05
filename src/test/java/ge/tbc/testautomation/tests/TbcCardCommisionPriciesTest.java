package ge.tbc.testautomation.tests;

import ge.tbc.testautomation.steps.TbcCardSteps;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static ge.tbc.testautomation.data.Constants.CARD_PAGE_URL;

public class TbcCardCommisionPriciesTest extends BaseTest {
    TbcCardSteps tbcCardSteps;

    @BeforeClass
    public void initializeSteps() {
        tbcCardSteps = new TbcCardSteps(page);
    }

    @Test(priority = 1, description = "ბარათების გვერდზე გადასვლა")
    public void testGoToCardsPageTest() {
        tbcCardSteps
                .clickOnGoToTbcCardsBtn()
                .validateCurrentPage(CARD_PAGE_URL);
    }

    @Test(priority = 2, description = "ბარათების ყოველწლიური ფასები")
    public void testCardsAnnualyPriciesTest() {
        tbcCardSteps
                .clickOnAnnuityBtn()
                .validateCardsAnnuallyPricies();
    }

    @Test(priority = 3, description = "ბარატბის ყოველთვიური ფასები")
    public void testCardsMonthlyPriciesTest() {
        tbcCardSteps
                .clickOnMonthlyBtn()
                .validateCardsMonthlyPricies();
    }


}
