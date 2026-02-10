package ge.tbc.testautomation.api.client.escuela;

import ge.tbc.testautomation.data.constants.escuela.Constants;
import ge.tbc.testautomation.data.models.request.escuela.UserRequest;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class EscuelaUsersApi extends BaseApi {

    public Response createUser(UserRequest user) {
        return given()
                .spec(escuelaSpec())
                .body(user)
                .post(Constants.Paths.USERS);
    }
}
