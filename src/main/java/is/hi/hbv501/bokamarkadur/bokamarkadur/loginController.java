package is.hi.hbv501.bokamarkadur.bokamarkadur;

import is.hi.hbv501.bokamarkadur.bokamarkadur.Entities.User;
import is.hi.hbv501.bokamarkadur.bokamarkadur.Services.BookService;
import is.hi.hbv501.bokamarkadur.bokamarkadur.Services.UserService;
import is.hi.hbv501.bokamarkadur.bokamarkadur.Wrappers.GenericResponse;
import is.hi.hbv501.bokamarkadur.bokamarkadur.Wrappers.GetUserResponse;
import is.hi.hbv501.bokamarkadur.bokamarkadur.Wrappers.LoginAndSignUpResponse;
//import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
public class loginController {

    /*
     * Handles the login and logout functions of the system.
     */

    private UserService userService;
    private BookService bookService;

    @Autowired
    public loginController(UserService userService, BookService bookService) {
        this.bookService = bookService;
        this.userService = userService;
    }

    /*
     * Logs the user in. Activates when the user presses the login button
     * after having inserted his username and password.
     * Checks if the username exists and if the password fits, and if so,
     * the user is logged in and redirected to the frontpage.
     * If not, the login page is reloaded.
     * The logged in user is stored in the current session.
     */
    // EKKI NOTA
    /*
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<LoginAndSignUpResponse> loginPOST(@Valid @RequestBody User user, BindingResult result,
                                                            HttpSession session){
        if(result.hasErrors()){
            return new ResponseEntity<>(new LoginAndSignUpResponse(user, null, result.getFieldErrors()),
                    HttpStatus.BAD_REQUEST);
        }
        User exists = userService.login(user);
        if(exists != null){
            session.setAttribute("LoggedInUser", user);
            return new ResponseEntity<>(new LoginAndSignUpResponse(user, "Login successful", null)
                    , HttpStatus.OK);
        }
        List<String> errors = new ArrayList<>();
        errors.add("Login unsuccessful");
        return new ResponseEntity<>(new LoginAndSignUpResponse(user, null, errors),HttpStatus.BAD_REQUEST);
    }

     */

    /*
     * Retrieves the current logged in user from the current session.
     */
    // EKKI NOTA
    /*
    @RequestMapping(value = "/loggedin", method = RequestMethod.GET)
    public ResponseEntity<GetUserResponse> loggedinGET(HttpSession session){
        User sessionUser = (User) session.getAttribute("LoggedInUser");
        if(sessionUser  != null){
            return new ResponseEntity<>(new GetUserResponse(sessionUser), HttpStatus.OK);
        }
        List<String> errors = new ArrayList<>();
        errors.add("You must be logged in to visit this page");
        return new ResponseEntity<>(new GetUserResponse(null, null, errors ), HttpStatus.UNAUTHORIZED);
    }

     */

    // NÝTT!!!
    @RequestMapping(value = "/loggedIn", method = RequestMethod.GET)
    public User loggedInGET(Authentication authentication) {
        System.out.println(authentication.getName());
        return userService.findByUsername(authentication.getName()); //var getName() og rautt
    }

    /*
     * Logs the current user out.
     */
    /*
    * Spurning með þetta - Má sleppa þessu? Þarf þetta setAttribute?
     */
   /*
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public ResponseEntity<LoginAndSignUpResponse> logout(BindingResult result) {

        if(result.hasErrors()){
            return new ResponseEntity<>(new LoginAndSignUpResponse(null, result.getFieldErrors()), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(new LoginAndSignUpResponse("Logout successful", null), HttpStatus.OK);

    }
   */

    /*
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public ResponseEntity<LoginAndSignUpResponse> logout(BindingResult result, HttpSession session){

        User sessionUser = (User) session.getAttribute("LoggedInUser");

        if(result.hasErrors()){
            return new ResponseEntity<>(new LoginAndSignUpResponse(sessionUser, null, result.getFieldErrors()), HttpStatus.BAD_REQUEST);
        }

        session.setAttribute("LoggedInUser", null);
        return new ResponseEntity<>(new LoginAndSignUpResponse(sessionUser, "Logout successful", null), HttpStatus.OK);
    }

     */

    /*
     * Returns the page with the login form, where a user can sign in.
     */
    /*
    Burt með þetta

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginGET(User user){
        return "login";
    }

     */

}
