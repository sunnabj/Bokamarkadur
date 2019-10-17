package is.hi.hbv501.bokamarkadur.bokamarkadur;

import is.hi.hbv501.bokamarkadur.bokamarkadur.Entities.Book;
import is.hi.hbv501.bokamarkadur.bokamarkadur.Services.BookService;
import is.hi.hbv501.bokamarkadur.bokamarkadur.Services.RentalLogService;
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
    // Ættum að setja þetta í spes rentalLogController og userController.
    private RentalLogService rentalLogService;
    private UserService userService;
    @Autowired
    public HomeController(BookService bookService, RentalLogService rentalLogService, UserService userService){
        this.bookService = bookService;
        this.userService = userService;
        this.rentalLogService = rentalLogService;
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
        bookService.save(book);
        model.addAttribute("books", bookService.findAll());
        return "Success";
    }

    //Setja inn í addbookforsale eitthvað varðandi genres
    // Tengja Genres við model.addAttributes

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


    //Gera samskonar og addbook fyrir create new user - þ.e. POST aðferð

    @RequestMapping(value="/loginform", method = RequestMethod.GET)
    public String loginForm() {
        return "login";
    }

    @RequestMapping(value="/newAccount", method = RequestMethod.GET)
    public String newuserForm() {
        return "new-account";
    }


    // H2 gagnagrunnur - gögnin eyðast alltaf út þegar serverinn er endurræstur.
    //

    @RequestMapping("/rentals")
    public String allRentals(Model model) {
        model.addAttribute("rentalLog", rentalLogService.findAll());
        return "rentals";
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
