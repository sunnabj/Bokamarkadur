package is.hi.hbv501.bokamarkadur.bokamarkadur;

import is.hi.hbv501.bokamarkadur.bokamarkadur.Entities.Book;
import is.hi.hbv501.bokamarkadur.bokamarkadur.Entities.Message;
import is.hi.hbv501.bokamarkadur.bokamarkadur.Entities.User;
import is.hi.hbv501.bokamarkadur.bokamarkadur.Services.BookService;
import is.hi.hbv501.bokamarkadur.bokamarkadur.Services.MessageService;
import is.hi.hbv501.bokamarkadur.bokamarkadur.Services.UserService;
import is.hi.hbv501.bokamarkadur.bokamarkadur.Wrappers.GetMessageResponse;
import is.hi.hbv501.bokamarkadur.bokamarkadur.Wrappers.SendMessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;


/**
 * Þessi klasi er ekki notaður
 */

@RestController
public class MessageController {

    private UserService userService;
    private BookService bookService;
    private MessageService messageService;

    @Autowired
    public MessageController(UserService userService, BookService bookService, MessageService messageService) {
        this.userService = userService;
        this.bookService = bookService;
        this.messageService = messageService;
    }

    /*
     * Creates a new message with the text the user writes in the box, with the relevant book
     * as attribute, and the user that added the book as receiver.
     */
    @RequestMapping(value ="/messageBook/{id}", method = RequestMethod.POST)
    public ResponseEntity<SendMessageResponse> messageBook(@PathVariable("id") long id, @Valid @RequestBody Message message,
                                                           Authentication authentication) {

        Book book = bookService.findById(id).orElseThrow(()-> new IllegalArgumentException("Invalid book ID"));
        User loggedinUser = userService.findByUsername(authentication.getName());
        User current = userService.findByUsername(loggedinUser.getUsername());
        message.setBook(book);
        message.setSender(current);
        message.setReceiver(book.getUser());
        return new ResponseEntity<>(new SendMessageResponse(messageService.save(message)), HttpStatus.CREATED);
    }


    /*
     * Creates and posts a reply to a particular message a user has received.
     */
    @RequestMapping(value="/replyMessage/{id}", method = RequestMethod.POST)
    public ResponseEntity<SendMessageResponse> sendReply(@PathVariable("id") long id, @Valid @RequestBody Message newMessage,
                                                                Authentication authentication) {
        User loggedinUser = userService.findByUsername(authentication.getName());
        if (loggedinUser == null) {
            //return Eitthvað villu /please log in object
        }
        // The message the user is replying to
        Message initialMessage = messageService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid message ID"));
        // The model fetches the messageBody string the user inserted
        String newMessageBody = newMessage.messageBody;
        // New messages are created with this messageBody and relevant information from the original message.
        newMessage = new Message();
        newMessage.setMessageBody(newMessageBody);
        newMessage.setBook(initialMessage.getBook());
        User current = userService.findByUsername(loggedinUser.getUsername());
        newMessage.setSender(current);
        newMessage.setReceiver(initialMessage.getSender());
        return new ResponseEntity<>(new SendMessageResponse(messageService.save(newMessage)), HttpStatus.CREATED);
    }



    /*
     * Returns a page with all messages the current logged in user has sent.
     */
    @RequestMapping(value = "/mySentMessages", method = RequestMethod.GET)
    public ResponseEntity<GetMessageResponse> viewSentMessages(Authentication authentication) {
        User loggedinUser = userService.findByUsername(authentication.getName());
        if (loggedinUser == null) {
            //Something?
        }
        User current = userService.findByUsername(loggedinUser.getUsername());
        List<Message> sentMessages = messageService.findBySender(current);
        return new ResponseEntity<>(new GetMessageResponse(sentMessages), HttpStatus.OK);
    }



    /*
     * Returns a page with all messages the current logged in user has received.
     */
    @RequestMapping(value = "/myReceivedMessages", method = RequestMethod.GET)
    public ResponseEntity<GetMessageResponse> viewReceivedMessages(Authentication authentication) {
        User loggedinUser = userService.findByUsername(authentication.getName());
        if (loggedinUser == null) {
            //Something?
        }
        User current = userService.findByUsername(loggedinUser.getUsername());
        List<Message> receivedMessages = messageService.findByReceiver(current);
        return new ResponseEntity<>(new GetMessageResponse(receivedMessages), HttpStatus.OK);
    }


}
