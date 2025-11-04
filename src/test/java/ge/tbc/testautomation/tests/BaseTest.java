package ge.tbc.testautomation.tests;

import com.microsoft.playwright.*;
import ge.tbc.testautomation.steps.BaseSteps;
import org.testng.annotations.*;

import static ge.tbc.testautomation.data.Constants.BASE_URL;

public class BaseTest {
    Playwright playwright;
    Browser browser;
    BrowserContext browserContext;
    Page page;
    BaseSteps baseSteps;
    private boolean isFirstRun = true;

    @BeforeTest
    public void setUp() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
                .setHeadless(false)
                .setSlowMo(50)
        );
        browserContext = browser.newContext();
        page = browserContext.newPage();
        baseSteps = new BaseSteps(page);
    }

    @BeforeClass
    public void navigateToBaseUrl() {
        page.navigate(BASE_URL);
        if(isFirstRun) {
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