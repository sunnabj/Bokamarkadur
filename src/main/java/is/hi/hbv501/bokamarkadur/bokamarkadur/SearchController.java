package is.hi.hbv501.bokamarkadur.bokamarkadur;


import is.hi.hbv501.bokamarkadur.bokamarkadur.Entities.Book;
import is.hi.hbv501.bokamarkadur.bokamarkadur.Entities.User;
import is.hi.hbv501.bokamarkadur.bokamarkadur.Services.BookService;
import is.hi.hbv501.bokamarkadur.bokamarkadur.Services.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class SearchController {

    private SearchService searchService;
    private BookService bookService;
    @Autowired
    public SearchController(SearchService searchService, BookService bookService){
        this.searchService = searchService;
        this.bookService = bookService;
    }

    /*
     * Returns a page where the user can search for books.
     */
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String search() {
        return "search";
    }

    /*
     * Returns search results.
     * The user inserts a search string and chooses whether to search for books for sale
     * or requested books. Returns search results for either title or author.
     */
    @RequestMapping(value= "/search", method = RequestMethod.POST)
    public String searchBook(
            @RequestParam(value = "search", required = false) String search,
            @RequestParam(value = "status", required = true) String status,
            Model model
    ){
        List<Book> book = bookService.findByAuthorOrTitle(status, search, search);

        model.addAttribute("search", search);
        model.addAttribute("books", book);

        return "search";
    }

}
