package ge.tbc.testautomation.helpers;

import com.github.javafaker.Faker;
import ge.tbc.testautomation.data.models.request.booking.BookingRequest;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class BookingTestHelper {

    private static final Faker faker = new Faker();
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static BookingRequest randomBookingRequest() {
        LocalDate checkIn = LocalDate.now().plusDays(faker.number().numberBetween(1, 30));
        LocalDate checkOut = checkIn.plusDays(faker.number().numberBetween(1, 10));

        return BookingRequest.builder()
                .firstName(faker.name().firstName())
                .lastName(faker.name().lastName())
                .totalPrice(faker.number().numberBetween(50, 500))
                .depositPaid(faker.bool().bool())
                .bookingDates(
                        BookingRequest.BookingDates.builder()
                                .checkIn(checkIn.format(formatter))
                                .checkOut(checkOut.format(formatter))
                                .build()
                )
                .additionalNeeds(faker.options().option("Breakfast", "Lunch", "Dinner", "None"))
                .build();
    }
}