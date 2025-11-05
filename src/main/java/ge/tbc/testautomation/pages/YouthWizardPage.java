package ge.tbc.testautomation.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class YouthWizardPage extends BasePage {
    public Locator idNumberField,
            continueBtn,
            mobileNumberField;

    public YouthWizardPage(Page page) {
        super(page);
        this.idNumberField = page.locator("//label[contains(@for, 'text-input')]");
        this.continueBtn = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("გაგრძელება"));
        this.mobileNumberField = page.getByLabel("ტელეფონის ნომერი");
    }
}
