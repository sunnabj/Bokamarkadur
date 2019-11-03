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
     * TODO: Gera fyrir login þannig að náð sé í user-inn sem er skrifaður inn og
     * TODO: hann birtist.
     * TODO: Má geyma þar til eftir næsta stoðtíma (fer eitthvað í login þar).
     */

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginGET(User user){
        return "login";
    }

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
        return "redirect:/";
    }

    //Hægt að hafa eitthvað svona fall ef við erum með mypage - þá nær hann í núverandi logged in notanda.
    // Fall úr stoðtíma
    /*
    @RequestMapping(value = "/loggedin", method = RequestMethod.GET)
    public String loggedinGET(HttpSession session, Model model){
        model.addAttribute("books",bookService.findAll());
        //getAttribute skilar annað hvort attribute-inu fyrir þennan lykil eða null.
        // ef enginn loggaður inn => Skilar null.
        User sessionUser = (User) session.getAttribute("LoggedInUser");
        if(sessionUser  != null){
            model.addAttribute("loggedinuser", sessionUser);
            return "loggedInUser";
        }
        return "redirect:/";
    }
    */


}
