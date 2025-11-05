package ge.tbc.testautomation.helper;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.Cookie;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CookieHelper {

    public static Map<String, String> getFrontendCookies() {
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch(
                    new BrowserType.LaunchOptions().setHeadless(true)
            );

            BrowserContext context = browser.newContext();
            Page page = context.newPage();

            page.navigate("https://tbcbank.ge/ka/tbc-youth/pupil-card");
            page.waitForTimeout(3000);

            List<Cookie> cookies = context.cookies();
            Map<String, String> cookieMap = cookies.stream()
                    .collect(Collectors.toMap(c -> c.name, c -> c.value));

            // optional: log them for debugging
            cookieMap.forEach((k, v) -> System.out.println("🍪 " + k + "=" + v));

            context.close();
            browser.close();
            return cookieMap;
        }
    }
}
