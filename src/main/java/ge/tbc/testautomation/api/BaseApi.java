package ge.tbc.testautomation.api;

import ge.tbc.testautomation.helper.ApiSession;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public abstract class BaseApi {
    protected final RequestSpecification spec;

    protected BaseApi(String baseUri) {
        this.spec = new RequestSpecBuilder()
                .setBaseUri(baseUri)
                .setContentType(ContentType.JSON)
                .addHeader("accept", "application/json, text/plain, */*")
                .addHeader("origin", "https://tbcbank.ge")
                .addHeader("referer", "https://tbcbank.ge/")
                // cookie filter automatically attached below
                .addFilter(ApiSession.COOKIE_JAR)
                .build();
    }
}
