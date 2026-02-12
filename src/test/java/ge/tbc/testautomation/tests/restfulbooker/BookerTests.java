package ge.tbc.testautomation.tests.restfulbooker;

import ge.tbc.testautomation.steps.restfulbooker.BookerSteps;
import org.testng.annotations.Test;

public class BookerTests {

    @Test
    public void bookingFlowTest() throws Exception {
        new BookerSteps()
                .requestToken()
                .createBooking()
                .verifyBooking()
                .partialUpdateBooking()
                .deleteBooking();
    }
}

