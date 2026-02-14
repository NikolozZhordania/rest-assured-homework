package ge.tbc.testautomation.basetest.security;

import ge.tbc.testautomation.data.security.Constants;
import ge.tbc.testautomation.steps.security.SecuritySteps;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.ErrorLoggingFilter;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.filter.log.LogDetail;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import security.v1.invoker.ApiClient;
import static io.restassured.config.RestAssuredConfig.config;
import static io.restassured.config.ObjectMapperConfig.objectMapperConfig;
import static security.v1.invoker.JacksonObjectMapper.jackson;

public class BaseTest {

    protected ApiClient apiClient;
    protected SecuritySteps securitySteps;

    @BeforeSuite
    public void createApiClient() {
        apiClient = ApiClient.api(ApiClient.Config.apiConfig()
                .reqSpecSupplier(() -> new RequestSpecBuilder()
                        .log(LogDetail.ALL)
                        .setConfig(config()
                                .objectMapperConfig(objectMapperConfig()
                                        .defaultObjectMapper(jackson())))
                        .addFilter(new ErrorLoggingFilter())
                        .addFilter(new RequestLoggingFilter())
                        .addFilter(new ResponseLoggingFilter())
                        .setBaseUri(Constants.URL.BASE_URL))
        );
    }

    @BeforeClass
    public void setUp() {
        securitySteps = new SecuritySteps(apiClient);
    }
}
