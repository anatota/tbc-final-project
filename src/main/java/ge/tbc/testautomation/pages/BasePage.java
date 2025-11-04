package ge.tbc.testautomation.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class BasePage {
    public Locator cookieReject;
    public Locator forMeLink,
            autoLoanSectionLink;
    public Locator schoolCardNavigation;

    public BasePage(Page page) {
        this.cookieReject = page.locator("//div[@class='tbcx-pw-cookie-consent']//button[contains(@class, 'secondary')]").getByText("უარყოფა");
        forMeLink = page.getByRole(AriaRole.BANNER).getByRole(AriaRole.LINK, new Locator.GetByRoleOptions().setName("ჩემთვის"));
        autoLoanSectionLink = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("ავტო სესხი"));
        schoolCardNavigation = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("მოსწავლის ბარათი"));
    }
}
