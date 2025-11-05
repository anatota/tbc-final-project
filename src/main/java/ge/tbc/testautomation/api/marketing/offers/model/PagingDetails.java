package ge.tbc.testautomation.api.marketing.offers.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data @NoArgsConstructor @AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PagingDetails {
    @JsonProperty("pageIndex")       private int pageIndex;
    @JsonProperty("pageNumber")      private int pageNumber;
    @JsonProperty("pageSize")        private int pageSize;
    @JsonProperty("totalCount")      private int totalCount;
    @JsonProperty("totalPages")      private int totalPages;
    @JsonProperty("hasPreviousPage") private boolean hasPreviousPage;
    @JsonProperty("hasNextPage")     private boolean hasNextPage;
    @JsonProperty("firstItemIndex")  private int firstItemIndex;
    @JsonProperty("lastItemIndex")   private int lastItemIndex;
    @JsonProperty("isFirstPage")     private boolean firstPage;
    @JsonProperty("isLastPage")      private boolean lastPage;
}
