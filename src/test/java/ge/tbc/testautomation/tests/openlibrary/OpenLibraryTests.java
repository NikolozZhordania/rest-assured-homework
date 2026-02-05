package ge.tbc.testautomation.tests.openlibrary;

import ge.tbc.testautomation.basetest.openlibrary.BaseTest;
import ge.tbc.testautomation.data.common.StatusCodes;
import org.testng.annotations.Test;

import static ge.tbc.testautomation.data.openlibrary.Constants.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;

public class OpenLibraryTests extends BaseTest {

    @Test
    public void openLibrarySearch() {
        given()
                .queryParam(QueryParams.SEARCH, Titles.NOVEL_TITLE)
                .when()
                .get(Endpoints.GET_SEARCH)
                .then()
                .log().ifValidationFails()
                .statusCode(StatusCodes.OK)
                .body("docs.size()", greaterThan(0))
                .body("docs[0].title", equalTo(Titles.BOOK_TITLE))
                .body("docs[0].author_name[0]", equalTo(Titles.AUTHOR_NAME));
    }
}
