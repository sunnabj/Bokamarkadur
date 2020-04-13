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


    // Skilar notandanum sem er loggaður inn þá stundina.
    @RequestMapping(value = "/loggedIn", method = RequestMethod.GET)
    public User loggedInGET(Authentication authentication) {
        System.out.println(authentication.getName());
        return userService.findByUsername(authentication.getName());
    }


}
