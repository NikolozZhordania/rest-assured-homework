package ge.tbc.testautomation.steps.petstore;

import io.qameta.allure.Step;
import lombok.Getter;
import org.assertj.core.api.SoftAssertions;
import pet.store.v3.invoker.ApiClient;
import pet.store.v3.model.Order;

import java.time.OffsetDateTime;

import static pet.store.v3.invoker.ResponseSpecBuilders.shouldBeCode;
import static pet.store.v3.invoker.ResponseSpecBuilders.validatedWith;

public class PetStoreSteps {

    private final ApiClient api;
    @Getter
    private Order createdOrder;
    @Getter
    private Order retrievedOrder;

    public PetStoreSteps(ApiClient api) {
        this.api = api;
    }

    @Step("Prepare a new order object for pet with ID {petId}")
    public PetStoreSteps prepareOrder(Long petId, int quantity, boolean complete) {
        createdOrder = new Order()
                .id(System.currentTimeMillis())
                .petId(petId)
                .quantity(quantity)
                .shipDate(OffsetDateTime.now())
                .status(Order.StatusEnum.PLACED)
                .complete(complete);
        return this;
    }

    @Step("Send POST request to create the order")
    public PetStoreSteps createOrder() {
        createdOrder = api.store().placeOrder()
                .body(createdOrder)
                .executeAs(validatedWith(shouldBeCode(200)));
        return this;
    }

    @Step("Validate that the created order matches expected values")
    public PetStoreSteps validateCreatedOrder() {
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(createdOrder.getId()).isNotNull();
        softly.assertThat(createdOrder.getPetId()).isNotNull();
        softly.assertThat(createdOrder.getQuantity()).isPositive();
        softly.assertThat(createdOrder.getStatus()).isEqualTo(Order.StatusEnum.PLACED);
        softly.assertThat(createdOrder.getComplete()).isTrue();
        softly.assertAll();
        return this;
    }

    @Step("Retrieve order by ID {createdOrder.id}")
    public PetStoreSteps retrieveOrder() {
        retrievedOrder = api.store().getOrderById()
                .orderIdPath(createdOrder.getId())
                .executeAs(validatedWith(shouldBeCode(200)));
        return this;
    }

    @Step("Validate that the retrieved order matches the created order")
    public PetStoreSteps validateRetrievedOrder() {
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(retrievedOrder)
                .isNotNull()
                .extracting(Order::getId, Order::getPetId, Order::getQuantity, Order::getStatus, Order::getComplete)
                .containsExactly(
                        createdOrder.getId(),
                        createdOrder.getPetId(),
                        createdOrder.getQuantity(),
                        Order.StatusEnum.PLACED,
                        createdOrder.getComplete()
                );
        softly.assertAll();
        return this;
    }

}
