package is.hi.hbv501.bokamarkadur.bokamarkadur;

import is.hi.hbv501.bokamarkadur.bokamarkadur.Entities.Book;
import is.hi.hbv501.bokamarkadur.bokamarkadur.Services.BookService;
import is.hi.hbv501.bokamarkadur.bokamarkadur.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {

    private BookService bookService;
    private UserService userService;
    @Autowired
    public HomeController(BookService bookService, UserService userService){
        this.bookService = bookService;
        this.userService = userService;
    }

    /*
     * Returns the home (front) page.
     * Returns 10 newest books for sale & requested.
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String Home(Model model) {
        model.addAttribute("books", bookService.findNewestBooks());
        return "Home";
    }

}
