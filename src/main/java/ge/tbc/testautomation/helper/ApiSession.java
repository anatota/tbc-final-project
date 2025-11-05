package ge.tbc.testautomation.helper;

import io.restassured.filter.cookie.CookieFilter;

/**
 * Shared cookie jar between requests — behaves like Postman’s session.
 */
public class ApiSession {
    public static final CookieFilter COOKIE_JAR = new CookieFilter();
}
