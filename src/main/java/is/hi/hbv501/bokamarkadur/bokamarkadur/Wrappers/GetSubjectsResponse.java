package is.hi.hbv501.bokamarkadur.bokamarkadur.Wrappers;

import is.hi.hbv501.bokamarkadur.bokamarkadur.Entities.Subjects;
import org.springframework.validation.ObjectError;

import java.util.List;

public class GetSubjectsResponse extends GenericResponse {

    private List<Subjects> subjects;

    public GetSubjectsResponse(List<Subjects> subjects) {
        this.subjects = subjects;
    }

    public GetSubjectsResponse(List<Subjects> subjects, String message, List<ObjectError> errors) {
        super(message, errors);
        this.subjects = subjects;
    }

    public List<Subjects> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subjects> subjects) {
        this.subjects = subjects;
    }

}
