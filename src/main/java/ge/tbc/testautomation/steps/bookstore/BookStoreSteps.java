package ge.tbc.testautomation.steps.bookstore;

import ge.tbc.testautomation.api.client.bookstore.BookStoreApi;
import ge.tbc.testautomation.data.constants.bookstore.Constants.AuthorNames;
import ge.tbc.testautomation.data.models.response.bookstore.BooksResponse;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;


public class BookStoreSteps {

    private final BookStoreApi api = new BookStoreApi();
    private BooksResponse books;

    public BookStoreSteps fetchBooks() {
        this.books = api.getBooks()
                .then()
                .statusCode(200)
                .extract()
                .as(BooksResponse.class);
        System.out.println("Total books: " + books.getBooks().size());
        return this;
    }

    public BookStoreSteps validatePageCount() {
        books.getBooks().forEach(book -> {
            assertThat(book.getPages(), lessThan(1000));
            System.out.println(book.getAuthor() + " - " + book.getTitle());
        });
        return this;
    }

    public BookStoreSteps validateLastTwoAuthors() {
        int totalBooks = books.getBooks().size();
        var lastBook = books.getBooks().get(totalBooks - 1);
        var secondLastBook = books.getBooks().get(totalBooks - 2);

        assertThat(secondLastBook.getAuthor(), equalTo(AuthorNames.SECOND_LAST_AUTHOR_NAME));
        assertThat(lastBook.getAuthor(), equalTo(AuthorNames.LAST_AUTHOR_NAME));
        return this;
    }
}
