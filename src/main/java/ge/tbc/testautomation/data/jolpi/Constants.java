package ge.tbc.testautomation.data.jolpi;

public class Constants {

    public static class URI {
        public static final String BASE_URI = "https://api.jolpi.ca";
    }

    public static class Paths {
        public static final String BASE_PATH = "/ergast/f1/2025";
    }

    public static class Endpoints {
        public static final String DRIVERS = "/drivers";
    }

    public static class Params {
        public static final String FORMAT = "format";
        public static final String JSON = "json";
    }

    public static class SeriesData{
        public static final String F1 = "f1";
    }

    public static class SeasonData {
        public static final String SEASON = "2025";
    }

    public static class DriverData {
        public static final String DUTCH_DRIVER = "Max Verstappen";
        public static final String CANADIAN_DRIVER = "Lance Stroll";
        public static final String FIRST_DRIVER_BEFORE_1990 = "Fernando Alonso";
        public static final int MIN_DRIVERS_AFTER_2000 = 8;
        public static final int FRENCH_DRIVERS_COUNT = 3;
        public static final int A_B_COUNT_MIN = 5;
        public static final int BRITISH_AFTER_1990_MIN = 3;
        public static final int SPECIAL_DRIVERS_MIN = 5;
    }

    public class DriverFields {
        public static final String GIVEN_NAME = "givenName";
        public static final String FAMILY_NAME = "familyName";
    }

    public class DriverNationalities {
        public static final String BRAZILIAN = "Brazilian";
        public static final String DUTCH = "Dutch";
        public static final String CANADIAN = "Canadian";
        public static final String FRENCH = "French";
    }
}
