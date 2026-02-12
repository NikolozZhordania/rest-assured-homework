package ge.tbc.testautomation.steps.escuela;

import ge.tbc.testautomation.api.client.escuela.EscuelaAuthApi;
import ge.tbc.testautomation.api.client.escuela.EscuelaUsersApi;
import ge.tbc.testautomation.data.models.request.escuela.LoginRequest;
import ge.tbc.testautomation.data.models.request.escuela.UserRequest;
import ge.tbc.testautomation.data.models.response.escuela.LoginResponse;
import ge.tbc.testautomation.data.models.response.escuela.ProfileResponse;
import io.restassured.response.Response;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class EscuelaAuthSteps {

    private final EscuelaUsersApi usersApi = new EscuelaUsersApi();
    private final EscuelaAuthApi authApi = new EscuelaAuthApi();

    private String accessToken;

    public EscuelaAuthSteps createUser(UserRequest user) {
        Response response = usersApi.createUser(user);
        response.then().statusCode(201);
        System.out.println("Create User Response: " + response.asString());
        return this;
    }

    public EscuelaAuthSteps loginUser(UserRequest user) {
        LoginRequest login = new LoginRequest();
        login.setEmail(user.getEmail());
        login.setPassword(user.getPassword());

        Response response = authApi.login(login);
        response.then().statusCode(201);
        System.out.println("Login Response: " + response.asString());

        LoginResponse tokens = response.as(LoginResponse.class);
        assertThat(tokens.getAccessToken(), notNullValue());
        assertThat(tokens.getRefreshToken(), notNullValue());

        this.accessToken = tokens.getAccessToken();

        return this;
    }

    public EscuelaAuthSteps validateProfile(String expectedEmail) {
        Response response = authApi.profile(this.accessToken);
        ProfileResponse profile = response.then()
                .statusCode(200)
                .extract()
                .as(ProfileResponse.class);

        assertThat(profile.getEmail(), equalTo(expectedEmail));
        assertThat(profile.getId(), greaterThan(0));

        return this;
    }
}
