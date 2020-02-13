package is.hi.hbv501.bokamarkadur.bokamarkadur;

import is.hi.hbv501.bokamarkadur.bokamarkadur.Entities.User;
import is.hi.hbv501.bokamarkadur.bokamarkadur.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@RestController
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    /*
     * Returns all users in the database, that is those who have created
     * an account through the site.
     * Used in development. Not intended to be reachable in a released product.
     */
    /*
    Sleppa

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String usersGET(Model model){
        model.addAttribute("users", userService.findAll());
        return "users";
    }

     */

    /*
     * Returns a form where a user can create a new user account.
     */
    /*
    Sleppelsi

    @RequestMapping(value="/newAccount", method = RequestMethod.GET)
    public String newuserForm(User user, Model model, HttpSession session) {
        User sessionUser = (User) session.getAttribute("LoggedInUser");
        model.addAttribute("loggedIn", sessionUser);
        return "new-account";
    }

     */


    /*
     * Creates a new user in the database, based on what is written into the
     * new account form.
     * Returns a welcome page where the new user is addressed.
     */
    @RequestMapping(value ="/newAccount", method = RequestMethod.POST)
    public User addNewUser(@Valid User user, BindingResult result, Model model, HttpSession session, Errors errors) {
        if(result.hasErrors()) {
            //return Villu-object
        }
        // Checks if this username already exists. If not -> A new user is created, else not.
        User exists = userService.findByUsername(user.username);
        if (exists != null) {
            //model.addAttribute("message", "Username already exist");
            //return "new-account";
            //return villuskilaboða-Object
        }
        // Checks if the user retypes its password correctly
        // If so - the password is hashed for security reasons, and the user is saved to the database.
        else if(!user.password.equals(user.retypePassword)) {
            //model.addAttribute("message", "Confirm Password is not equal to Password");
            //return "new-account";
            //return villuskilaboða-Object
        } else if (exists == null && user.password.equals(user.retypePassword) ) {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String hashedPassword = passwordEncoder.encode(user.password);
            user.password = hashedPassword;
            userService.save(user);
        }

        return user;
    }

    /*
     * Returns a page with information about a particular user.
     */
    /*
     * Nope - sleppedíslepp
    @RequestMapping(value ="/viewuser/{username}", method = RequestMethod.GET)
    public String viewUser(@PathVariable("username") String username, Model model, HttpSession session) {
        User user = userService.findByUsername(username);//.orElseThrow(() -> new IllegalArgumentException("Invalid user ID"));
        model.addAttribute("user", user);
        User sessionUser = (User) session.getAttribute("LoggedInUser");
        model.addAttribute("loggedIn", sessionUser);
        return "user-info";
    }

     */

    /*
     * Returns a form where the logged in user can update his profile
     * information.
     */
    /*
    Sleppísleppí

    @RequestMapping(value="/updateUserInfo", method = RequestMethod.GET)
    public String userInfoForm(User user, HttpSession session, Model model) {
        if ((User) session.getAttribute("LoggedInUser") == null) {
            return "please-log-in";
        }
        User sessionUser = (User) session.getAttribute("LoggedInUser");
        model.addAttribute("loggedIn", sessionUser);
        return "update-userinfo";
    }

     */

    /*
     * Updates information about the current logged in user.
     */
    @RequestMapping(value ="/updateUserInfo", method = RequestMethod.POST)
    public User updateUserInfo(@Valid User user, BindingResult result, Model model, HttpSession session) {
        if(result.hasErrors()) {
            //return eitthvað villu-object
        }
        User sessionUser = (User) session.getAttribute("LoggedInUser");
        User current = userService.findByUsername(sessionUser.getUsername());
        current.setInfo(user.info);
        userService.save(current);
        return current;
    }






}
