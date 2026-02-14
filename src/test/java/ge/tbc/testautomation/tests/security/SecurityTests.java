package ge.tbc.testautomation.tests.security;

import ge.tbc.testautomation.basetest.security.BaseTest;
import org.testng.annotations.Test;


public class SecurityTests extends BaseTest {

    @Test(priority = 1,
            description = "Register an admin user and verify tokens are returned")
    public void registrationShouldPass() {
        securitySteps
                .generateAdminData()
                .saveTokens(
                        securitySteps.sendRegisterRequest(
                                securitySteps.buildAdminRegisterRequest()
                        )
                );
    }

    @Test(priority = 2,
            dependsOnMethods = "registrationShouldPass",
            description = "Access protected admin endpoint using Bearer token")
    public void protectedEndpointShouldReturnMessage() {
        securitySteps.validateProtectedMessage(
                securitySteps.accessAdminProtectedResource()
        );
    }

    @Test(priority = 3,
            dependsOnMethods = "registrationShouldPass",
            description = "Authenticate admin user and validate roles/privileges")
    public void authenticationShouldReturnCorrectPrivileges() {
        securitySteps.validateAdminPrivileges(
                securitySteps.sendAuthenticationRequest(
                        securitySteps.buildAuthenticationRequest()
                )
        );
    }

    @Test(priority = 4,
            dependsOnMethods = "authenticationShouldReturnCorrectPrivileges",
            description = "Refresh the admin user's token and verify new access token is returned")
    public void refreshTokenShouldReturnNewAccessToken() {
        securitySteps.validateNewAccessToken(
                securitySteps.sendRefreshRequest(
                        securitySteps.buildRefreshRequest()
                )
        );
    }

    @Test(priority = 5,
            description = "Run full security flow: register, access, authenticate, and refresh token")
    public void fullSecurityFlowShouldWork() {
        securitySteps
                .generateAdminData()
                .saveTokens(
                        securitySteps.sendRegisterRequest(
                                securitySteps.buildAdminRegisterRequest()
                        )
                );

        securitySteps.validateProtectedMessage(
                securitySteps.accessAdminProtectedResource()
        );

        securitySteps.validateAdminPrivileges(
                securitySteps.sendAuthenticationRequest(
                        securitySteps.buildAuthenticationRequest()
                )
        );

        securitySteps.validateNewAccessToken(
                securitySteps.sendRefreshRequest(
                        securitySteps.buildRefreshRequest()
                )
        );
    }
}