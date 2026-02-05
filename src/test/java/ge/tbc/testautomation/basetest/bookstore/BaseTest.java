package ge.tbc.testautomation.basetest.bookstore;

import ge.tbc.testautomation.data.bookstore.Constants.*;
import ge.tbc.testautomation.helpers.bookstore.Util;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;

import java.util.List;
import java.util.Map;

public class BaseTest {

    public static List<Map<String, String>> books;

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = URI.BASE_URI;
        RestAssured.basePath = Paths.BASE_PATH;
    }

    @BeforeClass(dependsOnMethods = "setup")
    public void loadBooks() {
        books = Util.fetchBooks(Limits.MAX_BOOKS);

        if (books.size() < Limits.MAX_BOOKS) {
            throw new IllegalStateException(
                    "Expected at least " + Limits.MAX_BOOKS +
                            " books, but found " + books.size()
            );
        }
    }
}

