package ge.tbc.testautomation.tests.bookstore;

import ge.tbc.testautomation.basetest.bookstore.BaseTest;
import ge.tbc.testautomation.data.bookstore.Constants.*;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

import static org.hamcrest.Matchers.*;

public class BookStoreTests extends BaseTest {

    @Test
    public void validateBookPagesAndAuthors() {
        given()
                .spec(requestSpec)
                .when()
                .get(Endpoints.GET_BOOKS)
                .then()
                .spec(responseSpec)
                .body("books.pages", everyItem(lessThan(1000)))
                .body("books[0].author", equalTo(AuthorNames.FIRST_BOOK_AUTHOR))
                .body("books[1].author", equalTo(AuthorNames.SECOND_BOOK_AUTHOR));
    }
}