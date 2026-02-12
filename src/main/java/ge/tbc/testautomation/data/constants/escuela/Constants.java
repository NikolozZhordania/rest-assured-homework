package ge.tbc.testautomation.data.constants.escuela;

import com.github.javafaker.Faker;

public class Constants {
    static Faker faker = new Faker();

    public static class UserInfo {
    public static final String FIRST_NAME = faker.name().firstName();
    public static final String LAST_NAME = faker.name().lastName();
    public static final String FULL_NAME = (FIRST_NAME + LAST_NAME).toLowerCase();
    public static final String EMAIL = FULL_NAME + "@gmail.com";
    public static final String AVATAR = "https://picsum.photos/200";
    public static final String PASSWORD = "Password123";
    }

    public static class URI {
        public static final String BASE = "https://api.escuelajs.co/api";
    }

    public static class Paths {
        public static final String AUTH = "/v1/auth";
        public static final String USERS = "/v1/users";
    }

    public static class Endpoints {
        public static final String LOGIN = "/login";
        public static final String PROFILE = "/profile";
    }

    public static class Headers {
        public static final String AUTHORIZATION = "Authorization";
        public static final String BEARER = "Bearer ";
    }
}
