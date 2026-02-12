package ge.tbc.testautomation.api.client.escuela;

import ge.tbc.testautomation.data.constants.escuela.Constants;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public abstract class BaseApi {

    protected static RequestSpecification escuelaSpec() {
        return new RequestSpecBuilder()
                .setBaseUri(Constants.URI.BASE)
                .setContentType(ContentType.JSON)
                .log(LogDetail.ALL)
                .build();
    }
}