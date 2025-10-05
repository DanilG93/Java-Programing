package Service.Impl;

import Model.Author;
import Model.Book;
import Model.Publisher;
import Service.BookService;

import java.util.Arrays;

public class BookServiceImpl implements BookService {


    @Override
    public Book[] filterBooksByAuthor(Author author, Book[] books) {

        return Arrays.stream(books)
                .filter(book -> Arrays.stream(book.getAuthors())
                        .anyMatch(a -> a.equals(author)))
                .toArray(Book[]::new);

    }

    @Override
    public Book[] filterBooksByPublisher(Publisher publisher, Book[] books) {
        return Arrays.stream(books)
                .filter(book -> book.getPublisher().equals(publisher))
                .toArray(Book[]::new);
    }

    @Override
    public Book[] filterBooksAfterSpecifiedYear(int yearFromInclusively, Book[] books) {
        return Arrays.stream(books)
                .filter(book -> book.getPublishingYear() > yearFromInclusively)
                .toArray(Book[]::new);
    }
}
