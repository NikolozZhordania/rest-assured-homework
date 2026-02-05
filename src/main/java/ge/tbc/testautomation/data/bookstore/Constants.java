package ge.tbc.testautomation.data.bookstore;

public class Constants {
    public static class URI {
        public static final String BASE_URI = "https://bookstore.toolsqa.com";
    }

    public static class Paths {
        public static final String BASE_PATH = "/BookStore/v1";
    }

    public static class Endpoints {
        public static final String GET_BOOKS = "/Books";
        public static final String GET_BOOK = "/Book";
        public static final String DELETE_BOOK = "/Book";
    }

    public static class Messages {
        public static final String NOT_AUTHORIZED_MESSAGE = "User not authorized!";
    }


    public static class QueryParams {
        public static final String ISBN = "ISBN";
    }

    public static class Limits {
        public static final int MAX_BOOKS = 2;
    }

}
