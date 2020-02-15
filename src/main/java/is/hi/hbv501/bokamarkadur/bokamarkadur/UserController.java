package is.hi.hbv501.bokamarkadur.bokamarkadur;

import is.hi.hbv501.bokamarkadur.bokamarkadur.Entities.User;
import is.hi.hbv501.bokamarkadur.bokamarkadur.Services.UserService;
import is.hi.hbv501.bokamarkadur.bokamarkadur.Wrappers.LoginAndSignUpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

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
    public ResponseEntity<LoginAndSignUpResponse> addNewUser(@Valid @RequestBody User user, BindingResult result) {
        if(result.hasErrors()) {
            return new ResponseEntity<>(new LoginAndSignUpResponse(user, null, result.getFieldErrors()), HttpStatus.BAD_REQUEST);
        }
        // Checks if this username already exists. If not -> A new user is created, else not.
        User exists = userService.findByUsername(user.username);
        if (exists != null) {
            List<String> errors = new ArrayList<>();
            errors.add("Username already exists");
            return new ResponseEntity<>(new LoginAndSignUpResponse(user, null, errors), HttpStatus.BAD_REQUEST);
        }
        // Checks if the user retypes its password correctly
        // If so - the password is hashed for security reasons, and the user is saved to the database.
        else if(!user.password.equals(user.retypePassword)) {
            List<String> errors = new ArrayList<>();
            errors.add("Confirm Password is not equal to Password");
            return new ResponseEntity<>(new LoginAndSignUpResponse(user, null, errors), HttpStatus.BAD_REQUEST);
        } else if (exists == null && user.password.equals(user.retypePassword) ) {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String hashedPassword = passwordEncoder.encode(user.password);
            user.password = hashedPassword;
            userService.save(user);
        }

        return new ResponseEntity<>(new LoginAndSignUpResponse(user, "User created successfully", null), HttpStatus.CREATED);

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
    /*
    * Græja þetta!
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
