package is.hi.hbv501.bokamarkadur.bokamarkadur;

import is.hi.hbv501.bokamarkadur.bokamarkadur.Entities.User;
import is.hi.hbv501.bokamarkadur.bokamarkadur.Services.BookService;
import is.hi.hbv501.bokamarkadur.bokamarkadur.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class UserController {


    private UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    /*
    + viewProfilePage(id : long, model : Model) : String
    + addUserInfo(user : User, result : BindingResult, model : Model)
    + deleteUser(id : long, model : Model) : String
     */

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String usersGET(Model model){
        model.addAttribute("users", userService.findAll());
        return "users";
    }

    /*
     * Returns a form where a user can create a new user account.
     */
    @RequestMapping(value="/newAccount", method = RequestMethod.GET)
    public String newuserForm(User user) {
        return "new-account";
    }


    /*
     * Creates a new user in the database, based on what is written into the
     * new account form.
     * Returns a welcome page where the new user is addressed.
     */
    @RequestMapping(value ="/newAccount", method = RequestMethod.POST)
    public String addNewUser(@Valid User user, BindingResult result, Model model) {
        if(result.hasErrors()) {
            //Gætum haft villuskilaboð hér - ens og model.addAttribute("error") - eitthvað svona.
            return "new-account"; //Inni í gæsalöppum: HTML skrá.
        }
        System.out.println("Notandanafnið: " + user.username);
        // Checks if this username already exists. If not -> A new user is created, else not.
        User exists = userService.findByUsername(user.username);
        if (exists == null) {
            userService.save(user);
        }

        model.addAttribute("user", user); //Ekki?
        //model.addAttribute("books", bookService.findAll()); // Siggi - kannski því hann birtir forsíðuna aftur.
        return "welcome-user";
    }



}
