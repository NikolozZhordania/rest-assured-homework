package ge.tbc.testautomation.api.client.bookstore;

import io.restassured.response.Response;
import ge.tbc.testautomation.data.constants.bookstore.Constants;

public class BookStoreApi extends BaseApi {

    public Response createOrder(Object body) {
        return request.body(body)
                .post(Constants.CREATE_ORDER)
                .then().log().all()
                .extract().response();
    }
}
