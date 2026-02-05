package ge.tbc.testautomation.data.openlibrary;

public class Constants {

    public static class Titles{
        public static final String BOOK_TITLE = "Harry Potter and the Philosopher's Stone";
        public static final String AUTHOR_NAME = "J. K. Rowling";
        public static final String NOVEL_TITLE = "Harry Potter";
    }

    public static class URI {
        public static final String BASE_URI = "https://openlibrary.org";
    }

    public static class Endpoints {
        public static final String GET_SEARCH = "/search.json";
    }

    public static class QueryParams {
        public static final String SEARCH = "q";
    }
}
