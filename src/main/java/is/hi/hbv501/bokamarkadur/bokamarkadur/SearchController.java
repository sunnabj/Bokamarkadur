package is.hi.hbv501.bokamarkadur.bokamarkadur;

//TODO: Þetta er useless controller

import is.hi.hbv501.bokamarkadur.bokamarkadur.Entities.Book;
import is.hi.hbv501.bokamarkadur.bokamarkadur.Entities.User;
import is.hi.hbv501.bokamarkadur.bokamarkadur.Services.BookService;
import is.hi.hbv501.bokamarkadur.bokamarkadur.Wrappers.SearchRequestWrapper;
import is.hi.hbv501.bokamarkadur.bokamarkadur.Wrappers.SearchResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Þessi klasi er ekki notaður
 */

@RestController
public class SearchController {

    private BookService bookService;
    @Autowired
    public SearchController(BookService bookService){
        this.bookService = bookService;
    }


    @RequestMapping(value= "/search", method = RequestMethod.GET)
    public ResponseEntity<SearchResponse> searchBook(
            @RequestParam(value = "search" ,required = false) String search,
            @RequestParam(value = "status", required = true) String status
    ){
        List<Book> books = bookService.findByAuthorOrTitle(status, search, search);

        return new ResponseEntity<>(new SearchResponse(books), HttpStatus.OK);
    }

    @RequestMapping(value= "/searchB", method = RequestMethod.GET)
    public List<Book> searchBookB(
            @RequestParam(value = "search", required = false) String search,
            @RequestParam(value = "status", required = true) String status
    ){
        return bookService.findByAuthorOrTitle(status, search, search);
    }


}
