package is.hi.hbv501.bokamarkadur.bokamarkadur.Wrappers;

import java.util.List;

public class DeleteBookResponse extends GenericResponse {

    public DeleteBookResponse() {}

    public DeleteBookResponse(String message, List<?> errors) {
        super(message, errors);
    }
}
