package ge.tbc.testautomation.api.marketing.offers.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class SearchOffersRequest {
    @JsonProperty("filter")    private List<String> filter;   // e.g. ["ProductType:Pupil'sCard"]
    @JsonProperty("locale")    private String locale;         // "ka-GE"
    @JsonProperty("segment")   private String segment;        // "ForYouth"
    @JsonProperty("pageIndex") private int pageIndex;         // 0
    @JsonProperty("pageSize")  private int pageSize;          // 7
}
