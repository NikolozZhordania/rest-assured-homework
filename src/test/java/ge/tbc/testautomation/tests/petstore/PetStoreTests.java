package ge.tbc.testautomation.tests.petstore;
import ge.tbc.testautomation.basetest.petstore.BaseTest;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PetStoreTests extends BaseTest {

    @Test(description = "Validate the created order")
    public void validateCreatedOrderTest() {
        steps.validateCreatedOrder();
    }

    @Test(description = "Retrieve the created order")
    public void retrieveOrderTest() {
        steps.retrieveOrder();
        assertThat(steps.getRetrievedOrder()).isNotNull();
    }

    @Test(description = "Validate the retrieved order")
    public void validateRetrievedOrderTest() {
        steps.validateRetrievedOrder();
    }

}
