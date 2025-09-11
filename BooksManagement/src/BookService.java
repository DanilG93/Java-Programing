import java.util.ArrayList;

public class BookService {

    public Book[] filterBooksByAuthor(Author author, Book[] books) {
        if (author == null || books == null) {
            return new Book[0];
        }

        ArrayList<Book> bookByAuthors = new ArrayList<>();

        for (Book book : books) {
            Author[] bookAuthors = book.getAuthors();
            if (bookAuthors != null) {
                for (Author bookAuthor : bookAuthors) {
                    if (bookAuthor.equals(author)) {
                        bookByAuthors.add(book);
                        break; // dodaj samo jednom ako je autor pronađen
                    }
                }
            }
        }

        return bookByAuthors.toArray(new Book[0]);
    }

    public Book[] filterBooksByPublisher(Publisher publisher, Book[] books) {
        if (publisher == null || books == null) {
            return new Book[0];
        }

        ArrayList<Book> bookByPublisher = new ArrayList<>();

        for (Book book : books) {
            if (book.getPublisher() != null && book.getPublisher().equals(publisher)) {
                bookByPublisher.add(book);
            }
        }

        return bookByPublisher.toArray(new Book[0]);
    }

    public Book[] filterBooksAfterSpecifiedYear(int yearFromInclusively, Book[] books) {
        if (books == null) {
            return new Book[0];
        }

        ArrayList<Book> bookByYearFromInclusively = new ArrayList<>();

        for (Book book : books) {
            if (book.getPublishingYear() >= yearFromInclusively) {
                bookByYearFromInclusively.add(book);
            }
        }

        return bookByYearFromInclusively.toArray(new Book[0]);
    }
}
