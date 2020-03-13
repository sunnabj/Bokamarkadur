package is.hi.hbv501.bokamarkadur.bokamarkadur.Wrappers;

import org.springframework.validation.ObjectError;

import java.util.List;

public class GetSubjectResponse extends GenericResponse {


    private List<String> subjects;

    public GetSubjectResponse(List<String> subjects) {
        this.subjects = subjects;
    }

    public GetSubjectResponse(List<String> subjects, String message, List<ObjectError> errors) {
        super(message, errors);
        this.subjects = subjects;
    }

    public List<String> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<String> subjects) {
        this.subjects = subjects;
    }


}
