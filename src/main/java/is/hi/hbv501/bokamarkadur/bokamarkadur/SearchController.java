package is.hi.hbv501.bokamarkadur.bokamarkadur;


import is.hi.hbv501.bokamarkadur.bokamarkadur.Services.BookService;
import is.hi.hbv501.bokamarkadur.bokamarkadur.Services.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SearchController {

    private SearchService searchService;
    @Autowired
    public SearchController(SearchService searchService){this.searchService = searchService;}

    @RequestMapping("/search")
    public String search() {
        return "search";
    }

}
