package ge.tbc.testautomation.tests;

import com.microsoft.playwright.*;
import ge.tbc.testautomation.steps.BaseSteps;
import org.testng.annotations.*;

import static ge.tbc.testautomation.data.Constants.BASE_URL;

public class BaseTest {
    BaseSteps baseSteps;
    private boolean isFirstRun = true;

    protected Playwright playwright;
    protected Browser browser;
    protected BrowserContext browserContext;
    protected Page page;

    @BeforeClass
    public void setUp() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        browserContext = browser.newContext();
        page = browserContext.newPage();
        if (isFirstRun) {
            baseSteps.rejectCookies();
            isFirstRun = false;
        }
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