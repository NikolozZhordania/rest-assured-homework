package ge.tbc.testautomation.helpers.bookstore;

import ge.tbc.testautomation.data.common.StatusCodes;
import ge.tbc.testautomation.data.bookstore.Constants;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class Util {
    public static String deleteBookBody(String isbn) {
        return "{ \"isbn\": \"" + isbn + "\" }";
    }

    public static String safeToString(Object obj) {
        return obj != null ? obj.toString() : "";
    }

    public static List<Map<String, String>> fetchBooks(int limit) {
        Response response = given()
                .log().ifValidationFails()
                .when()
                .get(Constants.Endpoints.GET_BOOKS)
                .then()
                .statusCode(StatusCodes.OK)
                .extract().response();

        List<Object> allBooksRaw = response.jsonPath().getList("books");

        return allBooksRaw.stream()
                .limit(limit)
                .map(o -> {
                    Map<String, Object> bookMap = (Map<String, Object>) o;  // cast
                    Map<String, String> map = new HashMap<>();
                    map.put("isbn", safeToString(bookMap.get("isbn")));
                    map.put("author", safeToString(bookMap.get("author")));
                    return map;
                })
                .toList();
    }
}
