package ge.tbc.testautomation.steps.bookstore;

import ge.tbc.testautomation.api.client.bookstore.BookStoreApi;
import ge.tbc.testautomation.data.models.request.bookstore.BookstoreOrderRequest;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static org.hamcrest.Matchers.equalTo;

public class BookStoreSteps {

    private BookStoreApi api = new BookStoreApi();
    private Response lastResponse;

    @Step("Create bookstore order: {request}")
    public BookStoreSteps createOrder(BookstoreOrderRequest request) {
        lastResponse = api.createOrder(request)
                .then()
                .log().all()
                .extract()
                .response();
        return this;
    }

    @Step("Validate last response status code is {statusCode}")
    public BookStoreSteps validateStatusCode(int statusCode) {
        lastResponse.then().statusCode(statusCode);
        return this;
    }

    @Step("Validate last order response fields match request")
    public BookStoreSteps validateOrderFields(BookstoreOrderRequest request) {
        lastResponse.then()
                .body("id", equalTo(request.getId()))
                .body("petId", equalTo(request.getPetId()))
                .body("status", equalTo(request.getStatus()))
                .body("complete", equalTo(request.isComplete()));
        return this;
    }

    public Response getResponse() {
        return lastResponse;
    }
}
