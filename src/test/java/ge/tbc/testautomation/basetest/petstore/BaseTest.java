package ge.tbc.testautomation.basetest.petstore;

import ge.tbc.testautomation.data.petstore.Constants.*;
import ge.tbc.testautomation.steps.petstore.PetStoreSteps;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.ErrorLoggingFilter;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import pet.store.v3.invoker.ApiClient;
import pet.store.v3.invoker.JacksonObjectMapper;


import static ge.tbc.testautomation.data.petstore.Constants.*;
import static io.restassured.RestAssured.config;
import static io.restassured.config.ObjectMapperConfig.objectMapperConfig;

public class BaseTest {
    protected ApiClient api;
    protected PetStoreSteps steps;

    @BeforeSuite
    public void createApi() {
        api = ApiClient.api(ApiClient.Config.apiConfig()
                .reqSpecSupplier(() -> new RequestSpecBuilder()
                        .log(LogDetail.ALL)
                        .setConfig(config()
                                .objectMapperConfig(objectMapperConfig()
                                        .defaultObjectMapper(JacksonObjectMapper.jackson())))
                        .addFilter(new ErrorLoggingFilter())
                        .addFilter(new RequestLoggingFilter())
                        .addFilter(new ResponseLoggingFilter())
                        .setBaseUri(URL.BASE_URL)));
    }

    @BeforeClass
    public void setUp() {
        steps = new PetStoreSteps(api);
        steps.prepareOrder(PetData.PET_ID, PetData.QUANTITY, true)
                .createOrder();
    }

}
