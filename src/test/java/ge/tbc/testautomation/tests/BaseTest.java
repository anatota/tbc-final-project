package ge.tbc.testautomation.tests;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.Geolocation;
import org.testng.annotations.*;

import java.util.List;

import static ge.tbc.testautomation.data.Constants.BASE_URL;

public class BaseTest {

    protected Playwright playwright;
    protected Browser browser;
    protected BrowserContext browserContext;
    protected Page page;

    @BeforeClass
    @Parameters({"browserType", "device"})
    public void setUp(
            @Optional("chrome") String browserType,
            @Optional("DESKTOP") String deviceType) {

        Device device = Device.valueOf(deviceType.toUpperCase());

        playwright = Playwright.create();
        BrowserType.LaunchOptions options = new BrowserType.LaunchOptions()
                .setHeadless(false)
                .setArgs(List.of("--window-position=0,0"));

        switch (browserType.toLowerCase()) {
            case "chrome":
                browser = playwright.chromium().launch(options.setChannel("chrome"));
                break;
            case "webkit":
                browser = playwright.webkit().launch(options);
                break;
            default:
                throw new IllegalArgumentException("Unsupported browser: " + browserType);
        }

        browserContext = browser.newContext(new Browser.NewContextOptions()
                .setViewportSize(device.getViewportWidth(), device.getViewportHeight())
                .setPermissions(List.of("geolocation"))
                .setGeolocation(new Geolocation(41.7151, 44.8271))
        );

        page = browserContext.newPage();
        page.setDefaultTimeout(60000);
        page.setDefaultNavigationTimeout(80000);

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

    private enum Device {
        DESKTOP(1920, 1080),
        MOBILE(375, 667);

        private final int viewportWidth;
        private final int viewportHeight;

        Device(int viewportWidth, int viewportHeight) {
            this.viewportWidth = viewportWidth;
            this.viewportHeight = viewportHeight;
        }

        public int getViewportWidth() {
            return viewportWidth;
        }

        public int getViewportHeight() {
            return viewportHeight;
        }
    }
}