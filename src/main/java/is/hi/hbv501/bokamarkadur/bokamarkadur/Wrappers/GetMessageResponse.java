package is.hi.hbv501.bokamarkadur.bokamarkadur.Wrappers;

import is.hi.hbv501.bokamarkadur.bokamarkadur.Entities.Message;

import java.util.List;

public class GetMessageResponse extends GenericResponse {


    private Message message;
    private List<Message> messages;

    public GetMessageResponse(Message message) {
        this.message = message;
    }

    public GetMessageResponse(List<Message> messages) {
        this.messages = messages;
    }

    public GetMessageResponse(Message message, String msg, List<?> errors) {
        super(msg, errors);
        this.message = message;
    }

    public GetMessageResponse(List<Message> messages, String msg, List<?> errors) {
        super(msg, errors);
        this.messages = messages;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

}
