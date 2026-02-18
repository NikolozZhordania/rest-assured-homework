package ge.tbc.testautomation.data.security;

public class Constants {

    public static class URL {
        public static final String BASE_URL = "http://localhost:8086";
    }

    public static class Passwords {
        public static final String DEFAULT_PASSWORD = "Password123!";
    }

    public static class Messages {
        public static final String ADMIN_PROTECTED_RESOURCE_MESSAGE =
                "Hello, you have access to a protected resource that requires admin role and read authority.";
    }

    public static class Roles {
        public static final String READ_PRIVILEGE = "READ_PRIVILEGE";
        public static final String WRITE_PRIVILEGE = "WRITE_PRIVILEGE";
        public static final String DELETE_PRIVILEGE = "DELETE_PRIVILEGE";
        public static final String UPDATE_PRIVILEGE = "UPDATE_PRIVILEGE";
        public static final String ROLE_ADMIN = "ROLE_ADMIN";
    }
}