package creation.catalogues;

import creation.Book;

import java.util.List;

public interface LibraryCatalogue {
    List<Book> searchFor(String query);
}
