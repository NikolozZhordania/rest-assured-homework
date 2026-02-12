package ge.tbc.testautomation.data.models.response.booking;

import com.fasterxml.jackson.annotation.JsonProperty;
import ge.tbc.testautomation.data.models.request.booking.BookingRequest.*;
import lombok.Data;

@Data
public class BookingResponse {

    @JsonProperty("firstname")
    private String firstName;

    @JsonProperty("lastname")
    private String lastName;

    @JsonProperty("totalprice")
    private Integer totalPrice;

    @JsonProperty("depositpaid")
    private Boolean depositPaid;

    @JsonProperty("bookingdates")
    private BookingDates bookingDates;

    @JsonProperty("additionalneeds")
    private String additionalNeeds;
}

