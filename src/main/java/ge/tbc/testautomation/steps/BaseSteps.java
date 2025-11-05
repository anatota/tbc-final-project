package ge.tbc.testautomation.steps;

import com.microsoft.playwright.Page;
import ge.tbc.testautomation.pages.BasePage;
import io.qameta.allure.Step;

import static org.testng.Assert.assertEquals;

public class BaseSteps {
    Page page;
    BasePage basePage;

    public BaseSteps(Page page) {
        this.page = page;
        basePage = new BasePage(page);
    }

    @Step("Hover on 'For Me' link")
    public BaseSteps hoverOnForMeLink() {
        basePage.forMeLink.hover();
        return this;
    }

    @Step("Click on 'Auto Loan' section link")
    public BaseSteps clickOnAutoLoanSectionLink() {
        basePage.autoLoanSectionLink.click();
        return this;
    }
    @Step("\"აიღე თიბისი ბარათი\"-ზე გადასვლა \"ვრცლად\"-ზე  კლიკით\n")
    public BaseSteps clickOnGoToTbcCardsBtn() {
        basePage.goToTbcCardsButton.first().click();
        return this;
    }
    @Step("თიბისი ბარათების გვერდის ჩატვირთვა")
    public BaseSteps validateCurrentPage(String url) {
        assertEquals(url, page.url());
        return this;
    }
}
