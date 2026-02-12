package ge.tbc.testautomation.api.client.booking;

import ge.tbc.testautomation.data.models.request.booking.AuthRequest;
import ge.tbc.testautomation.data.models.request.booking.BookingRequest;
import io.restassured.response.Response;

public class BookingApi extends BaseApi {

    public Response createToken(AuthRequest authRequest) {
        return spec()
                .body(authRequest)
                .post("/auth");
    }

    public Response createBooking(BookingRequest bookingRequest) {
        return spec()
                .body(bookingRequest)
                .post("/booking");
    }

    public Response updateBooking(int bookingId, BookingRequest bookingRequest, String token) {
        return spec()
                .cookie("token", token)
                .body(bookingRequest)
                .put("/booking/" + bookingId);
    }

    public Response getBooking(int bookingId) {
        return spec()
                .get("/booking/" + bookingId);
    }
}
