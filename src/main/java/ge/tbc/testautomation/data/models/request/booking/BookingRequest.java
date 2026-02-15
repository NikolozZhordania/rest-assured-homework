package ge.tbc.testautomation.data.models.request.booking;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookingRequest {

    @JsonProperty("firstname")
    private String firstName;

    @JsonProperty("lastname")
    private String lastName;

    @JsonProperty("totalprice")
    private int totalPrice;

    @JsonProperty("depositpaid")
    private boolean depositPaid;

    @JsonProperty("bookingdates")
    private BookingDates bookingDates;

    @JsonProperty("additionalneeds")
    private String additionalNeeds;

    @JsonIgnore
    private Double salePrice;

    @JsonIgnore
    private String passportNo;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class BookingDates {

        @JsonProperty("checkin")
        private String checkIn;

        @JsonProperty("checkout")
        private String checkOut;
    }
}
