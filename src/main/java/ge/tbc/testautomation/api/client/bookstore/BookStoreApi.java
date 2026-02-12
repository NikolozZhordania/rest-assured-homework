package ge.tbc.testautomation.api.client.bookstore;

import ge.tbc.testautomation.data.constants.bookstore.Constants.*;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class BookStoreApi {
    public Response getBooks() {
        return given()
                .baseUri(URI.BASE)
                .get(Paths.BASE + Endpoints.BOOKS);
    }
}
