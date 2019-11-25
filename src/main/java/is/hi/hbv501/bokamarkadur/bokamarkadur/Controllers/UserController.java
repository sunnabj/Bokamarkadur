package is.hi.hbv501.bokamarkadur.bokamarkadur.Controllers;

import is.hi.hbv501.bokamarkadur.bokamarkadur.Entities.User;
import is.hi.hbv501.bokamarkadur.bokamarkadur.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
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
    public String newuserForm(User user, Model model, HttpSession session) {
        User sessionUser = (User) session.getAttribute("LoggedInUser");
        model.addAttribute("loggedIn", sessionUser);
        return "new-account";
    }


    /*
     * Creates a new user in the database, based on what is written into the
     * new account form.
     * Returns a welcome page where the new user is addressed.
     */
    @RequestMapping(value ="/newAccount", method = RequestMethod.POST)
    public String addNewUser(@Valid User user, BindingResult result, Model model, HttpSession session, Errors errors) {
        if(result.hasErrors()) {
            return "new-account";
        }
        // Checks if this username already exists. If not -> A new user is created, else not.
        User exists = userService.findByUsername(user.username);
        if (exists != null) {
            model.addAttribute("message", "Username already exist");
            return "new-account";
        }
        else if(!user.password.equals(user.retypePassword)) {
            model.addAttribute("message", "Confirm Password is not equal to Password");
            return "new-account";
        } else if (exists == null && user.password.equals(user.retypePassword) ) {
            userService.save(user);
        }



        model.addAttribute("user", user); //Ekki?
        User sessionUser = (User) session.getAttribute("LoggedInUser");
        model.addAttribute("loggedIn", sessionUser);
        //model.addAttribute("books", bookService.findAll()); // Maybe?
        return "welcome-user";
    }

    /*
     * Returns a page with information about a particular user.
     */
    @RequestMapping(value ="/viewuser/{username}", method = RequestMethod.GET)
    public String viewUser(@PathVariable("username") String username, Model model, HttpSession session) {
        User user = userService.findByUsername(username);//.orElseThrow(() -> new IllegalArgumentException("Invalid user ID"));
        model.addAttribute("user", user);
        User sessionUser = (User) session.getAttribute("LoggedInUser");
        model.addAttribute("loggedIn", sessionUser);
        return "user-info";
    }

    /*
     * Returns a form where the logged in user can update his profile
     * information.
     */
    @RequestMapping(value="/updateUserInfo", method = RequestMethod.GET)
    public String userInfoForm(User user, HttpSession session, Model model) {
        if ((User) session.getAttribute("LoggedInUser") == null) {
            return "please-log-in";
        }
        User sessionUser = (User) session.getAttribute("LoggedInUser");
        model.addAttribute("loggedIn", sessionUser);
        return "update-userinfo";
    }

    /*
     * Updates information about the current logged in user.
     */
    @RequestMapping(value ="/updateUserInfo", method = RequestMethod.POST)
    public String updateUserInfo(@Valid User user, BindingResult result, Model model, HttpSession session) {
        if(result.hasErrors()) {
            return "update-userinfo";
        }
        User sessionUser = (User) session.getAttribute("LoggedInUser");
        User current = userService.findByUsername(sessionUser.getUsername());
        current.setInfo(user.info);
        userService.save(current);
        model.addAttribute("user", current);
        model.addAttribute("loggedIn", sessionUser);
        return "user-info";
    }

}
