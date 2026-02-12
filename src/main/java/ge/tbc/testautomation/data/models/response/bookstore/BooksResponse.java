package ge.tbc.testautomation.data.models.response.bookstore;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;


import java.util.List;

@Getter
public class BooksResponse {
    @JsonProperty("books")
    private List<Book> books;
}
