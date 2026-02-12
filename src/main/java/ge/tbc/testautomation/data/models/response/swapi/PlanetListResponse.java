package ge.tbc.testautomation.data.models.response.swapi;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PlanetListResponse {

    @JsonProperty("results")
    private List<PlanetListItem> results;

    @JsonProperty("total_records")
    private int totalRecords;

    @JsonProperty("total_pages")
    private int totalPages;

    @JsonProperty("next")
    private String next;

    @JsonProperty("previous")
    private String previous;
}

