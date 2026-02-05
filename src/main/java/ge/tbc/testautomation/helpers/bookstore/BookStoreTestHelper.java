package ge.tbc.testautomation.helpers.bookstore;

import ge.tbc.testautomation.data.common.StatusCodes;
import ge.tbc.testautomation.data.bookstore.Constants;

import io.restassured.response.ValidatableResponse;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class BookStoreTestHelper {

    public static ValidatableResponse validateBook(Map<String, String> book) {
        return given()
                .queryParam(Constants.QueryParams.ISBN, book.get("isbn"))
                .when()
                .get(Constants.Endpoints.GET_BOOK)
                .then()
                .statusCode(StatusCodes.OK)
                .body("isbn", equalTo(book.get("isbn")))
                .body("author", equalTo(book.get("author")))
                .body("title", notNullValue())
                .body("publish_date", notNullValue())
                .body("pages", greaterThan(0));
    }
}
