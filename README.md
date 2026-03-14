# TBC Bank Test Automation Final Project

Automation and QA project for TBC Bank card-related user journeys across web UI and API layers.

This repository combines Playwright-based UI testing, RestAssured API validation, and a small shared framework built with Java, Maven, and TestNG. The strongest part of my contribution was the **School Card feature testing**, including a negative E2E flow, manual validation of the positive flow, and API testing that required solving a real **CSRF / antiforgery token** issue.

## Why This Project Matters

The project focuses on realistic banking flows where correctness, validation, and secure request handling matter. It covers:

- UI test automation for TBC card journeys
- API validation for marketing offers
- Shared helpers, page objects, and step classes for maintainability
- Security-aware request handling for endpoints protected by antiforgery logic

## My Contribution

My work in this project centered on the **School Card** feature.

### 1. Automated E2E negative scenario

I worked on the automated negative School Card flow that verifies invalid code handling in the UI. The scenario checks that the application:

- rejects an invalid code
- keeps the user on the current step
- highlights the field with an error state
- shows the correct validation message

Relevant implementation:

- [SchoolCardTest.java](src/test/java/ge/tbc/testautomation/tests/SchoolCardTest.java)
- [SchoolCardSteps.java](src/main/java/ge/tbc/testautomation/steps/SchoolCardSteps.java)
- [SchoolCardPage.java](src/main/java/ge/tbc/testautomation/pages/SchoolCardPage.java)

### 2. Manual testing of the positive case

I also tested the positive School Card flow manually and found a meaningful validation defect:

- the website accepted **any image upload** instead of validating that the uploaded file was a required document

This was useful because it exposed a business-rule gap that automation alone would not necessarily reveal early.

### 3. API testing and CSRF issue resolution

My primary API contribution was solving the antiforgery token problem that caused protected requests to fail.

The issue:

- API requests worked in browser-like environments but failed in automation with `400 Bad Request`
- the endpoint required valid frontend cookies and `XSRF-TOKEN`

What I implemented:

- fetched frontend cookies through a Playwright browser session
- extracted the `XSRF-TOKEN`
- passed cookies and the `x-xsrf-token` header into RestAssured requests

Relevant implementation:

- [OffersApiTest.java](src/test/java/ge/tbc/testautomation/tests/OffersApiTest.java)
- [OffersApiSteps.java](src/main/java/ge/tbc/testautomation/steps/OffersApiSteps.java)
- [OffersApi.java](src/main/java/ge/tbc/testautomation/api/OffersApi.java)
- [CookieHelper.java](src/main/java/ge/tbc/testautomation/helper/CookieHelper.java)
- [ApiSession.java](src/main/java/ge/tbc/testautomation/helper/ApiSession.java)

## Tech Stack

- Java
- Maven
- TestNG
- Playwright
- RestAssured
- Hamcrest

## Project Structure

```text
src/
  main/java/ge/tbc/testautomation/
    api/        API client layer
    config/     shared API configuration
    data/       constants and test data
    helper/     cookie/session utilities
    pages/      Playwright page objects
    steps/      reusable business-flow steps

  test/java/ge/tbc/testautomation/tests/
    UI tests
    API tests
    mobile tests
```

## Test Coverage Overview

The repository includes tests for several TBC card-related flows, including:

- School Card
- TBC Card
- Credit Card
- Auto Installment
- Youth-related offers API
- Mobile-emulated scenarios

From a portfolio perspective, the most important parts of my contribution are:

- negative E2E validation for School Card
- manual bug discovery in the positive School Card flow
- API security handling for CSRF-protected requests

## How to Run

Run the full test suite:

```bash
mvn clean test
```

Run using the TestNG suite file:

```bash
mvn clean test -DsuiteXmlFile=TestEN.xml
```

## Summary

This project demonstrates that I can contribute across multiple QA layers:

- write readable automated UI tests
- validate negative scenarios, not only happy paths
- perform manual testing that uncovers real product defects
- debug and fix API automation issues related to web security mechanisms
- work within a maintainable test framework structure
