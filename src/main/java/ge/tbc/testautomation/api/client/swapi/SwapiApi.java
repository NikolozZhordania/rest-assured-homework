package ge.tbc.testautomation.api.client.swapi;

import io.restassured.response.Response;

import static ge.tbc.testautomation.data.constants.swapi.Constants.PLANETS_ENDPOINT;

public class SwapiApi extends BaseApi {

    public Response getPlanets() {
        return request
                .get(PLANETS_ENDPOINT);
    }

    public Response getPlanetByUid(String uid) {
        return request
                .get("/planets/" + uid);
    }
}
