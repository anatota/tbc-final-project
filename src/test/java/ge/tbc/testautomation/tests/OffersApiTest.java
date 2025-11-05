package ge.tbc.testautomation.tests;

import ge.tbc.testautomation.api.marketing.offers.model.MarketingOffersResponse;
import ge.tbc.testautomation.steps.OffersApiSteps;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class OffersApiTest {

    private OffersApiSteps steps;

    @BeforeClass
    public void setUp() {
        steps = new OffersApiSteps();
    }

    @Test(description = "Automatically fetches antiforgery cookies and verifies youth offers")
    public void verifyYouthOffers() {
        MarketingOffersResponse response = steps.getYouthOffers();
        steps.validateOfferData(response);
    }
}
