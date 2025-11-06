package ge.tbc.testautomation.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class Header extends BasePage {
    public final Locator personalHeader,
            burgerMenuBtn,
            activeHeader,
            autoLoanCategoryBtn,
            loanHamburgerMenuBtn;

    public Header(Page page) {
        super(page);
        this.personalHeader = page.locator("//div[contains(text(), 'Personal')]").first();
        this.burgerMenuBtn = page.locator(".tbcx-pw-hamburger-menu button");
        this.activeHeader = page.locator("div[class = 'tbcx-pw-navigation-item tbcx-pw-navigation-item--selected']");
        this.autoLoanCategoryBtn = page.locator("//span[contains(text(), 'Auto Loan')]");
        this.loanHamburgerMenuBtn = page.locator("//span[contains(text(), 'Loans')]");
    }
}
