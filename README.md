# TBC Test Automation Final Project

This project is an end\-to\-end automated test suite for TBC Bank card products (web and API). It validates that key customer flows work correctly and that the backend returns correct data to the UI, with special focus on **secure API testing behind antiforgery / CSRF protection**.

---

## Table of contents

1. Overview
2. Tech stack
3. Project structure
4. UI test coverage
5. API test coverage \- antiforgery / CSRF solution
6. Negative scenario: ordering a school card with invalid code
7. How to run the tests
8. Extending and maintaining the framework

---

## 1\. Overview

The suite covers:

- **Web UI flows** for TBC card products (TBC Card, School Card, Credit Card, Auto Installment, Youth Wizard, OTP validation).
- **API tests** for marketing offers and related endpoints, including antiforgery / CSRF handling.
- Shared test infrastructure for configuration, sessions, and page/step objects.

The main goal is to have stable, realistic automated regression tests that exercise the system in a way similar to a real user and browser \- including security mechanisms such as antiforgery cookies and tokens.

---

## 2\. Tech stack

- **Language**: Java (tests and framework), JavaScript (performance script)
- **Build tool**: Maven
- **Test framework**: TestNG (see `TestEN.xml`)
- **UI testing**: Playwright (desktop and mobile\-emulated flows)
- **API testing**: RestAssured (wrapped by project utilities)
- **Assertion library**: Hamcrest
- **Supporting scripts**: JavaScript (`tbcCardPerformanceTest.js`) for additional checks/performance tests
- **Reporting / utilities**: `allurescreenshotlistener.java` and standard TestNG/Maven reports

---

## 3\. Project structure

Key packages:

- `src/main/java/ge/tbc/testautomation/api`
    - `BaseApi.java`, `OffersApi.java` \- core API client abstractions.
    - `marketing/offers/model` \- POJOs for marketing offers: `MarketingOffersResponse`, `Offer`, `PagingDetails`, `Partner`, `Segment`, `SimpleImage`, `SearchOffersRequest`.

- `src/main/java/ge/tbc/testautomation/config`
    - `BaseApiConfig.java` \- configuration for base URLs and API settings.

- `src/main/java/ge/tbc/testautomation/data`
    - `ApiConstants.java`, `Constants.java` \- endpoints, paths, test constants.

- `src/main/java/ge/tbc/testautomation/helper`
    - `ApiSession.java`, `CookieHelper.java`, `Helper.java` \- session management, antiforgery cookie handling, common utilities.

- `src/main/java/ge/tbc/testautomation/pages`
    - Playwright page objects for UI: `TbcCardPage`, `SchoolCardPage`, `CreditCardPage`, `AutoInstallmentPage`, `YouthWizardPage`, `Header`, `BasePage`, `OTPValidationPage`.

- `src/main/java/ge/tbc/testautomation/steps`
    - Step layers that orchestrate page objects and APIs: `TbcCardSteps`, `SchoolCardSteps`, `CreditCardSteps`, `AutoInstallmentSteps`, `HeaderSteps`, `OffersApiSteps`, etc.

- `src/test/java/ge/tbc/testautomation/tests`
    - `BaseTest.java` \- common Playwright / environment setup.
    - UI tests: `RequestCreditCardTest.java`, `TbcCardCommisionPriciesTest.java`, `AutoInstallmentIncomeTest.java`, `AutoInstallmentIncomeTestModeB.java`, `SchoolCardTest.java`.
    - Mobile UI tests: `mobile/AutoInstallmentIncomeMobileTest.java` (mobile device emulation via Playwright).
    - API tests: `OffersApiTest.java`.
    - Utility: `allurescreenshotlistener.java`.

- Root\-level helpers
    - `TestEN.xml` \- TestNG suite configuration (UI + mobile tests).
    - `tbcCardPerformanceTest.js` \- JavaScript performance / auxiliary checks.

---

## 4\. UI test coverage

The UI tests are built on **Playwright** and use a **Page Object Model (POM)** combined with **Steps** classes:

- Page classes (e.g. `TbcCardPage`, `SchoolCardPage`) encapsulate:
    - Playwright locators.
    - Low\-level interactions such as clicking, typing, waiting for elements.

- Steps classes (e.g. `TbcCardSteps`, `SchoolCardSteps`) combine multiple page actions into higher\-level business flows (for example: *fill out full school card application*).

- Test classes under `src/test/java/ge/tbc/testautomation/tests` call these steps to keep the test code short and readable.

Covered flows include:

- Requesting different card types (TBC Card, School Card, Credit Card, etc.).
- Filling in customer and income details.
- Verifying that specific UI elements, errors, and confirmations appear.
- Running some flows in **mobile mode** (see `ge.tbc.testautomation.tests.mobile.AutoInstallmentIncomeMobileTest` referenced by `TestEN.xml`).

The design allows adding new flows mostly by creating/updating a page or steps class, keeping test classes themselves minimal.

---

## 5\. API test coverage \- antiforgery / CSRF solution (main focus)

### 5\.1 What we needed to test

On the API side, the key focus is **validating student card (School Card) offers**:

- Fetching offers for the student card product.
- Ensuring the **backend data is correct**: titles, descriptions, partner information, paging, segments, images, etc.
- Making sure the **UI displays exactly what the API returns**, so marketing offers on the page match the backend response.

### 5\.2 The problem we hit

Initially it seemed simple:

- A request to the marketing offers endpoint worked from Postman.
- The same request (same URL, headers, and body) was implemented with RestAssured in code.
- But the RestAssured test consistently failed with **`400 Bad Request`**.

After carefully checking:

- Endpoint URL
- HTTP method
- Headers
- Request body

everything matched Postman, yet the server still rejected the call. It looked like the whole API testing part might fail.

Further investigation showed:

- The TBC site generates an **`XSRF\-TOKEN`** and stores it in **antiforgery cookies**.
- These cookies only exist when the page is loaded in a **real browser context**.
- When the RestAssured test called the endpoint directly, it had **no access** to those antiforgery cookies.
- As a result, the backend treated the request as missing/invalid CSRF protection and returned `400`.

Postman and a real browser worked because they had valid cookies and reused them correctly. The raw Java API tests had no built\-in antiforgery handling.

### 5\.3 Implemented solution: automated antiforgery cookie handling

To solve this properly, the project implements a **custom antiforgery handling mechanism** using `CookieHelper` and Playwright.

**Core idea**:  
Use a **headless browser via Playwright** to obtain real antiforgery cookies, then pass them to RestAssured so the API requests look like they come from a real browser session.

#### 5\.3\.1 `CookieHelper` flow

The `CookieHelper` class orchestrates three steps:

1. **Load the target page in headless mode (Playwright)**
    - A headless Playwright browser opens the School Card (student card) page or another relevant TBC page.
    - The server reacts as it would for a real user, generating antiforgery cookies such as `XSRF\-TOKEN`.

2. **Extract antiforgery cookies**
    - All cookies are read from the Playwright context/page.
    - Relevant antiforgery cookies are filtered out (e.g. `XSRF\-TOKEN` and related security cookies).
    - They are stored in an in\-memory structure, usually a `Map<String, String>` (HashMap) for easy reuse.

3. **Expose cookies for API tests**
    - Public methods return the cookie map in a format that RestAssured can use.
    - API tests do not need to know the Playwright details; they just receive a ready cookie map.

#### 5\.3\.2 Using cookies in RestAssured tests

In `OffersApiTest` and the API layer (`OffersApi`, `BaseApi`):

- Before hitting the marketing offers endpoint, the test calls `CookieHelper` to get antiforgery cookies.
- These cookies are attached to the RestAssured request, typically with:
    - `cookies(cookieMap)` in the request specification, or
    - Adding them individually.

This makes each API request **cookie\-aware** and consistent with what a Playwright browser sees.

As a result:

- The backend receives a valid `XSRF\-TOKEN` and related antiforgery context.
- The `400 Bad Request` responses disappear.
- The server returns a **valid JSON payload** with marketing offers.

The solution:

- Respects the real security configuration (no disabling CSRF, no hardcoding tokens).
- Is reusable for any additional endpoints protected by the same antiforgery mechanism.
- Keeps browser\-level security handling centralized in `CookieHelper` while API tests stay clean.

### 5\.4 Validating responses with Hamcrest

Once the antiforgery issue was solved and responses were available:

- The JSON was deserialized into model classes such as `MarketingOffersResponse`, `Offer`, `Partner`, `PagingDetails`, `Segment`, `SimpleImage`.
- **Hamcrest** assertions were used to validate:
    - Response structure (presence of required fields).
    - Business rules like:
        - Non\-empty list of offers.
        - Correct partners and segments.
        - Valid paging information.

This ensures:

- Backend responses are structurally correct.
- Data shown on the School Card page is consistent with what the API returns.
- Regressions in marketing offers or schema are caught early.

---

## 6\. Negative scenario: ordering a school card with invalid code

A separate piece of work focuses on the **School Card ordering flow**, especially **negative validation**.

In `src/test/java/ge/tbc/testautomation/tests/SchoolCardTest.java` (with `SchoolCardPage` and `SchoolCardSteps`):

- The test simulates a user trying to **order a School Card with an invalid code** (for example, an incorrect school or student code).

High\-level steps:

1. Use Playwright to navigate to the School Card page.
2. Start the School Card ordering process.
3. Enter deliberately invalid code data into the relevant fields.
4. Submit the form.

Assertions verify that:

- The **correct validation or error message** appears next to the field or as a general error.
- The flow **does not proceed** to any success or confirmation page.
- The page stays in an error state consistent with the product\’s business rules.

This scenario checks that:

- Incorrect codes are gracefully rejected.
- Users get clear feedback instead of silent failures or half\-completed requests.
- The overall integrity of the School Card ordering workflow is preserved.

Because this test uses the shared Playwright pages and steps, more negative or edge\-case scenarios can be added with minimal duplication.

---

## 7\. How to run the tests

From the project root:

### Run all Maven tests

```bash
bash
mvn clean test
```

### Run via TestNG suite (`TestEN.xml`)

`TestEN.xml` defines:

- A UI test suite for all classes under `ge.tbc.testautomation.tests`.
- A dedicated mobile test (`ge.tbc.testautomation.tests.mobile.AutoInstallmentIncomeMobileTest`) with `device=MOBILE` parameter.

You can run it with:

```bash
bash
mvn clean test -DsuiteXmlFile=TestEN.xml
```

Environment\-specific values (base URL, credentials, etc.) can be passed as system properties or Maven profiles. See `pom.xml`, `BaseApiConfig.java`, and the `data` package for actual property names and defaults.

---

## 8\. Extending and maintaining the framework

To add **new API tests** (including endpoints behind antiforgery):

1. Reuse the existing antiforgery handling (`CookieHelper`, `ApiSession`).
2. Add or extend client methods in `OffersApi.java` or create a new API class.
3. Create/update model classes under `marketing/offers/model` as needed.
4. Implement a TestNG test under `ge.tbc.testautomation.tests` that:
    - Obtains cookies through `CookieHelper`.
    - Calls the API method.
    - Asserts the response with Hamcrest.

To add **new Playwright UI flows**:

1. Create or extend page objects under `ge.tbc.testautomation.pages` (Playwright locators and actions).
2. Implement corresponding steps under `ge.tbc.testautomation.steps`.
3. Add a TestNG class under `ge.tbc.testautomation.tests` (or `tests.mobile` for mobile) that uses these steps.

The structure keeps:

- **Antiforgery / security logic** centralized.
- **Playwright UI logic** encapsulated in page and step layers.
- **Tests** focused on business logic and expected outcomes, making the suite easier to grow and maintain.