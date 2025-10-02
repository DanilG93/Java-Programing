package Service;

import Model.Author;
import Model.Book;
import Model.Publisher;

public interface BookService {

    public Book[] filterBooksByAuthor(Author author, Book[] books);
    public Book[] filterBooksByPublisher(Publisher publisher, Book[] books);
    public Book[] filterBooksAfterSpecifiedYear(int yearFromInclusively, Book[] books);


}
