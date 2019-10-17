package is.hi.hbv501.bokamarkadur.bokamarkadur;


import is.hi.hbv501.bokamarkadur.bokamarkadur.Entities.Book;
import is.hi.hbv501.bokamarkadur.bokamarkadur.Services.BookService;
import is.hi.hbv501.bokamarkadur.bokamarkadur.Services.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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

    @RequestMapping("/search")
    public String search() {
        return "search";
    }

    @RequestMapping(value= "/bookSearch", method = RequestMethod.POST)
    public String searchBook(@RequestParam(value = "search", required = false) String search, Model model){
        System.out.println("The Search object"+search);
        // Að hafa search inni í findByTitle þarf titillinn að vera nákvæmlega réttur.
        // Við myndum vilja hafa einhvers konar regex eða eitthvað.
        // Breytum inni í BookServiceImplementation - inni í findByTitle.
        List<Book> book = bookService.findByTitle(search);
        model.addAttribute("books", book);
        return "Home";
    }

}
