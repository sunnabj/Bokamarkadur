package is.hi.hbv501.bokamarkadur.bokamarkadur;

import is.hi.hbv501.bokamarkadur.bokamarkadur.Entities.User;
import is.hi.hbv501.bokamarkadur.bokamarkadur.Repositories.UserRepository;
import is.hi.hbv501.bokamarkadur.bokamarkadur.Services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class loginController {

    private LoginService loginservice;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    public loginController(LoginService loginservice) {
        this.loginservice = loginservice;
    }


    @RequestMapping(value="/loginform", method = RequestMethod.POST)
    public String loginform(@ModelAttribute(name="loginform")User loginForm, Model model) {

        String username = loginForm.getUsername();
        String password = loginForm.getPassword();
        if("hieu".equals(username) && "123".equals(password)) {
            return "Home";
        }

        model.addAttribute("invalidCredentials", true);
        return "login";
    }


}
