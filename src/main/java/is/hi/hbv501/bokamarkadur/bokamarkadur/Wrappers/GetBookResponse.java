package is.hi.hbv501.bokamarkadur.bokamarkadur.Wrappers;

import is.hi.hbv501.bokamarkadur.bokamarkadur.Entities.Book;

import java.util.List;

public class GetBookResponse extends GenericResponse {

    private Book book;

    public GetBookResponse(Book book) {
        this.book = book;
    }

    public GetBookResponse(Book book, String message, List<?> errors) {
        super(message, errors);
        this.book = book;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
