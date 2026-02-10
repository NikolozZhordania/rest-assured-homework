package ge.tbc.testautomation.api.client.escuela;

import ge.tbc.testautomation.data.constants.escuela.Constants.*;
import ge.tbc.testautomation.data.models.request.escuela.LoginRequest;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class EscuelaAuthApi extends BaseApi {

    public Response login(LoginRequest request) {
        return given()
                .spec(escuelaSpec())
                .body(request)
                .post(Paths.AUTH + Endpoints.LOGIN);
    }

    public Response profile(String token) {
        return given()
                .spec(escuelaSpec())
                .header(Headers.AUTHORIZATION, Headers.BEARER + token)
                .get(Paths.AUTH + Endpoints.PROFILE);
    }
}
