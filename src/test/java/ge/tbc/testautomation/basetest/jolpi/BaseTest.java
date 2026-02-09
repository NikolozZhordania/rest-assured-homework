package ge.tbc.testautomation.basetest.jolpi;

import ge.tbc.testautomation.data.jolpi.Constants.*;
import ge.tbc.testautomation.data.common.StatusCodes;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.not;

public class BaseTest {

    protected RequestSpecification requestSpec;
    protected ResponseSpecification responseSpec;

    protected Response response;
    protected JsonPath jsonPath;

    @BeforeClass
    public void setUp() {
        requestSpec = new RequestSpecBuilder()
                .setBaseUri(URI.BASE_URI)
                .setBasePath(Paths.BASE_PATH)
                .setContentType(ContentType.JSON)
                .build().log().all();

        responseSpec = new ResponseSpecBuilder()
                .expectStatusCode(StatusCodes.OK)
                .expectBody(not(empty()))
                .log(LogDetail.ALL)
                .build();

        response = given()
                .spec(requestSpec)
                .param(Params.FORMAT, Params.JSON)
                .get(Endpoints.DRIVERS)
                .then()
                .spec(responseSpec)
                .extract()
                .response();

        jsonPath = response.jsonPath();
    }
}
