package creation;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;


import creation.catalogues.LibraryCatalogue;
import creation.catalogues.SampleLibraryCatalogue;
import org.junit.Test;

public class BookSearchQueryTest {

  private final List<Book> sampleBooks = Arrays.asList(
          new Book("A Tale of Two Cities", "Charles Dickens", 1859),
          new Book("Pride and Prejudice", "Jane Austen", 1813),
          new Book("Lord of the Flies", "William Golding", 1954),
          new Book("Hamlet", "William Shakespeare", 1603)
  );

  private final LibraryCatalogue sampleCatalogue = new SampleLibraryCatalogue(sampleBooks);

  @Test
  public void searchesByAuthorSurname() {
    List<Book> books = new BookSearchQueryBuilder(sampleCatalogue)
            .withName2("Dickens")
            .build()
            .execute();

    assertThat(books.size(), is(1));
    assertTrue(books.getFirst().matchesAuthor("Dickens"));
  }

  @Test
  public void searchesByAuthorFirstname() {
    List<Book> books = new BookSearchQueryBuilder(sampleCatalogue)
            .withName1("Jane")
            .build()
            .execute();

    assertThat(books.size(), is(1));
    assertTrue(books.getFirst().matchesAuthor("Austen"));
  }

  @Test
  public void searchesByTitle() {
    List<Book> books = new BookSearchQueryBuilder(sampleCatalogue)
            .withTitle("Two Cities")
            .build()
            .execute();

    assertThat(books.size(), is(1));
    assertTrue(books.getFirst().matchesAuthor("Dickens"));
  }

  @Test
  public void searchesBeforeGivenYear() {
    List<Book> books = new BookSearchQueryBuilder(sampleCatalogue)
            .withDate2(1700)
            .build()
            .execute();

    assertThat(books.size(), is(1));
    assertTrue(books.getFirst().matchesAuthor("Shakespeare"));
  }

  @Test
  public void searchesAfterGivenYear() {
    List<Book> books = new BookSearchQueryBuilder(sampleCatalogue)
            .withDate1(1900)
            .build()
            .execute();

    assertThat(books.size(), is(1));
    assertTrue(books.getFirst().matchesAuthor("Golding"));
  }

  @Test
  public void searchesWithCombinationOfParameters() {
    List<Book> books = new BookSearchQueryBuilder(sampleCatalogue)
            .withName2("Dickens")
            .withDate2(1900)
            .build()
            .execute();

    assertThat(books.size(), is(1));
    assertTrue(books.getFirst().matchesAuthor("Charles Dickens"));
  }
}
