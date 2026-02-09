package ge.tbc.testautomation.helpers.jolpi;

import io.restassured.path.json.JsonPath;

import java.util.List;

public class Utils {

    public static List<String> findDriversByNationality(JsonPath jsonPath, String nationality) {
        return jsonPath.getList(
                String.format(
                        "MRData.DriverTable.Drivers.findAll { it.nationality == '%s' }" +
                                ".collect { it.givenName + ' ' + it.familyName }",
                        nationality
                )
        );
    }

}
