package ge.tbc.testautomation.data.petstore;


public class Constants {
    public static class URI {
        public static final String BASE_URI = "https://petstore.swagger.io";
    }

    public static class Paths {
        public static final String BASE_PATH = "/v2";
    }

    public static class Endpoints {
        public static final String CREATE_ORDER = "/store/order";
        public static final String UPDATE_PET = "/pet/10";
        public static final String GET_PET = "/pet/140140140140";
        public static final String LOGIN_USER = "/user/login";
        public static final String LOGOUT_USER = "/user/logout";
    }

    public static class QueryParams {
        public static final String USERNAME = "username";
        public static final String PASSWORD = "password";
    }

    public static class FormParams {
        public static final String NAME = "name";
        public static final String STATUS = "status";
    }

    public static class PetData {
        public static final String PET_NAME = "Buddy";
        public static final String PET_STATUS_AVAILABLE = "available";
    }

    public static class UserInfo {
        public static final String TEST_USERNAME = "testuser";
        public static final String TEST_PASSWORD = "testpass";
    }

}
