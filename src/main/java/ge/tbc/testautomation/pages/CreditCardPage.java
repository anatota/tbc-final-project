package ge.tbc.testautomation.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import ge.tbc.testautomation.steps.CreditCardSteps;

public class CreditCardPage {
    public Locator creditCardComparisonContainer,
            tableRows,
            requestACallBtn,
            formContainer,
            fullNameNameInput,
            idInput,
            phoneNumberInput;

    public CreditCardPage(Page page) {
        creditCardComparisonContainer = page.locator("//div[@role='treegrid']");
        tableRows = page.locator("//div[@role='row']");
        requestACallBtn = page.locator("//button[text()='ზარის მოთხოვნა']");
        formContainer = page.locator("//ng-component[@role='dialog']");
        fullNameNameInput = page.locator("//input[@placeholder='სახელი " +
                "გვარი']");
        idInput = page.locator("//input[@placeholder='პირადი ნომერი']");
        phoneNumberInput = page.locator("//input[@placeholder='ტელეფონის ნომერი']");

    }
}
