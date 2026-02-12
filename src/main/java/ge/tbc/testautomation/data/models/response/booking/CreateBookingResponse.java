package ge.tbc.testautomation.data.models.response.booking;

import com.fasterxml.jackson.annotation.JsonProperty;
import ge.tbc.testautomation.data.models.request.booking.BookingRequest;
import lombok.Data;

@Data
public class CreateBookingResponse {

    @JsonProperty("bookingid")
    private int bookingId;

    @JsonProperty("booking")
    private BookingRequest booking;
}
