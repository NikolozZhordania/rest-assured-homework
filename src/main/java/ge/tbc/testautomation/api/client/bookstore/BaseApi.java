package ge.tbc.testautomation.api.client.bookstore;

import ge.tbc.testautomation.data.constants.bookstore.Constants;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public abstract class BaseApi {
    protected RequestSpecification request;

    public BaseApi() {
        RestAssured.baseURI = Constants.BASE_URL;
        request = RestAssured.given()
                .contentType(ContentType.JSON)
                .log().all();
    }
}

