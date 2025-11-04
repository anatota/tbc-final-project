package ge.tbc.testautomation.steps;

import com.microsoft.playwright.Page;
import ge.tbc.testautomation.pages.BasePage;
import io.qameta.allure.Step;

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
}
