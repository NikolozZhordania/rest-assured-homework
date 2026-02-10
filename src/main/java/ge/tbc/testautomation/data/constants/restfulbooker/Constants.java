package ge.tbc.testautomation.data.constants.restfulbooker;

import com.github.javafaker.Faker;

public class Constants {
    public static final Faker faker = new Faker();

    public static class BookingData {
        public static final String FIRST_NAME = faker.name().firstName();
        public static final String LAST_NAME = faker.name().lastName();
        public static final String ADDITIONAL_NEEDS = "Breakfast";
        public static final int TOTAL_PRICE = faker.number().numberBetween(50, 100);
        public static final boolean DEPOSIT_PAID = true;
        public static final String CHECK_IN = "2026-01-02";
        public static final String CHECK_OUT = "2026-01-03";
        public static final String NEW_FIRST_NAME = FIRST_NAME + " UPDATED";
    }

    public static class URI {
        public static final String BASE = "https://restful-booker.herokuapp.com";
    }

    public static class Paths {
        public static final String AUTH = "/auth";
    }

    public static class Endpoints {
        public static final String BOOKING = "/booking";
    }

    public static class Headers {
        public static final String COOKIE = "Cookie";
        public static final String TOKEN = "token=";
    }

    public static class AuthReq {
        public static final String USERNAME = "admin";
        public static final String PASSWORD = "password123";
    }
}
