package ge.tbc.testautomation.api.marketing.offers.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data @NoArgsConstructor @AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class SimpleImage {
    @JsonProperty("$id")  private String id;
    @JsonProperty("alt")  private String alt;
    @JsonProperty("desc") private String desc;
    @JsonProperty("src")  private String src;
}
