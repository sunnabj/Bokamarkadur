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

@RestController
public class SearchController {

    private BookService bookService;
    @Autowired
    public SearchController(BookService bookService){
        this.bookService = bookService;
    }

    /*
     * Returns search results.
     * The user inserts a search string and chooses whether to search for books for sale
     * or requested books. Returns search results for either title or author.
     */
    /*
    * Alls ekki viss með þetta...
    * /search?search=Kafteinn+ofrubrok&status=for+sale
     */
    /*
    * Hmmmmm, hvernig skal gera þessa search?
     */

    /*
    * Sleppa searchController!
     */

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


    /*
     * Returns a page where the user can search for books.
     */
    /*
    Sleppi slepp

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String search(Model model, HttpSession session) {
        User sessionUser = (User) session.getAttribute("LoggedInUser");
        model.addAttribute("loggedIn", sessionUser);
        return "search";
    }

     */

}
