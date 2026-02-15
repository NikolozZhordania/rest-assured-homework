package ge.tbc.testautomation.api.client.swapi;

import ge.tbc.testautomation.data.constants.swapi.Constants;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public abstract class BaseApi {
    protected RequestSpecification request;

    public BaseApi() {
        RestAssured.baseURI = Constants.BASE_URL;
        request = RestAssured.given()
                .contentType(ContentType.JSON)
                .filter(new AllureRestAssured())
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter());
    }
}
