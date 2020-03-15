package is.hi.hbv501.bokamarkadur.bokamarkadur;

import is.hi.hbv501.bokamarkadur.bokamarkadur.Entities.Review;
import is.hi.hbv501.bokamarkadur.bokamarkadur.Entities.User;
import is.hi.hbv501.bokamarkadur.bokamarkadur.Services.ReviewService;
import is.hi.hbv501.bokamarkadur.bokamarkadur.Services.UserService;
import is.hi.hbv501.bokamarkadur.bokamarkadur.Wrappers.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
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
    private ReviewService reviewService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    /*
     * Returns all users in the database, that is those who have created
     * an account through the site.
     * Used in development. Not intended to be reachable in a released product.
     */
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public ResponseEntity<GetAllUsersResponse> usersGET(){
        return new ResponseEntity<>(new GetAllUsersResponse(userService.findAll()), HttpStatus.OK);
    }

    /*
     * Creates a new user in the database, based on what is written into the
     * new account form.
     * Returns a welcome page where the new user is addressed.
     */
    // EKKI NOTA
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
    @RequestMapping(value ="/viewuser/{username}", method = RequestMethod.GET)
    public ResponseEntity<GetUserResponse> viewUser(@PathVariable("username") String username) {
        User user = userService.findByUsername(username);//.orElseThrow(() -> new IllegalArgumentException("Invalid user ID"));
        //TODO: Græja villuresponse
        return new ResponseEntity<>(new GetUserResponse(user), HttpStatus.OK);
    }


    /*
     * Updates information about the current logged in user.
     */
    @RequestMapping(value ="/updateUserInfo", method = RequestMethod.POST)
    public ResponseEntity<GetUserResponse> updateUserInfo(@Valid @RequestBody User user, BindingResult result,
                                                          Authentication authentication) {
        if(result.hasErrors()) {
            return new ResponseEntity<>(new GetUserResponse(user, null, result.getFieldErrors()), HttpStatus.BAD_REQUEST);
        }
        User loggedinUser = userService.findByUsername(authentication.getName());
        //User sessionUser = (User) session.getAttribute("LoggedInUser");
        User current = userService.findByUsername(loggedinUser.getUsername());
        current.setInfo(user.info);
        userService.save(current);
        return new ResponseEntity<>(new GetUserResponse(current), HttpStatus.OK);
    }



    @RequestMapping(value ="/writeReview/{id}", method = RequestMethod.POST)
    public ResponseEntity<AddReviewResponse> writeReview(@PathVariable("id") long id,
                                                         @Valid @RequestBody Review review, Authentication authentication) {

        User user = userService.findById(id).orElseThrow(()-> new IllegalArgumentException("Invalid user ID"));

        User loggedinUser = userService.findByUsername(authentication.getName());
        User current = userService.findByUsername(loggedinUser.getUsername());

        review.setReviewer(current);
        review.setUser(user);
        return new ResponseEntity<>(new AddReviewResponse(reviewService.save(review)), HttpStatus.CREATED);
    }


    @RequestMapping(value ="/viewreviews/{username}", method = RequestMethod.GET)
    public ResponseEntity<GetReviewsResponse> viewReviews(@PathVariable("username") String username) {
        User user = userService.findByUsername(username);//.orElseThrow(() -> new IllegalArgumentException("Invalid user ID"));
        //TODO: Græja villuresponse
        List<Review> reviews = reviewService.findByUser(user);
        return new ResponseEntity<>(new GetReviewsResponse(reviews), HttpStatus.OK);
    }




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



}
