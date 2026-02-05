package ge.tbc.testautomation.basetest.petstore;

import ge.tbc.testautomation.data.petstore.Constants.*;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;

public class BaseTest {

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = URI.BASE_URI;
        RestAssured.basePath = Paths.BASE_PATH;
    }
}
