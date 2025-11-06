package ge.tbc.testautomation.steps;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;
import ge.tbc.testautomation.pages.Header;
import io.qameta.allure.Step;

public class HeaderSteps {
    Header pageObj ;
    public HeaderSteps(Page page) {
        this.pageObj = new Header(page);
    }
    @Step("Hover over Personal navigation item")
    public HeaderSteps hoverOverPersonalNavItem() {
        pageObj.personalHeader.hover();
        return this;
    }

    @Step("Click on Burger Menu button")
    public HeaderSteps clickOnBurgerMenuBtn() {
        pageObj.burgerMenuBtn.click();
        return this;
    }
    @Step("Verify Active Header Text")// didn't fit into the scenario well
    public HeaderSteps verifyActiveHeaderText(String expectedText) {
        try {
            pageObj.activeHeader.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
        } catch (Exception e) {
            throw new AssertionError("Active header is not visible within the timeout period.");
        }
        String actualText = pageObj.activeHeader.textContent().trim();
        if (!actualText.equals(expectedText)) {
            throw new AssertionError("Expected header text: " + expectedText + ", but got: " + actualText);
        }
        return this;
    }
    @Step("Click on Auto Loan Category button")
    public HeaderSteps clickOnAutoLoanCategoryBtn() {
        pageObj.autoLoanCategoryBtn.click();
        return this;
    }
    @Step("click Loan category Hamburger menu")
    public HeaderSteps clickLoanCategoryHamburgerMenu() {
        pageObj.loanHamburgerMenuBtn.nth(1).click();

        return this;
    }
    @Step("click auto loans mobile")
    public HeaderSteps clickAutoLoansMobile() {
        //sleep 2000
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Restore the interrupted status
            throw new RuntimeException("Thread was interrupted", e);
        }
        pageObj.autoLoanCategoryBtn.nth(1).click();
        return this;
    }

}
