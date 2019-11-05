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
    // Some functions to be added later on.
    + viewProfilePage(id : long, model : Model) : String
    + addUserInfo(user : User, result : BindingResult, model : Model)
    + deleteUser(id : long, model : Model) : String
     */

    /*
     * Returns all users in the database, that is those who have created
     * an account through the site.
     * Used in development. Won't be in the final product (probably).
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
            return "new-account";
        }
        // Checks if this username already exists. If not -> A new user is created, else not.
        User exists = userService.findByUsername(user.username);
        if (exists == null) {
            userService.save(user);
        }

        model.addAttribute("user", user); //Ekki?
        //model.addAttribute("books", bookService.findAll()); // Maybe?
        return "welcome-user";
    }

    /*
     * Returns a page with information about a particular user.
     */
    @RequestMapping(value ="/viewuser/{username}", method = RequestMethod.GET)
    public String viewUser(@PathVariable("username") String username, Model model) {
        User user = userService.findByUsername(username);//.orElseThrow(() -> new IllegalArgumentException("Invalid user ID"));
        model.addAttribute("user", user);
        return "user-info";
    }



    @RequestMapping(value="/updateUserInfo", method = RequestMethod.GET)
    public String userInfoForm(User user, HttpSession session) {
        if ((User) session.getAttribute("LoggedInUser") == null) {
            return "please-log-in";
        }
        return "update-userinfo";
    }

    /*
     * Hvernig á að græja þetta???
     */
    @RequestMapping(value ="/updateUserInfo", method = RequestMethod.POST)
    public String updateUserInfo(@Valid User user, BindingResult result, Model model, HttpSession session) {
        if(result.hasErrors()) {
            return "update-userinfo";
        }
        User sessionUser = (User) session.getAttribute("LoggedInUser");
        System.out.println("INFOO: " + user.info);
        model.addAttribute("user", sessionUser);
        sessionUser.setInfo(user.info);
        return "users";
    }



}
