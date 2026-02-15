package ge.tbc.testautomation.tests.swapi;

import ge.tbc.testautomation.steps.swapi.SwapiSteps;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import ge.tbc.testautomation.data.models.response.swapi.PlanetListItem;

public class SwapiTests {

    private SwapiSteps steps;
    private List<PlanetListItem> planetsList;

    @BeforeClass
    public void setup() throws Exception {
        steps = new SwapiSteps();
        planetsList = steps.getPlanetsList()
                .deserializePlanetsList();
    }

    @Test(description = "Validate top 3 newest planets")
    public void testTop3NewestPlanets() {
        steps.getTopNewestPlanets(planetsList, 3);
    }

    @Test(description = "Validate planet with max rotation period")
    public void testPlanetMaxRotation() {
        steps.getPlanetWithMaxRotation(planetsList);
    }
}
