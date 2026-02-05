package ge.tbc.testautomation.tests.petstore;

import ge.tbc.testautomation.basetest.petstore.BaseTest;
import ge.tbc.testautomation.data.common.StatusCodes;
import ge.tbc.testautomation.data.petstore.Constants.*;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class PetStoreTests extends BaseTest {

    @Test
    public void createPetOrder() {
        given()
                .contentType(ContentType.JSON)
                .body("""
                        {
                            "id": 1001,
                            "petId": 10,
                            "quantity": 1,
                            "shipDate": "2026-02-05T12:00:00.000Z",
                            "status": "placed",
                            "complete": true
                        }
                    """)
                .when()
                .post(Endpoints.CREATE_ORDER)
                .then()
                .log().ifValidationFails()
                .statusCode(StatusCodes.OK)
                .body("id", notNullValue())
                .body("petId", equalTo(10))
                .body("status", equalTo("placed"));
    }

    @Test
    public void updatePetForm() {
        given()
                .contentType(ContentType.URLENC)
                .formParam(FormParams.NAME, PetData.PET_NAME)
                .formParam(FormParams.STATUS, PetData.PET_STATUS_AVAILABLE)
                .when()
                .post(Endpoints.UPDATE_PET)
                .then()
                .log().ifValidationFails()
                .statusCode(StatusCodes.OK)
                .body("code", notNullValue())
                .body("type", notNullValue())
                .body("message", notNullValue());
    }

    @Test
    public void getPetNotFound() {
        given()
                .when()
                .get(Endpoints.GET_PET)
                .then()
                .log().ifValidationFails()
                .statusCode(StatusCodes.NOT_FOUND);
    }


    @Test
    public void loginUser() {
        String message =
                given()
                        .queryParam(QueryParams.USERNAME, UserInfo.TEST_USERNAME)
                        .queryParam(QueryParams.PASSWORD, UserInfo.TEST_PASSWORD)
                        .when()
                        .get(Endpoints.LOGIN_USER)
                        .then()
                        .log().ifValidationFails()
                        .statusCode(StatusCodes.OK)
                        .extract().jsonPath().getString("message");

        String number = message.replaceAll("\\D", "");
        assert number.length() == 10 : "Expected 10 significant digits but found " + number.length();
        System.out.println("Extracted number: " + number);
    }

    @Test
    public void logoutUser() {
        given()
                .when()
                .get(Endpoints.LOGOUT_USER)
                .then()
                .log().ifValidationFails()
                .statusCode(StatusCodes.OK);
    }
}


