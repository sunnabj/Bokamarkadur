package is.hi.hbv501.bokamarkadur.bokamarkadur;

import is.hi.hbv501.bokamarkadur.bokamarkadur.Entities.Book;
import is.hi.hbv501.bokamarkadur.bokamarkadur.Entities.User;
import is.hi.hbv501.bokamarkadur.bokamarkadur.Services.BookService;
import is.hi.hbv501.bokamarkadur.bokamarkadur.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

@Controller
public class HomeController {

    private BookService bookService;
    private UserService userService;
    @Autowired
    public HomeController(BookService bookService, UserService userService){
        this.bookService = bookService;
        this.userService = userService;
    }


    //Skilar home síðunni.
    //Model geymir gögnin okkar sem eiga að birtast.
    @RequestMapping("/")
    public String Home(Model model) {
        model.addAttribute("books", bookService.findAll()); //Binda listann af myndum við books taggið í módelinu
        return "Home";
    }


    @RequestMapping(value="/newbook", method = RequestMethod.GET)
    public String addBook() {
        return "add-book";
    }

    @RequestMapping(value ="/addbookforsale", method = RequestMethod.POST)
    public String addBookForSale(@Valid Book book, BindingResult result, Model model) {
        if(result.hasErrors()) {
            //Gætum haft villuskilaboð hér - ens og model.addAttribute("error") - eitthvað svona.
            return "sell-book"; //Inni í gæsalöppum: HTML skrá.
        }
        book.setStatus("For sale");
        bookService.save(book);
        model.addAttribute("books", bookService.findAll());
        return "Success";
    }

    @RequestMapping(value ="/addrequestbook", method = RequestMethod.POST)
    public String addRequestBook(@Valid Book book, BindingResult result, Model model) {
        if(result.hasErrors()) {
            //Gætum haft villuskilaboð hér - ens og model.addAttribute("error") - eitthvað svona.
            return "request-book"; //Inni í gæsalöppum: HTML skrá.
        }
        book.setStatus("Requested");
        bookService.save(book);
        model.addAttribute("books", bookService.findAll());
        return "Success";
    }

    @RequestMapping(value="/addbookforsale", method = RequestMethod.GET)
    public String addBookForSaleForm(Book book) {
        return "sell-book";
    }

    @RequestMapping(value="/addrequestbook", method = RequestMethod.GET)
    public String addRequestBook(Book book) {
        return "request-book";
    }

    // id inni í {} - þýðir að þetta er variable.
    @RequestMapping(value="/delete/{id}", method = RequestMethod.GET)
    public String deleteBook(@PathVariable("id") long id, Model model) {
        //Reynir að sækja book með þetta id í gagnagrunninn - ef ekki til - kastar villu
        Book book = bookService.findById(id).orElseThrow(()-> new IllegalArgumentException("Invalid book ID"));
        //Sækja bók, eyða - sækja listann eftir það.
        bookService.delete(book);
        model.addAttribute("books", bookService.findAll());
        return "Home";
    }

    @RequestMapping(value ="/viewbook/{id}", method = RequestMethod.GET)
    public String viewBook(@PathVariable("id") long id, Model model) {
       Book book = bookService.findById(id).orElseThrow(()-> new IllegalArgumentException("Invalid book ID"));
       model.addAttribute("book", book);
       return "book-info";
    }


    @RequestMapping(value="/loginform", method = RequestMethod.GET)
    public String loginForm() {
        return "login";
    }

    /*
     * Skilar formi þar sem maður getur búið til nýtt account.
     */
    @RequestMapping(value="/newAccount", method = RequestMethod.GET)
    public String newuserForm(User user) {
        return "new-account";
    }

    /*
     * Býr til nýjan notanda út frá því sem skrifað er inn í new account formið.
     * Birtir welcome síðu þar sem notandi er ávarpaður.
     */
    @RequestMapping(value ="/newAccount", method = RequestMethod.POST)
    public String addNewUser(@Valid User user, BindingResult result, Model model) {
        if(result.hasErrors()) {
            //Gætum haft villuskilaboð hér - ens og model.addAttribute("error") - eitthvað svona.
            return "new-account"; //Inni í gæsalöppum: HTML skrá.
        }
        userService.save(user);
        model.addAttribute("user", user);
        return "welcome-user";
    }



    // H2 gagnagrunnur - gögnin eyðast alltaf út þegar serverinn er endurræstur.
    //

}
