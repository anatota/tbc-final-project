package ge.tbc.testautomation.api.marketing.offers.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data @NoArgsConstructor @AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Segment {
    @JsonProperty("$id")     private String id;
    @JsonProperty("label")   private String label;
    @JsonProperty("isHidden")private boolean isHidden;
}
