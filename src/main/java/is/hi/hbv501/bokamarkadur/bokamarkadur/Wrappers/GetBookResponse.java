package is.hi.hbv501.bokamarkadur.bokamarkadur.Wrappers;

import is.hi.hbv501.bokamarkadur.bokamarkadur.Entities.Book;

import java.util.List;
import java.util.Optional;

public class GetBookResponse extends GenericResponse {

    private Book book;
    private Optional<Book> bookOptional;

    public GetBookResponse(Book book) {
        this.book = book;
    }

    public GetBookResponse(Book book, String message, List<?> errors) {
        super(message, errors);
        this.book = book;
    }

    public GetBookResponse(Optional<Book> book) {
        this.bookOptional = book;
    }


    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Optional<Book> getBookOptional() {
        return bookOptional;
    }

    public void setBookOptional(Optional<Book> bookOptional) {
        this.bookOptional = bookOptional;
    }
}
