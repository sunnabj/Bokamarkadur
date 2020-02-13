package is.hi.hbv501.bokamarkadur.bokamarkadur;


import is.hi.hbv501.bokamarkadur.bokamarkadur.Entities.Book;
import is.hi.hbv501.bokamarkadur.bokamarkadur.Entities.User;
import is.hi.hbv501.bokamarkadur.bokamarkadur.Services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    /*
     * Returns search results.
     * The user inserts a search string and chooses whether to search for books for sale
     * or requested books. Returns search results for either title or author.
     */
    @RequestMapping(value= "/search", method = RequestMethod.POST)
    public List<Book> searchBook(
            @RequestParam(value = "search", required = false) String search,
            @RequestParam(value = "status", required = true) String status,
            Model model, HttpSession session
    ){
        List<Book> books = bookService.findByAuthorOrTitle(status, search, search);

        return books;
    }

}
