package ge.tbc.testautomation.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import io.qameta.allure.Step;

public class BasePage {
    public Locator cookieReject,
            forMeLink,
            autoLoanSectionLink,
            goToTbcCardsButton,
            creditCardPageLink,
            schoolCardNavigation;


    public final Locator acceptAllCookies;
    public final Locator rejectCookieButton;
    public final Locator customiseCookieButton;
    public final Locator languageSwitcherBtn;
    public BasePage(Page page) {
        this.cookieReject = page.locator("//div[@class='tbcx-pw-cookie-consent']//button[contains(@class, 'secondary')]").getByText("უარყოფა");
        this.forMeLink = page.getByRole(AriaRole.BANNER).getByRole(AriaRole.LINK, new Locator.GetByRoleOptions().setName("ჩემთვის"));
        this.autoLoanSectionLink = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("ავტო სესხი"));
        this.goToTbcCardsButton = page.locator("//a[@href='/ka/tbc-card']//button[text()='ვრცლად']");
        this.schoolCardNavigation = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("მოსწავლის ბარათი"));
        this.creditCardPageLink = page.locator("//a[.//text()='საკრედიტო ბარათი']");
        this.acceptAllCookies= page.locator("//div[@class='tbcx-pw-cookie-consent__actions']//button").nth(0);
        this.rejectCookieButton=page.locator("//div[@class='tbcx-pw-cookie-consent__actions']//button").nth(2);
        this.customiseCookieButton=page.locator("//div[@class='tbcx-pw-cookie-consent__actions']//button").nth(1);
        this.languageSwitcherBtn=page.locator("tbcx-lang-switcher").first();

    }

}
