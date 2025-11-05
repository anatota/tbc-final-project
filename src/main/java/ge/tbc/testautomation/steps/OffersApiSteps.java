package ge.tbc.testautomation.steps;

import ge.tbc.testautomation.api.OffersApi;
import ge.tbc.testautomation.api.marketing.offers.model.MarketingOffersResponse;
import ge.tbc.testautomation.api.marketing.offers.model.SearchOffersRequest;

import java.util.List;

import static org.testng.Assert.*;

public class OffersApiSteps {
    private final OffersApi offersApi = new OffersApi();

    public MarketingOffersResponse getYouthOffers() {
        SearchOffersRequest req = SearchOffersRequest.builder()
                .filter(List.of("ProductType:Pupil'sCard"))
                .locale("ka-GE")
                .segment("ForYouth")
                .pageIndex(0)
                .pageSize(7)
                .build();

        return offersApi.postOffers(req);
    }

    public void validateOfferData(MarketingOffersResponse response) {
        assertNotNull(response.getList());
        assertTrue(response.getList().size() <= 7);
    }
}
