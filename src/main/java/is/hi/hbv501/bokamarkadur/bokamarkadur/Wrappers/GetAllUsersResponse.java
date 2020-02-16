package is.hi.hbv501.bokamarkadur.bokamarkadur.Wrappers;

import is.hi.hbv501.bokamarkadur.bokamarkadur.Entities.User;

import java.util.List;

public class GetAllUsersResponse extends GenericResponse {

    private List<User> users;

    public GetAllUsersResponse(List<User> users) {
        this.users = users;
    }

    public GetAllUsersResponse(List<User> users, String message, List<?> errors) {
        super(message, errors);
        this.users = users;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
