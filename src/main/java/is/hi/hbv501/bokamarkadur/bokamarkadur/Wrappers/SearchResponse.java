package is.hi.hbv501.bokamarkadur.bokamarkadur.Wrappers;

import is.hi.hbv501.bokamarkadur.bokamarkadur.Entities.Book;

import java.util.List;

public class SearchResponse extends GenericResponse {
    private List<Book> books;

    public SearchResponse(){}

    public SearchResponse(List<Book> books) {
        this.books = books;
    }
    public SearchResponse(List<Book> books, String message, List<?> errors) {
        super(message, errors);
        this.books = books;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
