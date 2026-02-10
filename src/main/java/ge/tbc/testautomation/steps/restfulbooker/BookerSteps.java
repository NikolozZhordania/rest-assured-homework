package ge.tbc.testautomation.steps.restfulbooker;

import com.fasterxml.jackson.databind.ObjectMapper;
import ge.tbc.testautomation.api.client.restfulbooker.BookerAuthApi;
import ge.tbc.testautomation.api.client.restfulbooker.BookerBookingApi;
import ge.tbc.testautomation.data.constants.restfulbooker.Constants.BookingData;
import ge.tbc.testautomation.data.models.request.restfulbooker.BookingDates;
import ge.tbc.testautomation.data.models.request.restfulbooker.BookingRequest;
import ge.tbc.testautomation.data.models.request.restfulbooker.PartialBookingRequest;
import ge.tbc.testautomation.data.models.response.restfulbooker.BookingGetResponse;
import ge.tbc.testautomation.data.models.response.restfulbooker.BookingResponse;
import ge.tbc.testautomation.data.models.response.restfulbooker.TokenResponse;
import io.restassured.response.Response;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class BookerSteps {

    private final BookerAuthApi authApi = new BookerAuthApi();
    private final BookerBookingApi bookingApi = new BookerBookingApi();
    private final ObjectMapper mapper = new ObjectMapper();

    private String token;
    private BookingResponse bookingResponse;
    private BookingGetResponse bookingGetResponse;

    public BookerSteps requestToken() {
        Response resp = authApi.createToken()
                .then()
                .statusCode(200)
                .extract()
                .response();

        this.token = resp.as(TokenResponse.class).getToken();
        System.out.println("Token: " + token);
        return this;
    }

    public BookerSteps createBooking() throws Exception {
        BookingDates dates = new BookingDates(BookingData.CHECK_IN, BookingData.CHECK_OUT);
        BookingRequest req = new BookingRequest(
                BookingData.FIRST_NAME,
                BookingData.LAST_NAME,
                BookingData.TOTAL_PRICE,
                BookingData.DEPOSIT_PAID,
                dates,
                BookingData.ADDITIONAL_NEEDS
        );

        System.out.println("Booking Request: " + mapper.writeValueAsString(req));

        Response resp = bookingApi.createBooking(req);

        if (resp.statusCode() != 200) {
            System.out.println("Booking creation failed! Status: " + resp.statusCode());
            System.out.println("Response body: " + resp.asString());
            return this;
        }

        this.bookingResponse = resp.as(BookingResponse.class);
        System.out.println("Booking created with ID: " + bookingResponse.getBookingId());
        return this;
    }

    public BookerSteps verifyBooking() {
        if (bookingResponse == null) return this;

        this.bookingGetResponse = bookingApi.getBooking(bookingResponse.getBookingId())
                .then()
                .statusCode(200)
                .extract()
                .as(BookingGetResponse.class);

        assertThat(bookingGetResponse.getFirstName(), equalTo(BookingData.FIRST_NAME));
        assertThat(bookingGetResponse.getLastName(), equalTo(BookingData.LAST_NAME));
        assertThat(bookingGetResponse.getTotalPrice(), equalTo(BookingData.TOTAL_PRICE));
        assertThat(bookingGetResponse.getDepositPaid(), is(BookingData.DEPOSIT_PAID));
        assertThat(bookingGetResponse.getBookingDates().getCheckIn(), equalTo(BookingData.CHECK_IN));
        assertThat(bookingGetResponse.getBookingDates().getCheckOut(), equalTo(BookingData.CHECK_OUT));
        assertThat(bookingGetResponse.getAdditionalNeeds(), equalTo(BookingData.ADDITIONAL_NEEDS));

        return this;
    }

    public BookerSteps partialUpdateBooking() {
        if (bookingResponse == null) return this;

        PartialBookingRequest update = new PartialBookingRequest();
        update.setFirstName(BookingData.NEW_FIRST_NAME);

        bookingApi.partialUpdate(bookingResponse.getBookingId(), token, update)
                .then()
                .statusCode(200);

        this.bookingGetResponse = bookingApi.getBooking(bookingResponse.getBookingId())
                .then()
                .statusCode(200)
                .extract()
                .as(BookingGetResponse.class);

        assertThat(bookingGetResponse.getFirstName(), equalTo(BookingData.NEW_FIRST_NAME));
        return this;
    }

    public BookerSteps deleteBooking() {
        if (bookingResponse == null) return this;

        bookingApi.deleteBooking(bookingResponse.getBookingId(), token)
                .then()
                .statusCode(201);

        bookingApi.getBooking(bookingResponse.getBookingId())
                .then()
                .statusCode(404);

        return this;
    }
}
