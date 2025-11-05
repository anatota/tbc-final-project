package ge.tbc.testautomation.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class OTPValidationPage extends BasePage {
    public Locator codeField;
    public Locator codeFieldContainer;
    public Locator errorMessage;

    public OTPValidationPage(Page page) {
        super(page);
        this.codeField = page.locator("//label[contains(text(), 'კოდი')]");
        this.codeFieldContainer = page.locator("//div[contains(@class, 'tbcx-text-input size-normal')]");
        this.errorMessage = page.locator("//div[contains(@class, 'description')]");
    }
}
