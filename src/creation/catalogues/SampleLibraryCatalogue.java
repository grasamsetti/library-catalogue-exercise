package creation.catalogues;

import creation.Book;

import java.util.List;
import java.util.stream.Collectors;

public class SampleLibraryCatalogue implements LibraryCatalogue {

    private final List<Book> books;

    public SampleLibraryCatalogue(List<Book> books) {
      this.books = books;
    }

    @Override
    public List<Book> searchFor(String query) {
      return books.stream()
              .filter(book -> book.matchesAuthor(QueryParser.lastNameFrom(query)))
              .filter(book -> book.matchesAuthor(QueryParser.firstNameFrom(query)))
              .filter(book -> book.matchesTitle(QueryParser.titleFrom(query)))
              .filter(book -> book.publishedSince(QueryParser.publishedAfterFrom(query)))
              .filter(book -> book.publishedBefore(QueryParser.publishedBeforeFrom(query)))
              .collect(Collectors.toList());
    }
  }