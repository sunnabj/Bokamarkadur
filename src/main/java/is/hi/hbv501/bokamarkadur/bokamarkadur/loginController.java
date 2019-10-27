package is.hi.hbv501.bokamarkadur.bokamarkadur;

import is.hi.hbv501.bokamarkadur.bokamarkadur.Services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;

public class loginController {

    private LoginService loginservice;

    @Autowired
    public loginController(LoginService loginservice) {
        this.loginservice = loginservice;
    }


}
