package ge.tbc.testautomation.api.marketing.offers.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Data @NoArgsConstructor @AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Partner {
    @JsonProperty("$id")       private String id;
    @JsonProperty("description") private String description;
    @JsonProperty("title")     private String title;
    @JsonProperty("slug")      private String slug;
    @JsonProperty("image")     private SimpleImage image;
    @JsonProperty("logo")      private SimpleImage logo;
    @JsonProperty("segments")  private List<Segment> segments;
}
