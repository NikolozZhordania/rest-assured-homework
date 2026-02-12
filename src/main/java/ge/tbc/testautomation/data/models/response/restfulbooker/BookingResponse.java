package ge.tbc.testautomation.data.models.response.restfulbooker;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookingResponse {
    @JsonProperty("bookingid")
    private Integer bookingId;

    @JsonProperty("booking")
    private BookingGetResponse booking;
}
