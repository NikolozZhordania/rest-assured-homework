package ge.tbc.testautomation.api.client.restfulbooker;

import ge.tbc.testautomation.data.constants.restfulbooker.Constants.*;
import ge.tbc.testautomation.data.models.request.restfulbooker.AuthRequest;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class BookerAuthApi {

    public Response createToken() {
        AuthRequest authRequest = new AuthRequest(AuthReq.USERNAME, AuthReq.PASSWORD);

        return given()
                .baseUri(URI.BASE)
                .contentType(ContentType.JSON)
                .body(authRequest)
                .post(Paths.AUTH);
    }
}
