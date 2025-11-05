package ge.tbc.testautomation.steps;

import com.microsoft.playwright.Page;
import ge.tbc.testautomation.pages.TbcCardPage;
import io.qameta.allure.Step;
import org.testng.Assert;

public class TbcCardSteps extends BaseSteps {
    TbcCardPage tbcCardPage;

    public TbcCardSteps(Page page) {
        super(page);
        this.tbcCardPage = new TbcCardPage(page);
    }

    @Step("\"ყოველწლიურად\"-ის მონიშვნა კლიკით")
    public TbcCardSteps clickOnAnnuityBtn() {
        tbcCardPage.annualPriciesBtn.click();
        return this;
    }

    @Step("ბარათების ტარიფები ჩანს წლიური ფასებით\n")
    public TbcCardSteps validateCardsAnnuallyPricies() {
        Assert.assertTrue(tbcCardPage.tbcCard.innerText().contains("0"));
        Assert.assertTrue(tbcCardPage.conceptCard.innerText().contains("250"));
        Assert.assertTrue(tbcCardPage.concept360Card.innerText().contains("400"));
        return this;
    }

    @Step("\"ყოველთვიურად\"-ის მონიშვნა კლიკით")
    public TbcCardSteps clickOnMonthlyBtn() {
        tbcCardPage.monthlyPriciesBtn.click();
        return this;
    }

    @Step("ბარათების ტარიფები ჩანს თვიური ფასებით\n")
    public TbcCardSteps validateCardsMonthlyPricies() {
        Assert.assertTrue(tbcCardPage.tbcCard.innerText().contains("0"));
        Assert.assertTrue(tbcCardPage.conceptCard.innerText().contains("25"));
        Assert.assertTrue(tbcCardPage.concept360Card.innerText().contains("40"));

        return this;
    }
}
