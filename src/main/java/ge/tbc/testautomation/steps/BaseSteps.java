package ge.tbc.testautomation.steps;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;
import ge.tbc.testautomation.pages.BasePage;
import io.qameta.allure.Step;

import static org.testng.Assert.assertEquals;
import java.util.regex.Pattern;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class BaseSteps {
    Page page;
    BasePage basePage;

    public BaseSteps(Page page) {
        this.page = page;
        basePage = new BasePage(page);
    }

    @Step("Hover on 'For Me' link")
    public BaseSteps hoverOnForMeLink() {
        basePage.forMeLink.hover();
        return this;
    }

    @Step("Click on 'Auto Loan' section link")
    public BaseSteps clickOnAutoLoanSectionLink() {
        basePage.autoLoanSectionLink.click();
        return this;
    }
  
    @Step("\"აიღე თიბისი ბარათი\"-ზე გადასვლა \"ვრცლად\"-ზე  კლიკით\n")
    public BaseSteps clickOnGoToTbcCardsBtn() {
        basePage.goToTbcCardsButton.first().click();
        return this;
    }
    @Step("თიბისი ბარათების გვერდის ჩატვირთვა")
    public BaseSteps validateCurrentPage(String url) {
        assertEquals(url, page.url());
        return this;
    }
  
    @Step("Click on reject cookies")
    public BaseSteps rejectCookies() {
        basePage.cookieReject.click();
        return this;
    }

    @Step("Navigate to item")
    public BaseSteps navigateToItem(Locator locator) {
        locator.hover();
        return this;
    }

    @Step("Click on item")
    public BaseSteps clickOnItem(Locator locator) {
        locator.click();
        return this;
    }

    @Step("Fill item field")
    public BaseSteps fillItemField(Locator locator, String value) {
        locator.fill(value);
        return this;
    }

    public BaseSteps assertItemVisibility(Locator locator) {
        assertThat(locator).isVisible();
        return this;
    }

    public BaseSteps assertText(Locator locator, String text) {
        assertThat(locator).hasText(text);
        return this;
    }

    public BaseSteps waitForLocator(Locator locator) {
        locator.waitFor(new Locator.WaitForOptions()
                .setState(WaitForSelectorState.VISIBLE)
                .setTimeout(10000));
        return this;
    }

    public BaseSteps itemHasClass(Locator locator, String className) {
        String regex = String.format(".*%s.*", className);
        assertThat(locator).hasClass(Pattern.compile(regex));
        return this;
      
    @Step("Navigate to credit card page")
    public CreditCardSteps navigateToCreditCardPage(){
     basePage.creditCardPageLink.click();
     return new CreditCardSteps(page);
    }
}
