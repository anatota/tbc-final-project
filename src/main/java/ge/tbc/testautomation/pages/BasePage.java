package ge.tbc.testautomation.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class BasePage {
    public Locator cookieReject,
            forMeLink,
            autoLoanSectionLink,
            goToTbcCardsButton,
            creditCardPageLink,
            schoolCardNavigation;

    public BasePage(Page page) {
        this.cookieReject = page.locator("//div[@class='tbcx-pw-cookie-consent']//button[contains(@class, 'secondary')]").getByText("უარყოფა");
        forMeLink = page.getByRole(AriaRole.BANNER).getByRole(AriaRole.LINK, new Locator.GetByRoleOptions().setName("ჩემთვის"));
        autoLoanSectionLink = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("ავტო სესხი"));
        goToTbcCardsButton = page.locator("//a[@href='/ka/tbc-card']//button[text()='ვრცლად']");
        schoolCardNavigation = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("მოსწავლის ბარათი"));
        creditCardPageLink = page.locator("//a[.//text()='საკრედიტო ბარათი']");
    }
}
