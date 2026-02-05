package ge.tbc.testautomation.data.bookstore;

import ge.tbc.testautomation.helpers.bookstore.Util;
import org.testng.annotations.DataProvider;

import java.util.List;
import java.util.Map;

public class BookDataProvider {

    @DataProvider
    public Object[][] bookData() {
        List<Map<String, String>> books = Util.fetchBooks(2);

        Object[][] data = new Object[books.size()][1];
        for (int i = 0; i < books.size(); i++) {
            data[i][0] = books.get(i);
        }
        return data;
    }
}
