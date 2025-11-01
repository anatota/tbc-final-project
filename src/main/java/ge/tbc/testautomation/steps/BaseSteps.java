package ge.tbc.testautomation.steps;

import com.microsoft.playwright.Page;
import ge.tbc.testautomation.pages.BasePage;

public class BaseSteps {
    Page page;
    BasePage basePage;

    public BaseSteps(Page page) {
        this.page = page;
        basePage = new BasePage(page);
    }


}
