package is.hi.hbv501.bokamarkadur.bokamarkadur;

import is.hi.hbv501.bokamarkadur.bokamarkadur.Entities.Book;
import is.hi.hbv501.bokamarkadur.bokamarkadur.Services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

public class BookController {

    private BookService bookService;


    @Autowired
    public BookController(BookService bookService){
        this.bookService = bookService;
    }

    // Það virkar ekki að hafa þetta hérna inni í staðinn fyrir HomeController - veit ekki af hverju!
/*
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


    @RequestMapping(value="/addbookforsale", method = RequestMethod.GET)
    public String addBookForSaleForm(Book book) {
        return "sell-book";
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

    @RequestMapping(value="/addrequestbook", method = RequestMethod.GET)
    public String addRequestBook(Book book) {
        return "request-book";
    }

*/
}
