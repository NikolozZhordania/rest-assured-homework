package ge.tbc.testautomation.steps.booking;

import ge.tbc.testautomation.api.client.booking.BookingApi;
import ge.tbc.testautomation.data.models.request.booking.AuthRequest;
import ge.tbc.testautomation.data.models.request.booking.BookingRequest;
import ge.tbc.testautomation.data.models.response.booking.BookingResponse;
import ge.tbc.testautomation.data.models.response.booking.AuthResponse;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import lombok.Getter;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class BookingSteps {

    private final BookingApi api;
    private Response lastResponse;
    @Getter
    private String token;

    public BookingSteps() {
        api = new BookingApi();
    }

    @Step("Authenticate and save token")
    public BookingSteps authenticate(AuthRequest authRequest) {
        lastResponse = api.createToken(authRequest);
        token = lastResponse.as(AuthResponse.class).getToken();
        assertThat("Token should not be null", token, notNullValue());
        return this;
    }

    @Step("Create booking")
    public int createBooking(BookingRequest request) {
        lastResponse = api.createBooking(request);
        int bookingId = lastResponse.jsonPath().getInt("bookingid");
        assertThat(bookingId, greaterThan(0));
        return bookingId;
    }

    @Step("Update booking ID {bookingId}")
    public BookingSteps updateBooking(int bookingId, BookingRequest request) {
        lastResponse = api.updateBooking(bookingId, request, token);
        return this;
    }

    @Step("Validate status code is {statusCode}")
    public BookingSteps validateStatusCode(int statusCode) {
        assertThat(lastResponse.statusCode(), is(statusCode));
        return this;
    }

    @Step("Validate booking fields match request")
    public BookingSteps validateBookingFields(int bookingId, BookingRequest request) {
        BookingResponse response = api.getBooking(bookingId).as(BookingResponse.class);

        assertThat(response.getFirstName(), is(request.getFirstName()));
        assertThat(response.getLastName(), is(request.getLastName()));
        assertThat(response.getTotalPrice(), is(request.getTotalPrice()));
        assertThat(response.getDepositPaid(), is(request.isDepositPaid()));
        assertThat(response.getAdditionalNeeds(), is(request.getAdditionalNeeds()));
        assertThat(response.getBookingDates().getCheckIn(),
                is(request.getBookingDates().getCheckIn()));
        assertThat(response.getBookingDates().getCheckOut(),
                is(request.getBookingDates().getCheckOut()));

        return this;
    }

}
