package ge.tbc.testautomation.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class SchoolCardPage extends BasePage {
    public Locator orderBtn;
    public SchoolCardPage(Page page) {
        super(page);
        this.orderBtn = page.locator("//a[contains(@href, 'youth-wizard')]//button[contains(@class, primary)]");
    }
}
