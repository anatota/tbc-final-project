package ge.tbc.testautomation.api;

import ge.tbc.testautomation.api.marketing.offers.model.MarketingOffersResponse;
import ge.tbc.testautomation.api.marketing.offers.model.SearchOffersRequest;
import ge.tbc.testautomation.config.BaseApiConfig;
import ge.tbc.testautomation.data.ApiConstants;
import ge.tbc.testautomation.helper.CookieHelper;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class OffersApi extends BaseApiConfig {
    public OffersApi() {
        super(ApiConstants.BASE_URI);
    }


    /**
     * Fetches frontend cookies using Playwright and extracts the XSRF-TOKEN.
     */
    public String fetchXsrfToken(Map<String, String> cookies) {
        String xsrf = cookies.get("XSRF-TOKEN");
        if (xsrf == null || xsrf.isEmpty()) {
            throw new RuntimeException("❌ No XSRF-TOKEN found in Playwright cookies: " + cookies);
        }

        System.out.println("✅ Got XSRF-TOKEN: " + xsrf);
        return xsrf;
    }

    /**
     * Sends POST request to TBC marketing offers endpoint.
     */
    public MarketingOffersResponse postOffers(SearchOffersRequest body) {
        // Step 1. Fetch cookies via Playwright
        Map<String, String> cookies = CookieHelper.getFrontendCookies();
        System.out.println("🍪 Cookies fetched: " + cookies);

        // Step 2. Extract XSRF token
        String xsrfToken = fetchXsrfToken(cookies);

        // Step 3. Send POST request
        Response response = given()
                .spec(spec)
                .cookies(cookies)
                .header("x-xsrf-token", xsrfToken)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(body)
                .log().all()
                .when()
                .post("/marketing/entries/offer")
                .then()
                .log().all()
                .statusCode(200)
                .extract()
                .response();

        System.out.println("✅ Request successful! Parsing response...");

        // Step 4. Deserialize JSON -> POJO
        return response.as(MarketingOffersResponse.class);
    }
}
