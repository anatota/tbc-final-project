package ge.tbc.testautomation.api.marketing.offers.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data @NoArgsConstructor @AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Offer {
    @JsonProperty("$id")        private String id;
    @JsonProperty("description")private String description;
    @JsonProperty("title")      private String title;
    @JsonProperty("badgeValue") private String badgeValue;
    @JsonProperty("badgeType")  private String badgeType;
    @JsonProperty("slug")       private String slug;

    @JsonProperty("startDate")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss[.SSS][XXX]")
    private LocalDateTime startDate;

    @JsonProperty("endDate")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss[.SSS][XXX]")
    private LocalDateTime endDate;

    @JsonProperty("image")     private SimpleImage image;
    @JsonProperty("segments")  private List<Segment> segments;
    @JsonProperty("partner")   private Partner partner;
}
