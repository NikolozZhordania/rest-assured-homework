package ge.tbc.testautomation.tests.bookstore;

import ge.tbc.testautomation.steps.bookstore.BookStoreSteps;
import ge.tbc.testautomation.data.models.request.bookstore.BookstoreOrderRequest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class BookStoreTests {

    private BookStoreSteps steps;
    private BookstoreOrderRequest request;

    @BeforeClass
    public void setup() {
        steps = new BookStoreSteps();
        request = BookstoreOrderRequest.builder()
                .id(123)
                .petId(1)
                .quantity(2)
                .status("placed")
                .complete(true)
                .build();
    }

    @Test(description = "Create bookstore order")
    public void testCreateOrder() throws Exception {
        steps.createOrder(request)
                .validateStatusCode(200)
                .validateOrderFields(request);
    }
}
