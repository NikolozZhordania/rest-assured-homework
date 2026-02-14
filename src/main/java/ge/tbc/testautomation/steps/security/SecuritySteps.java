package ge.tbc.testautomation.steps.security;

import com.github.javafaker.Faker;
import io.qameta.allure.Step;
import lombok.Getter;
import security.v1.api.AuthenticationApi;
import security.v1.api.AuthorizationApi;
import security.v1.invoker.ApiClient;
import security.v1.model.*;
import ge.tbc.testautomation.data.security.Constants;

import static org.assertj.core.api.Assertions.assertThat;

@Getter
public class SecuritySteps {

    private final AuthenticationApi authenticationApi;
    private final AuthorizationApi authorizationApi;

    private String accessToken;
    private String refreshToken;
    private String email;
    private String firstName;
    private String lastName;
    private final String password = Constants.Passwords.DEFAULT_PASSWORD;

    private final Faker faker = new Faker();

    public SecuritySteps(ApiClient apiClient) {
        this.authenticationApi = apiClient.authentication();
        this.authorizationApi = apiClient.authorization();
    }

    @Step("Generate unique admin email and names")
    public SecuritySteps generateAdminData() {
        firstName = faker.name().firstName();
        lastName = faker.name().lastName();
        email = firstName.toLowerCase() + "." + lastName.toLowerCase() + "@test.com";
        return this;
    }

    @Step("Build admin register request")
    public RegisterRequest buildAdminRegisterRequest() {
        return new RegisterRequest()
                .firstname(firstName)
                .lastname(lastName)
                .email(email)
                .password(password)
                .role(RegisterRequest.RoleEnum.ADMIN);
    }

    @Step("Send register request")
    public AuthenticationResponse sendRegisterRequest(RegisterRequest request) {
        return authenticationApi
                .register()
                .body(request)
                .executeAs(r -> {
                    r.then().statusCode(200);
                    return r;
                });
    }

    @Step("Save tokens from register response")
    public SecuritySteps saveTokens(AuthenticationResponse response) {
        accessToken = response.getAccessToken();
        refreshToken = response.getRefreshToken();

        assertThat(accessToken).isNotBlank();
        assertThat(refreshToken).isNotBlank();
        return this;
    }

    @Step("Access admin protected resource")
    public String accessAdminProtectedResource() {
        return authorizationApi
                .sayHelloWithRoleAdminAndReadAuthority()
                .reqSpec(req -> req.addHeader("Authorization", "Bearer " + accessToken))
                .execute(r -> {
                    r.then().statusCode(200);
                    return r;
                })
                .asString();
    }

    @Step("Validate protected resource message")
    public SecuritySteps validateProtectedMessage(String message) {
        assertThat(message)
                .isEqualTo(Constants.Messages.ADMIN_PROTECTED_RESOURCE_MESSAGE);
        return this;
    }

    @Step("Build authentication request")
    public AuthenticationRequest buildAuthenticationRequest() {
        return new AuthenticationRequest()
                .email(email)
                .password(password);
    }

    @Step("Send authentication request")
    public AuthenticationResponse sendAuthenticationRequest(AuthenticationRequest request) {
        return authenticationApi
                .authenticate()
                .body(request)
                .executeAs(r -> {
                    r.then().statusCode(200);
                    return r;
                });
    }

    @Step("Validate admin privileges")
    public SecuritySteps validateAdminPrivileges(AuthenticationResponse response) {
        assertThat(response.getRoles())
                .containsExactlyInAnyOrder(
                        Constants.Roles.READ_PRIVILEGE,
                        Constants.Roles.WRITE_PRIVILEGE,
                        Constants.Roles.DELETE_PRIVILEGE,
                        Constants.Roles.UPDATE_PRIVILEGE,
                        Constants.Roles.ROLE_ADMIN
                );
        return this;
    }

    @Step("Build refresh token request")
    public RefreshTokenRequest buildRefreshRequest() {
        return new RefreshTokenRequest()
                .refreshToken(refreshToken);
    }

    @Step("Send refresh token request")
    public RefreshTokenResponse sendRefreshRequest(RefreshTokenRequest request) {
        return authenticationApi
                .refreshToken()
                .body(request)
                .executeAs(r -> {
                    r.then().statusCode(200);
                    return r;
                });
    }

    @Step("Validate new access token")
    public SecuritySteps validateNewAccessToken(RefreshTokenResponse response) {
        assertThat(response.getAccessToken()).isNotBlank();
        return this;
    }
}
