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

    @Step("Assert item visibility")
    public BaseSteps assertItemVisibility(Locator locator) {
        assertThat(locator).isVisible();
        return this;
    }

    @Step("Assert item text")
    public BaseSteps assertText(Locator locator, String text) {
        assertThat(locator).hasText(text);
        return this;
    }

    @Step("Wait for locator to be visible")
    public BaseSteps waitForLocator(Locator locator) {
        locator.waitFor(new Locator.WaitForOptions()
                .setState(WaitForSelectorState.VISIBLE)
                .setTimeout(10000));
        return this;
    }

    @Step("Assert item has class: {className}")
    public BaseSteps itemHasClass(Locator locator, String className) {
        String regex = String.format(".*%s.*", className);
        assertThat(locator).hasClass(Pattern.compile(regex));
        return this;
    }

    @Step("Navigate to credit card page")
    public CreditCardSteps navigateToCreditCardPage() {
        basePage.creditCardPageLink.click();
        return new CreditCardSteps(page);
    }
    @Step("Accept All Cookies")
    public BaseSteps acceptAllCookies() {
        basePage.acceptAllCookies.click();
        return this;
    }
    @Step("Check Cookie Colours ")
    public BaseSteps CheckCookieCollorsForNonGPRCountries() {
        page.reload();
        String backgroundColor = basePage.acceptAllCookies.evaluate("element => window.getComputedStyle(element).backgroundColor").toString();
        String backgroundColorofRejectButton = basePage.rejectCookieButton.evaluate("element => window.getComputedStyle(element).backgroundColor").toString();
        String backgroundcolorofCustomise = basePage.customiseCookieButton.evaluate("element => window.getComputedStyle(element).backgroundColor").toString();
        assert backgroundColor.equals("rgb(0, 173, 238)") : "Accept button color is not blue";
        assert backgroundColorofRejectButton.equals("rgb(255, 255, 255)") : "Reject button color is not grey";
        assert backgroundcolorofCustomise.equals("rgb(255, 255, 255)") : "Customise button color is not grey";
        return this ;
    }
    @Step("Click on Language Switcher button")
    public BaseSteps clickOnLanguageSwitcherBtn() {
        //side note :allowing waitin  after switching language cuss page needs to reaload
        String current_Language = basePage.languageSwitcherBtn.textContent().trim();
        basePage.languageSwitcherBtn.click();
        String url = page.url();
        try {
            for (int i = 0; i < 150; i++) { // Polling mechanism with a timeout
                String newText = basePage.languageSwitcherBtn.textContent().trim();

                Thread.sleep(100);
                if (!newText.equals(current_Language ) && !page.url().equals(url)) {
                    break;
                }
                Thread.sleep(100);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Restore the interrupted status
            throw new RuntimeException("Thread was interrupted", e);
        }
        return this;
    }
    @Step("switch language mobile")
    public BaseSteps clickOnLanguageSwitcherBtnMobile() {
        basePage.languageSwitcherBtnMobile.click();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Restore the interrupted status
            throw new RuntimeException("Thread was interrupted", e);
        }
        return this;
    }
    @Step("reload page with wait")
    public BaseSteps realoadPage(){
        page.reload();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Restore the interrupted status
            throw new RuntimeException("Thread was interrupted", e);
        }
        return this;
    }
    @Step("assert that any Google analytics cookie is not present ")
    public BaseSteps ckeckforGoogleAnalytics(){
        boolean cookieFound = page.context().cookies().stream()
                .anyMatch(cookie -> cookie.name.contains("_ga"));
        assert cookieFound : " cookie containing '_ga' found!";
        return this;
    }
}
