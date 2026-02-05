package ge.tbc.testautomation.tests.bookstore;

import ge.tbc.testautomation.basetest.bookstore.BaseTest;
import ge.tbc.testautomation.data.common.StatusCodes;
import ge.tbc.testautomation.data.bookstore.BookDataProvider;
import ge.tbc.testautomation.helpers.bookstore.BookStoreTestHelper;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import java.util.Map;


import static ge.tbc.testautomation.data.bookstore.Constants.*;
import static ge.tbc.testautomation.helpers.bookstore.Util.deleteBookBody;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class BookStoreTests extends BaseTest {

    @Test
    public void getFirstBookByIsbn() {
        Map<String, String> book = books.get(0);
        BookStoreTestHelper.validateBook(book);
    }

    @Test(dataProvider = "bookData", dataProviderClass = BookDataProvider.class)
    public void getBookWithDataProvider(Map<String, String> book) {
        BookStoreTestHelper.validateBook(book);
    }

    @Test
    public void deleteBookUnauthorized() {

        given()
                .contentType(ContentType.JSON)
                .body(deleteBookBody(books.get(0).get("isbn")))
                .when()
                .delete(Endpoints.DELETE_BOOK)
                .then()
                .statusCode(StatusCodes.UNAUTHORIZED)
                .body("message", equalTo(Messages.NOT_AUTHORIZED_MESSAGE));
    }
}

