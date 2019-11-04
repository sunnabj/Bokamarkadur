package is.hi.hbv501.bokamarkadur.bokamarkadur;

import is.hi.hbv501.bokamarkadur.bokamarkadur.Services.BookService;
import is.hi.hbv501.bokamarkadur.bokamarkadur.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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
     * Will probably be deleted in the future.
     */
    @RequestMapping("/")
    public String Home(Model model) {
        return "Home";
    }




}
