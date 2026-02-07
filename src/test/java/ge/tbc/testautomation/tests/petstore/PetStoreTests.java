package ge.tbc.testautomation.tests.petstore;

import ge.tbc.testautomation.basetest.petstore.BaseTest;
import ge.tbc.testautomation.data.common.StatusCodes;
import ge.tbc.testautomation.data.petstore.Constants.*;
import ge.tbc.testautomation.helpers.petstore.Utils;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.annotations.Test;

import java.util.List;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class PetStoreTests extends BaseTest {

    @Test
    public void addPetAndValidate() {
        long petId = Utils.createPet(requestSpec, Status.AVAILABLE);

        Response byStatus = given()
                .spec(requestSpec)
                .param(JsonKeys.STATUS, Status.AVAILABLE)
                .get(Endpoints.PET_BY_STATUS)
                .then()
                .statusCode(StatusCodes.OK)
                .extract().response();

        List<Long> ids = ((List<?>) byStatus.path(JsonKeys.ID)).stream()
                .map(obj -> ((Number) obj).longValue())
                .collect(Collectors.toList());

        assertThat(ids, hasItem(petId));

        JSONObject foundPet = new JSONObject(
                byStatus.jsonPath().getMap(String.format("find { it.id == %d }", petId))
        );

        assertThat(foundPet.getLong(JsonKeys.ID), equalTo(petId));
        assertThat(foundPet.getString(JsonKeys.STATUS), equalTo(Status.AVAILABLE));
        assertThat(foundPet.getString(JsonKeys.NAME), startsWith(PetData.PET_TYPE));
    }

    @Test
    public void updatePetFormData() {
        long petId = Utils.createPet(requestSpec, Status.AVAILABLE);
        String petByIdEndpoint = Endpoints.PET_BY_ID.replace(PathParams.PET_ID, String.valueOf(petId));

        given()
                .spec(requestSpec)
                .contentType(ContentType.URLENC)
                .formParam(JsonKeys.NAME, PetData.UPDATED_PET_NAME)
                .formParam(JsonKeys.STATUS, Status.SOLD)
                .post(petByIdEndpoint)
                .then()
                .statusCode(StatusCodes.OK);

        Response response = given()
                .spec(requestSpec)
                .get(petByIdEndpoint)
                .then()
                .statusCode(StatusCodes.OK)
                .extract().response();

        assertThat(response.path(JsonKeys.NAME), equalTo(PetData.UPDATED_PET_NAME));
        assertThat(response.path(JsonKeys.STATUS), equalTo(Status.SOLD));
    }

    @Test
    public void uploadPetImage() {
        long petId = Utils.createPet(requestSpec, Status.AVAILABLE);

        Response response = Utils.uploadPetImage(
                requestSpec,
                petId,
                FilePaths.SEA_OTTER_IMAGE,
                Metadata.SEA_OTTER
        );

        assertThat(
                response.path(JsonKeys.MESSAGE),
                containsString(Metadata.SEA_OTTER)
        );
    }
}
