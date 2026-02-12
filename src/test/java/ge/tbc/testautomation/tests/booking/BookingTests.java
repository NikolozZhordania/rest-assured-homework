package ge.tbc.testautomation.tests.booking;

import ge.tbc.testautomation.data.models.request.booking.AuthRequest;
import ge.tbc.testautomation.data.models.request.booking.BookingRequest;
import ge.tbc.testautomation.helpers.BookingTestHelper;
import ge.tbc.testautomation.steps.booking.BookingSteps;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static ge.tbc.testautomation.data.constants.booking.Constants.AUTH_PASSWORD;
import static ge.tbc.testautomation.data.constants.booking.Constants.AUTH_USERNAME;

public class BookingTests {

    private BookingSteps bookingSteps;
    private int bookingId;
    private BookingRequest initialRequest;
    private BookingRequest updateRequest;


    @BeforeClass
    public void setUp() {
        bookingSteps = new BookingSteps();
        bookingSteps.authenticate(new AuthRequest(AUTH_USERNAME, AUTH_PASSWORD));

        initialRequest = BookingTestHelper.randomBookingRequest();
        updateRequest = BookingTestHelper.randomBookingRequest();
    }

    @Test(priority = 1)
    public void testCreateBooking() {
        bookingId = bookingSteps.createBooking(initialRequest);
    }

    @Test(priority = 2, dependsOnMethods = "testCreateBooking")
    public void testUpdateBooking() {
        bookingSteps.updateBooking(bookingId, updateRequest)
                .validateStatusCode(200);
    }

    @Test(priority = 3, dependsOnMethods = "testUpdateBooking")
    public void testValidateUpdatedBooking() {
        bookingSteps.validateBookingFields(bookingId, updateRequest);
    }
}
