package ge.tbc.testautomation.helpers.petstore;

import com.github.javafaker.Faker;
import ge.tbc.testautomation.data.common.StatusCodes;
import ge.tbc.testautomation.data.petstore.Constants.*;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;

import java.io.File;

import static io.restassured.RestAssured.given;

public class Utils {
    protected static final Faker faker = new Faker();

    public static long createPet(RequestSpecification requestSpec, String status) {
        String petName = PetData.PET_TYPE + faker.name().firstName();

        JSONObject pet = new JSONObject()
                .put(JsonKeys.ID, System.currentTimeMillis())
                .put(JsonKeys.NAME, petName)
                .put(JsonKeys.STATUS, status);

        Response response = given()
                .spec(requestSpec)
                .body(pet.toString())
                .post(Endpoints.PET)
                .then()
                .statusCode(StatusCodes.OK)
                .extract().response();

        return ((Number) response.path(JsonKeys.ID)).longValue();
    }

    public static Response uploadPetImage(RequestSpecification requestSpec, long petId, String filePath, String metadata) {
        File file = new File(filePath);
        String uploadEndpoint = Endpoints.UPLOAD_IMAGE.replace(PathParams.PET_ID, String.valueOf(petId));

        return given()
                .spec(requestSpec)
                .contentType(ContentType.MULTIPART)
                .multiPart(MultiPartParams.FILE, file)
                .multiPart(MultiPartParams.ADDITIONAL_META_DATA, metadata)
                .post(uploadEndpoint)
                .then()
                .statusCode(StatusCodes.OK)
                .extract().response();
    }
}
