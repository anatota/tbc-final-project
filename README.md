# TBC Test Automation Final Project

This repository contains an end-to-end automated test suite for TBC Bank card products (Web UI + API).
The suite verifies that major customer journeys function correctly and that backend data is accurately reflected on the UI.
A key part of the project involved **secure API testing that respects TBC’s antiforgery / CSRF protection**.

---

# Table of Contents

1. Overview
2. My Role in This Project
3. Tech Stack
4. Project Structure
5. UI Test Coverage
6. API Test Coverage (Antiforgery / CSRF-Aware Testing)
7. Negative Scenario: Invalid School Card Code
8. How to Run the Tests
9. Extending and Maintaining the Framework

---

# 1. Overview

The automated suite covers:

* **Web UI flows** for TBC card products:
  TBC Card, School Card, Credit Card, Auto Installment, Youth Wizard, OTP validation.
* **API tests** for marketing offers, including antiforgery / CSRF cookie handling.
* Shared test infrastructure for:

    * configuration
    * Playwright sessions
    * API clients
    * page objects and steps

The goal is to create stable regression tests that behave like real users (browser + cookies + security mechanisms).

---

# 2. My Role in This Project

My main responsibilities focused on the **API automation**, **security-aware testing**, and **framework design**.
Specifically, I contributed to:

* **API automation setup**
  Implemented RestAssured-based API client structure (`BaseApi`, `OffersApi`) and created the POJOs for marketing offer responses.

* **Antiforgery / CSRF-aware API testing**
  Investigated recurring `400 Bad Request` failures and implemented the automated antiforgery cookie extraction flow using Playwright (`CookieHelper`, `ApiSession`).

* **UI and Steps Layer**
  Contributed to Playwright page objects and steps for School Card flows, validations, and negative scenarios.

* **Mobile-emulated Playwright Tests**
  Assisted with the configuration used in `TestEN.xml`.

* **Project architecture & maintainability**
  Helped shape the package structure, centralized utilities, and ensured clean separation between pages, steps, and tests.

Overall, I focused on making the framework **secure**, **reliable**, and **extensible**, while ensuring both UI and API layers worked together consistently.

---

# 3. Tech Stack

* **Language:** Java (framework + tests), JavaScript (performance scripts)
* **Build tool:** Maven
* **Test framework:** TestNG (`TestEN.xml`)
* **UI testing:** Playwright (desktop + mobile emulation)
* **API testing:** RestAssured
* **Assertions:** Hamcrest
* **Reporting:** Allure-compatible screenshot listener + TestNG reports
* **Utilities:** Custom helpers for cookies, sessions, config

---

# 4. Project Structure

### API Layer – `src/main/java/ge/tbc/testautomation/api`

* `BaseApi.java`, `OffersApi.java`
* Model classes (`marketing/offers/model`):
  `MarketingOffersResponse`, `Offer`, `PagingDetails`, `Partner`, `Segment`, `SimpleImage`, `SearchOffersRequest`

### Configuration – `config`

* `BaseApiConfig.java` for URLs and API defaults

### Data – `data`

* `ApiConstants.java`, `Constants.java` for endpoints and paths

### Helper Layer – `helper`

* `ApiSession.java`, `CookieHelper.java`, `Helper.java`
  (session handling + antiforgery cookie logic)

### UI Layer – `pages` and `steps`

* Playwright page objects
* Step classes: `TbcCardSteps`, `SchoolCardSteps`, `CreditCardSteps`, `AutoInstallmentSteps`, etc.

### Test Layer – `src/test/java`

* UI tests
* Mobile tests (`tests.mobile`)
* API tests (`OffersApiTest.java`)
* `BaseTest.java` for environment setup
* `allurescreenshotlistener.java` for reporting

### Root-level

* `TestEN.xml` (suite)
* `tbcCardPerformanceTest.js` (performance script)

---

# 5. UI Test Coverage

The UI layer follows a **Page Object Model (POM)** plus **Steps** abstraction:

* Pages define locators and low-level actions
* Steps combine actions into business flows
* Tests call steps to keep test logic short and readable

Covered flows include:

* Requesting all major TBC cards
* Filling customer and income data
* Validation messages and UI correctness
* Mobile-mode tests executed through Playwright emulation

This structure supports quick addition of new flows with minimal duplication.

---

# 6. API Test Coverage – Antiforgery / CSRF-Aware Testing

### 6.1 What we validate

* Marketing offers for the School Card product
* Correct backend structure and business rules
* Full consistency between the UI and returned API data

### 6.2 Challenge: `400 Bad Request`

Postman + real browsers worked, but RestAssured failed due to:

* missing antiforgery cookies
* server-side CSRF validation rejecting requests
* cookies generated only inside an actual browser session

### 6.3 Implemented Solution: Automated Cookie Handling

To replicate real browser behavior:

1. **Headless Playwright session** loads a TBC page
2. Antiforgery cookies (e.g., `XSRF-TOKEN`) are extracted
3. Cookies are passed into RestAssured
4. API requests succeed with valid security context

### 6.4 Response Validation

Using Hamcrest and model deserialization, tests verify:

* structure
* non-empty offer lists
* correct partners/segments
* valid paging
* correct UI consistency

---

# 7. Negative Scenario: Invalid School Card Code

A dedicated UI flow validates that submitting invalid school/student codes:

* triggers correct error messages
* prevents navigation to confirmation pages
* preserves business logic integrity

This scenario is implemented with page objects, steps, and TestNG.

---

# 8. How to Run the Tests

Run everything:

```bash
mvn clean test
```

Run with TestNG suite:

```bash
mvn clean test -DsuiteXmlFile=TestEN.xml
```

Environment values (base URLs, browser modes, etc.) can be set via Maven profiles or system properties.

---

# 9. Extending and Maintaining the Framework

### Add new API tests:

1. Reuse `CookieHelper` & `ApiSession`
2. Extend API classes or add new ones
3. Add/modify model classes
4. Write TestNG tests using the shared cookie-handling logic

### Add new UI tests:

1. Create/extend page objects
2. Add steps for new flows
3. Add TestNG test classes for UI or mobile tests

The architecture keeps:

* security logic centralized
* UI logic clean
* tests maintainable and readable
