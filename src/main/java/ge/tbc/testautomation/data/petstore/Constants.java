package ge.tbc.testautomation.data.petstore;

public class Constants {

    public static class URI {
        public static final String BASE_URI = "https://petstore.swagger.io";
    }

    public static class Paths {
        public static final String BASE_PATH = "/v2";
    }

    public static class Endpoints {
        public static final String PET = "/pet";
        public static final String PET_BY_STATUS = "/pet/findByStatus";
        public static final String PET_BY_ID = "/pet/{petId}";
        public static final String UPLOAD_IMAGE = "/pet/{petId}/uploadImage";
    }

    public static class Status {
        public static final String AVAILABLE = "available";
        public static final String SOLD = "sold";
    }

    public static class JsonKeys {
        public static final String ID = "id";
        public static final String NAME = "name";
        public static final String STATUS = "status";
        public static final String MESSAGE = "message";
    }

    public static class FilePaths {
        public static final String SEA_OTTER_IMAGE = "src/test/resources/Sea_Otter.jpg";
    }

    public static class Metadata {
        public static final String SEA_OTTER = "SeaOtterPhoto";
    }

    public static class PetData {
        public static final String PET_TYPE = "Otter_";
        public static final String UPDATED_PET_NAME = "Otter_Updated";
    }

    public static class PathParams {
        public static final String PET_ID = "{petId}";
    }

    public static class MultiPartParams {
        public static final String FILE = "file";
        public static final String ADDITIONAL_META_DATA = "additionalMetadata";
    }
}
