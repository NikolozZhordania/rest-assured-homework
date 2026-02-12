package ge.tbc.testautomation.tests.bookstore;

import ge.tbc.testautomation.steps.bookstore.BookStoreSteps;
import org.testng.annotations.Test;

public class BookStoreTests {

    @Test
    public void booksValidationTest() {
        new BookStoreSteps()
                .fetchBooks()
                .validatePageCount()
                .validateLastTwoAuthors();
    }
}