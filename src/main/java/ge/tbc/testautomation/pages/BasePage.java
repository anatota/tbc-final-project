package ge.tbc.testautomation.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class BasePage {

    public Locator forMeLink,
            autoLoanSectionLink,
            goToTbcCardsButton;

    public BasePage(Page page) {
        forMeLink = page.getByRole(AriaRole.BANNER).getByRole(AriaRole.LINK, new Locator.GetByRoleOptions().setName("ჩემთვის"));
        autoLoanSectionLink = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("ავტო სესხი"));
        goToTbcCardsButton = page.locator("//a[@href='/ka/tbc-card']//button[text()='ვრცლად']");

    }
}
