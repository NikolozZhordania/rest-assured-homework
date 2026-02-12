package ge.tbc.testautomation.api.client.booking;

import ge.tbc.testautomation.data.constants.booking.Constants;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

public abstract class BaseApi {
    protected RequestSpecification request;

    public BaseApi() {
        RestAssured.baseURI = Constants.BASE_URL;
        request = RestAssured.given()
                .contentType("application/json") // didn't work with ContentType.JSON
                .accept("application/json")
                .log().all();
    }

    protected RequestSpecification spec() {
        return request;
    }
}
