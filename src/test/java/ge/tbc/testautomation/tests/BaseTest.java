package ge.tbc.testautomation.tests;

import com.microsoft.playwright.*;
import org.testng.annotations.*;

import static ge.tbc.testautomation.data.Constants.BASE_URL;

public class BaseTest {
    Playwright playwright;
    Browser browser;
    BrowserContext browserContext;
    Page page;

    @BeforeTest
    public void setUp() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(50));
        browserContext = browser.newContext();
        page = browserContext.newPage();
    }

    @BeforeClass
    public void navigateToBaseUrl() {
        page.navigate(BASE_URL);
    }

    @AfterClass
    public void tearDown() {
        if (browserContext != null) {
            browserContext.close();
        }
        if (browser != null) {
            browser.close();
        }
        if (playwright != null) {
            playwright.close();
        }
    }
}