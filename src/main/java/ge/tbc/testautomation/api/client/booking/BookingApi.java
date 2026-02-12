package ge.tbc.testautomation.api.client.booking;

import ge.tbc.testautomation.data.models.request.booking.AuthRequest;
import ge.tbc.testautomation.data.models.request.booking.BookingRequest;
import io.restassured.response.Response;

import static ge.tbc.testautomation.data.constants.booking.Constants.*;

public class BookingApi extends BaseApi {

    public Response createToken(AuthRequest authRequest) {
        return spec()
                .body(authRequest)
                .post(AUTH);
    }

    // Create booking
    public Response createBooking(BookingRequest bookingRequest) {
        return spec()
                .body(bookingRequest)
                .post(CREATE_BOOKING);
    }

    // Update booking
    public Response updateBooking(int bookingId, BookingRequest bookingRequest, String token) {
        return spec()
                .cookie("token", token)
                .body(bookingRequest)
                .put(String.format(UPDATE_BOOKING, bookingId));
    }

    // Get booking
    public Response getBooking(int bookingId) {
        return spec()
                .get(String.format(GET_BOOKING, bookingId));
    }
}
