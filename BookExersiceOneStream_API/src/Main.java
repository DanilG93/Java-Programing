import Model.Author;
import Model.Book;
import Model.Enum.CoverType;
import Model.Publisher;
import Service.BookService;
import Service.Impl.BookServiceImpl;

import java.math.BigDecimal;

public class Main {

    private static final BookService bookService;

    static {
        bookService = new BookServiceImpl();
    }

    public static void main(String[] args) {

        Book[] books = new Book[]{
                new Book(1, "Book_1", new Author[]{new Author(1, "Jon", "Johnson")}, new Publisher(1, "Publisher_1"), 1990, 231, BigDecimal.valueOf(24.99), CoverType.PAPERBACK),
                new Book(2, "Book_2", new Author[]{new Author(1, "Jon", "Johnson"), new Author(2, "William", "Wilson")}, new Publisher(2, "Publisher_2"), 2000, 120, BigDecimal.valueOf(14.99), CoverType.PAPERBACK),
                new Book(3, "Book_3", new Author[]{new Author(3, "Walter", "Peterson")}, new Publisher(1, "Publisher_1"), 1997, 350, BigDecimal.valueOf(34.99), CoverType.HARDCOVER),
                new Book(4, "Book_4", new Author[]{new Author(4, "Craig", "Gregory")}, new Publisher(3, "Publisher_3"), 1992, 185, BigDecimal.valueOf(19.99), CoverType.PAPERBACK)
        };



        Author authorJon = new Author(1, "Jon", "Johnson");
        Book[] books_filterBooksByAuthor = bookService.filterBooksByAuthor(authorJon, books);

        Publisher publisher1 = new Publisher(1, "Publisher_1");
        Book[] books_filterBooksByPublisher = bookService.filterBooksByPublisher(publisher1, books);

        Book[] books_filterBooksAfterSpecifiedYear = bookService.filterBooksAfterSpecifiedYear(1997, books);

        // test find author Jon
        for (Book book : books_filterBooksByAuthor) {
            System.out.println(book);

        }


        System.out.println();
        System.out.println("-------------------------------");
        System.out.println();

        // find by Publisher
        for (Book book : books_filterBooksByPublisher) {
            System.out.println(book);
        }

        System.out.println();
        System.out.println("-------------------------------");
        System.out.println();

        // find by Year
        for (Book book : books_filterBooksAfterSpecifiedYear) {
            System.out.println(book);
        }


    }
}