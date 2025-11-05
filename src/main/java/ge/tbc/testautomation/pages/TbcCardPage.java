package ge.tbc.testautomation.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class TbcCardPage extends BasePage {
    public Locator monthlyPriciesBtn,
            annualPriciesBtn,
            tbcCard,
            conceptCard,
            concept360Card;

    public TbcCardPage(Page page) {
        super(page);
        annualPriciesBtn = page.locator("xpath=//div[@class='tbcx-pw-tab-segment tbcx-pw-tab-segment--condensed']//button[.//span[text()='ყოველწლიურად']]").first();
        monthlyPriciesBtn = page.locator("xpath=//div[@class='tbcx-pw-tab-segment tbcx-pw-tab-segment--condensed']//button[.//span[text()='ყოველთვიურად']]").first();

        tbcCard = page.locator("xpath=//p[@class = 'period-header-cell__period-description__text']").nth(0);
        conceptCard = page.locator("xpath=//p[@class = 'period-header-cell__period-description__text']").nth(1);
        concept360Card = page.locator("xpath=//p[@class = 'period-header-cell__period-description__text']").nth(2);
    }

}
