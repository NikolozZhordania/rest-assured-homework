package ge.tbc.testautomation.tests.escuela;


import ge.tbc.testautomation.data.models.request.escuela.UserRequest;
import ge.tbc.testautomation.steps.escuela.EscuelaAuthSteps;
import org.testng.annotations.Test;

import static ge.tbc.testautomation.data.constants.escuela.Constants.*;

public class EscuelaAuthTests {
    @Test
    public void testUserCreationAndLogin() {
        UserRequest user = new UserRequest();

        user.setName(UserInfo.FIRST_NAME + " " + UserInfo.LAST_NAME);
        user.setEmail(UserInfo.EMAIL);
        user.setAvatar(UserInfo.AVATAR);
        user.setPassword(UserInfo.PASSWORD);

        new EscuelaAuthSteps()
                .createUser(user)
                .loginUser(user)
                .validateProfile(user.getEmail());
    }
}
