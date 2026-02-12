package ge.tbc.testautomation.data.models.response.bookstore;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class BookstoreOrderResponse {

    @JsonProperty("id")
    private int id;

    @JsonProperty("petId")
    private int petId;

    @JsonProperty("status")
    private String status;

    @JsonProperty("complete")
    private boolean complete;
}
