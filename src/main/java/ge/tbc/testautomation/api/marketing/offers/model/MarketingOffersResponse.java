package ge.tbc.testautomation.api.marketing.offers.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@Data @NoArgsConstructor @AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class MarketingOffersResponse {
    @JsonProperty("pagingDetails")
    private PagingDetails pagingDetails;

    @JsonProperty("list")
    private List<Offer> list;
}
