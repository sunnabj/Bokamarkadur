package is.hi.hbv501.bokamarkadur.bokamarkadur.Entities;

import javax.persistence.Entity;

public class MessageMap {

    private long bookID;
    private long senderID;
    private long receiverID;
    private long messageID;

    public MessageMap() {

    }


    public long getBookID() {
        return bookID;
    }

    public void setBookID(long bookID) {
        this.bookID = bookID;
    }

    public long getSenderID() {
        return senderID;
    }

    public void setSenderID(long senderID) {
        this.senderID = senderID;
    }

    public long getReceiverID() {
        return receiverID;
    }

    public void setReceiverID(long receiverID) {
        this.receiverID = receiverID;
    }

    public long getMessageID() {
        return messageID;
    }

    public void setMessageID(long messageID) {
        this.messageID = messageID;
    }
}
