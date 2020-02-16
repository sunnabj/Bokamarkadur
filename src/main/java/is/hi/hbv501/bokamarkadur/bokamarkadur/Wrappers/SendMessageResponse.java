package is.hi.hbv501.bokamarkadur.bokamarkadur.Wrappers;

import is.hi.hbv501.bokamarkadur.bokamarkadur.Entities.Message;

import java.util.List;

public class SendMessageResponse extends GenericResponse {

    private Message message;

    public SendMessageResponse() {}

    public SendMessageResponse(Message message) {
        this.message = message;
    }


    public SendMessageResponse(String msg, List<?> errors) {
        this(null, msg, errors);
    }


    public SendMessageResponse(Message message, String msg, List<?> errors) {
        super(msg, errors);
        this.message = message;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

}
