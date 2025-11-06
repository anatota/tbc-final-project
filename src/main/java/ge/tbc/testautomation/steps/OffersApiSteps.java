package ge.tbc.testautomation.steps;
import ge.tbc.testautomation.api.OffersApi;
import ge.tbc.testautomation.api.marketing.offers.model.MarketingOffersResponse;
import ge.tbc.testautomation.api.marketing.offers.model.SearchOffersRequest;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import java.util.List;
import static org.hamcrest.Matchers.notNullValue;
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
        var p = response.getPagingDetails();
        var list = response.getList();

        assertThat(response, notNullValue());
        assertThat(response.getPagingDetails(), notNullValue());
        assertThat(list, notNullValue());
        assertThat(list.size(), lessThanOrEqualTo(7));

        assertThat(p.getPageSize(), is(7));
        assertThat(p.getPageIndex(), is(0));
        assertThat(p.getPageNumber(), is(p.getPageIndex() + 1));
        assertThat(p.getTotalCount(), greaterThanOrEqualTo(list.size()));

        assertThat(list, everyItem(allOf(
                hasProperty("id", notNullValue()),
                hasProperty("title", notNullValue()),
                hasProperty("slug", notNullValue())
        )));

        assertThat(list, everyItem(hasProperty("image", anyOf(nullValue(),
                hasProperty("src", allOf(notNullValue(), not(emptyString())))
        ))));

        list.stream()
                .filter(o -> o.getStartDate() != null && o.getEndDate() != null)
                .forEach(o -> assertThat(!o.getEndDate().isBefore(o.getStartDate()), is(true)));

    }
}
