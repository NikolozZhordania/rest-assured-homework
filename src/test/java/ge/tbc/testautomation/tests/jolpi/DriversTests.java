package ge.tbc.testautomation.tests.jolpi;

import ge.tbc.testautomation.basetest.jolpi.BaseTest;
import ge.tbc.testautomation.data.jolpi.Constants.*;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

import static ge.tbc.testautomation.helpers.jolpi.Utils.findDriversByNationality;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class DriversTests extends BaseTest {

    @Test
    public void validateSeriesAndSeason() {
        String series = jsonPath.getString("MRData.series");
        String season = jsonPath.getString("MRData.DriverTable.season");

        assertThat(series, equalTo(SeriesData.F1));
        assertThat(season, equalTo(SeasonData.SEASON));
    }

    @Test
    public void validateTotalDrivers() {
        int total = Integer.parseInt(jsonPath.getString("MRData.total"));
        List<?> drivers = jsonPath.getList("MRData.DriverTable.Drivers");

        assertThat(drivers.size(), equalTo(total));
    }

    @Test
    public void validateFirstDriverBornBefore1990() {
        Map<String, String> firstDriver = jsonPath.getMap(
                "MRData.DriverTable.Drivers.find { it.dateOfBirth < '1990-01-01' }"
        );
        String fullName = firstDriver.get(DriverFields.GIVEN_NAME) + " " +
                firstDriver.get(DriverFields.FAMILY_NAME);
        assertThat(fullName, equalTo(DriverData.FIRST_DRIVER_BEFORE_1990));
    }

    @Test
    public void validateDriversBornAfter2000() {
        List<String> after2000 = jsonPath.getList(
                "MRData.DriverTable.Drivers.findAll { it.dateOfBirth > '2000-01-01' }" +
                        ".collect { it.givenName + ' ' + it.familyName }"
        );
        assertThat(after2000.size(), greaterThanOrEqualTo(DriverData.MIN_DRIVERS_AFTER_2000));
    }

    @Test
    public void validateFrenchDriversCount() {
        List<String> frenchDrivers = findDriversByNationality(jsonPath, DriverNationalities.FRENCH);
        assertThat(frenchDrivers.size(), equalTo(DriverData.FRENCH_DRIVERS_COUNT));
    }

    @Test
    public void validateDriversFamilyNameAB() {
        List<String> aBDrivers = jsonPath.getList(
                "MRData.DriverTable.Drivers.findAll { it.familyName.matches('^[AB].*') }" +
                        ".collect { it.givenName + ' ' + it.familyName }"
        );
        assertThat(aBDrivers.size(), greaterThanOrEqualTo(DriverData.A_B_COUNT_MIN));
    }

    @Test
    public void validateBritishDriversAfter1990() {
        List<String> britishAfter1990 = jsonPath.getList(
                "MRData.DriverTable.Drivers.findAll { it.nationality == 'British' && it.dateOfBirth > '1990-01-01' }" +
                        ".collect { it.givenName + ' ' + it.familyName }"
        );
        assertThat(britishAfter1990.size(), greaterThanOrEqualTo(DriverData.BRITISH_AFTER_1990_MIN));
    }

    @Test
    public void validateSpecialDrivers() {
        List<String> specialDrivers = jsonPath.getList(
                "MRData.DriverTable.Drivers.findAll { " +
                        "(it.permanentNumber != null && it.permanentNumber.toInteger() < 10) || " +
                        "it.familyName.length() > 7" +
                        " }.collect { it.givenName + ' ' + it.familyName }"
        );

        specialDrivers.forEach(System.out::println);

        assertThat(specialDrivers.size(), greaterThanOrEqualTo(DriverData.SPECIAL_DRIVERS_MIN));
    }


    @Test
    public void validateNationalityDrivers() {
        List<String> dutchDriver = findDriversByNationality(jsonPath, DriverNationalities.DUTCH);
        assertThat(dutchDriver, hasItem(DriverData.DUTCH_DRIVER));

        List<String> brazilianDriver = findDriversByNationality(jsonPath, DriverNationalities.BRAZILIAN);
        assertThat(brazilianDriver.size(), greaterThan(0));

        List<String> canadianDriver = findDriversByNationality(jsonPath, DriverNationalities.CANADIAN);
        assertThat(canadianDriver, hasItem(DriverData.CANADIAN_DRIVER));
    }
}
