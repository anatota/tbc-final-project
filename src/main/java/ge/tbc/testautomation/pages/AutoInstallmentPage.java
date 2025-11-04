package ge.tbc.testautomation.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class AutoInstallmentPage {
    public Locator autoInstallment,
            autoInstallmentTitle,
            incomeCalculatorBtn,
            incomeInputField,
            loanPeriodInputField,
            takeLoanBtn,
            installmentAmountField,
            monthlyContributionField,
            monthDividers,
            titleLabel,
            interestRateField,
            effectiveInterestRateField,
            loanForm;

    public AutoInstallmentPage(Page page) {
        autoInstallment = page.locator("a[href='/ka/loans/auto-loan/auto-installment'] button");
        autoInstallmentTitle = page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("ავტოგანვადება").setExact(true));
        incomeCalculatorBtn = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("შემოსავლით"));
        incomeInputField = page.getByRole(AriaRole.SPINBUTTON, new Page.GetByRoleOptions().setName("შემოსავალი *"));
        loanPeriodInputField = page.locator("#tbcx-text-input-4");
        takeLoanBtn = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("arrow-right-outlined აიღე სესხი"));
        installmentAmountField = page.locator(".tbcx-pw-calculated-info__top-title");
        monthlyContributionField = page.locator(".tbcx-pw-calculated-info__rows-item-info.ng-star-inserted");
        monthDividers = page.locator(".slider-dividers");
        titleLabel = page.locator(".tbcx-pw-calculated-info__alert");
        interestRateField = getSiblingInfoByLabelText("საპროცენტო განაკვეთი");
        effectiveInterestRateField = getSiblingInfoByLabelText("ეფექტური პროცენტი");
        loanForm = page.locator("#cdk-dialog-serverApp0");
    }

    public Locator getSiblingInfoByLabelText(String text) {
        Locator label = titleLabel.getByText(text);
        return label.locator("xpath=following-sibling::*[contains(@class, 'tbcx-pw-calculated-info__rows-item-info')]").first();
    }
}
