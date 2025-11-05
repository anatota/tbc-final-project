package ge.tbc.testautomation.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class BasePage {

    public Locator forMeLink,
    creditCardPageLink,
            autoLoanSectionLink;

    public BasePage(Page page) {
        forMeLink = page.getByRole(AriaRole.BANNER).getByRole(AriaRole.LINK, new Locator.GetByRoleOptions().setName("ჩემთვის"));
        autoLoanSectionLink = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("ავტო სესხი"));
        creditCardPageLink = page.locator("//a[.//text()='საკრედიტო ბარათი']");
    }
}
