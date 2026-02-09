package ge.tbc.testautomation.basetest.petstore;

import ge.tbc.testautomation.data.petstore.Constants.*;
import ge.tbc.testautomation.data.common.StatusCodes;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;

import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.empty;

public class BaseTest {

    protected RequestSpecification requestSpec;
    protected ResponseSpecification responseSpec;

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
    }
}
