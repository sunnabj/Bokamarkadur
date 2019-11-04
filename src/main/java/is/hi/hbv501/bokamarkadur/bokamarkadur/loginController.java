package is.hi.hbv501.bokamarkadur.bokamarkadur;

import is.hi.hbv501.bokamarkadur.bokamarkadur.Entities.User;
import is.hi.hbv501.bokamarkadur.bokamarkadur.Services.BookService;
import is.hi.hbv501.bokamarkadur.bokamarkadur.Services.LoginService;
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
public class loginController {

    /*
     * The login functionality is still in progress. The basic login functions have just been
     * implemented, all connected functions, such as only letting logged in users add books,
     * showing which user added a particular book, and letting users update their information
     * for others to view.
     */

    private LoginService loginService;
    private UserService userService;
    private BookService bookService;

    @Autowired
    public loginController(LoginService loginService, UserService userService, BookService bookService) {
        this.loginService = loginService;
        this.bookService = bookService;
        this.userService = userService;
    }

    /*
     * Returns the page with the login form, where a user can sign in.
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginGET(User user){
        return "login";
    }

    /*
     * Logs the user in. Activates when the user presses the login button
     * after having inserted his username and password.
     * Checks if the username exists and if the password fits, and if so,
     * the user is logged in and redirected to the frontpage.
     * If not, the login page is reloaded.
     * The logged in user is stored in the current session.
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginPOST(@Valid User user, BindingResult result, Model model, HttpSession session){
        if(result.hasErrors()){
            return "login";
        }
        model.addAttribute("books", bookService.findAll());
        User exists = userService.login(user);
        if(exists != null){
            session.setAttribute("LoggedInUser", user);
            return "redirect:/";
        }
        return "login";
    }

    /*
     * Retrieves the current logged in user from the current session.
     */
    @RequestMapping(value = "/loggedin", method = RequestMethod.GET)
    public String loggedinGET(HttpSession session, Model model){
        model.addAttribute("books",bookService.findAll());
        User sessionUser = (User) session.getAttribute("LoggedInUser");
        if(sessionUser  != null){
            model.addAttribute("loggedinuser", sessionUser);
            return "loggedInUser";
        }
        return "redirect:/";
    }



}
