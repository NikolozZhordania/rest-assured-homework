package ge.tbc.testautomation.basetest.openlibrary;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;

import static ge.tbc.testautomation.data.openlibrary.Constants.*;

public class BaseTest {

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = URI.BASE_URI;
    }
}