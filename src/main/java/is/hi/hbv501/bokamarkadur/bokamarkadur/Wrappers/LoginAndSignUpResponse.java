package is.hi.hbv501.bokamarkadur.bokamarkadur.Wrappers;

import is.hi.hbv501.bokamarkadur.bokamarkadur.Entities.User;

import java.util.List;

public class LoginAndSignUpResponse extends GenericResponse {

    private User user;

    public LoginAndSignUpResponse(User user){
        this.user = user;
    }

    public LoginAndSignUpResponse(User user, String message, List<?> errors) {
        super(message, errors);
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
