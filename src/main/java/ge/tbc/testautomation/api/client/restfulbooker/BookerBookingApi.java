package ge.tbc.testautomation.api.client.restfulbooker;

import ge.tbc.testautomation.data.constants.restfulbooker.Constants.*;
import ge.tbc.testautomation.data.models.request.restfulbooker.BookingRequest;
import ge.tbc.testautomation.data.models.request.restfulbooker.PartialBookingRequest;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class BookerBookingApi {

    public Response createBooking(BookingRequest req) {
        return given()
                .baseUri(URI.BASE)
                .body(req)
                .post(Endpoints.BOOKING);
    }

    public Response getBooking(int id) {
        return given()
                .baseUri(URI.BASE)
                .get(Endpoints.BOOKING + "/" + id);
    }

    public Response partialUpdate(int id, String token, PartialBookingRequest req) {
        return given()
                .baseUri(URI.BASE)
                .header(Headers.COOKIE, Headers.TOKEN + token)
                .body(req)
                .patch(Endpoints.BOOKING + "/" + id);
    }

    public Response deleteBooking(int id, String token) {
        return given()
                .baseUri(URI.BASE)
                .header(Headers.COOKIE, Headers.TOKEN + token)
                .delete(Endpoints.BOOKING + "/" + id);
    }
}
