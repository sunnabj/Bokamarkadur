package is.hi.hbv501.bokamarkadur.bokamarkadur;

import is.hi.hbv501.bokamarkadur.bokamarkadur.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;

public class UserController {


    private UserService userService;
    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    /*
    + viewProfilePage(id : long, model : Model) : String
    + addUserInfo(user : User, result : BindingResult, model : Model)
    + deleteUser(id : long, model : Model) : String
     */

}
