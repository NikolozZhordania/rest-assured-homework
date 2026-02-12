package ge.tbc.testautomation.steps.swapi;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import ge.tbc.testautomation.api.client.swapi.SwapiApi;
import ge.tbc.testautomation.data.models.response.swapi.PlanetListItem;
import ge.tbc.testautomation.data.models.response.swapi.PlanetListResponse;
import ge.tbc.testautomation.data.models.response.swapi.PlanetResult;
import ge.tbc.testautomation.data.models.response.swapi.PlanetResponse;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class SwapiSteps {

    private final SwapiApi api;
    private final ObjectMapper mapper;
    private Response lastResponse;

    public SwapiSteps() {
        api = new SwapiApi();
        mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    }

    @Step("Get all planets list")
    public SwapiSteps getPlanetsList() {
        lastResponse = api.getPlanets();
        return this;
    }

    @Step("Deserialize planets list response")
    public List<PlanetListItem> deserializePlanetsList() throws Exception {
        return mapper.readValue(lastResponse.asString(),
                        PlanetListResponse.class)
                .getResults();
    }

    @Step("Get detailed info for a planet by UID")
    public PlanetResult getPlanetDetails(String uid) {
        lastResponse = api.getPlanetByUid(uid);
        try {
            PlanetResponse planetResponse = mapper.readValue(lastResponse.asString(), PlanetResponse.class);
            return planetResponse.getResult().getProperties();
        } catch (Exception e) {
            throw new RuntimeException("Failed to deserialize planet details", e);
        }
    }

    @Step("Get top {0} newest planets by creation date")
    public SwapiSteps getTopNewestPlanets(List<PlanetListItem> planetsList, int count) {
        List<PlanetResult> topNewest = planetsList.stream()
                .map(item -> getPlanetDetails(item.getUid()))
                .filter(p -> p.getCreated() != null)
                .sorted(Comparator.comparing(PlanetResult::getCreated).reversed())
                .limit(count)
                .collect(Collectors.toList());

        topNewest.forEach(p -> System.out.println(p.getName() + " -> " + p.getCreated()));

        // Assertion: descending order
        for (int i = 0; i < topNewest.size() - 1; i++) {
            assertThat(topNewest.get(i).getCreated(), greaterThanOrEqualTo(topNewest.get(i + 1).getCreated()));
        }
        return this;
    }

    @Step("Get planet with max rotation period")
    public SwapiSteps getPlanetWithMaxRotation(List<PlanetListItem> planetsList) {
        PlanetResult maxRotation = planetsList.stream()
                .map(item -> getPlanetDetails(item.getUid()))
                .filter(p -> {
                    try {
                        Integer.parseInt(p.getRotationPeriod());
                        return true;
                    } catch (Exception e) {
                        return false;
                    }
                })
                .max(Comparator.comparingInt(p -> Integer.parseInt(p.getRotationPeriod())))
                .orElseThrow(() -> new RuntimeException("No planet has a valid rotation_period!"));

        System.out.println("Planet with max rotation: " + maxRotation.getName() + " -> " + maxRotation.getRotationPeriod());

        // Assertions
        assertThat(maxRotation.getRotationPeriod(), notNullValue());
        assertThat(Integer.parseInt(maxRotation.getRotationPeriod()), greaterThan(0));

        return this;
    }
}
