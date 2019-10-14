package is.hi.hbv501.bokamarkadur.bokamarkadur;

import is.hi.hbv501.bokamarkadur.bokamarkadur.Entities.Book;
import is.hi.hbv501.bokamarkadur.bokamarkadur.Services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
public class HomeController {

    private BookService bookService;
    @Autowired
    public HomeController(BookService bookService){this.bookService = bookService;}


    //Skilar home síðunni.
    //Model geymir gögnin okkar sem eiga að birtast.
    @RequestMapping("/")
    public String Home(Model model) {
        model.addAttribute("books", bookService.findAll()); //Binda listann af myndum við books taggið í módelinu
        return "Home";
    }

    @RequestMapping(value ="/addbookforsale", method = RequestMethod.POST)
    public String addBookForSale(@Valid Book book, BindingResult result, Model model) {
        if(result.hasErrors()) {
            //Gætum haft villuskilaboð hér - ens og model.addAttribute("error") - eitthvað svona.
            return "add-book-sale"; //Inni í gæsalöppum: HTML skrá.
        }
        bookService.save(book);
        model.addAttribute("books", bookService.findAll());
        return "Success";
    }

    @RequestMapping(value="/addbookforsale", method = RequestMethod.GET)
    public String addBookForSaleForm(Book book) {
        return "add-book-sale";
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

}
