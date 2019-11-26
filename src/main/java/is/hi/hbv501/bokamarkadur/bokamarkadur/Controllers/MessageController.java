package is.hi.hbv501.bokamarkadur.bokamarkadur.Controllers;

import is.hi.hbv501.bokamarkadur.bokamarkadur.Entities.Book;
import is.hi.hbv501.bokamarkadur.bokamarkadur.Entities.Message;
import is.hi.hbv501.bokamarkadur.bokamarkadur.Entities.User;
import is.hi.hbv501.bokamarkadur.bokamarkadur.Services.BookService;
import is.hi.hbv501.bokamarkadur.bokamarkadur.Services.MessageService;
import is.hi.hbv501.bokamarkadur.bokamarkadur.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
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
    public String messageBook(@PathVariable("id") long id, @Valid Message message,
                              Model model, HttpSession session) {

        System.out.println("ID á new Message í request message POST: " + message.getId());
        Book book = bookService.findById(id).orElseThrow(()-> new IllegalArgumentException("Invalid book ID"));
        model.addAttribute("book", book);
        model.addAttribute("message", message);
        User sessionUser = (User) session.getAttribute("LoggedInUser");
        model.addAttribute("loggedIn", sessionUser);
        User current = userService.findByUsername(sessionUser.getUsername());
        message.setBook(book);
        message.setSender(current);
        message.setReceiver(book.getUser());
        messageService.save(message);
        return "redirect:/myMessages";
    }

    /*
     * Returns a page with a messagebox where a user can send a message to a book owner/requester.
     */
    @RequestMapping(value="/messageBook/{id}", method = RequestMethod.GET)
    public String sendRequest(@PathVariable("id") long id, Model model, HttpSession session) {
        User sessionUser = (User) session.getAttribute("LoggedInUser");
        model.addAttribute("loggedIn", sessionUser);
        if (sessionUser == null) {
            return "please-log-in";
        }
        Book book = bookService.findById(id).orElseThrow(()-> new IllegalArgumentException("Invalid book ID"));
        model.addAttribute("book", book);
        User current = userService.findByUsername(sessionUser.getUsername());
        // A user cannot send message to himself
        if (current == book.getUser()) {
            return "notSameUser";
        }
        Message message = new Message();
        model.addAttribute("message", message);
        return "messageBox";
    }


    /*
     * Returns a page with all messages the current logged in user has sent and received.
     */
    @RequestMapping(value="/myMessages", method = RequestMethod.GET)
    public String viewMessages(Model model, HttpSession session) {
        User sessionUser = (User) session.getAttribute("LoggedInUser");
        model.addAttribute("loggedIn", sessionUser);
        if (sessionUser == null) {
            return "please-log-in";
        }
        User current = userService.findByUsername(sessionUser.getUsername());

        List<Message> receivedMessages = messageService.findByReceiver(current);
        model.addAttribute("receivedmessages", receivedMessages);

        List<Message> sentMessages = messageService.findBySender(current);
        model.addAttribute("sentmessages", sentMessages);
        return "myMessages";
    }

    /*
     * Returns a page with a messagebox where a user can reply to a particular message
     * he has received.
     */
    @RequestMapping(value="/replyMessage/{id}", method = RequestMethod.GET)
    public String pushReply(@PathVariable("id") long id, Model model, HttpSession session) {
        User sessionUser = (User) session.getAttribute("LoggedInUser");
        model.addAttribute("loggedIn", sessionUser);
        if (sessionUser == null) {
            return "please-log-in";
        }
        // The message the user is about to reply to is fetched. It has all relevant information
        // needed for the new reply message.
        Message initialMessage = messageService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid message ID"));
        User current = userService.findByUsername(sessionUser.getUsername());
        // A user cannot send messages to himself.
        if (current == initialMessage.getSender()) {
            return "notSameUser";
        }
        // New empty messages are added to the model -> The messages the user is about to write.
        Message newMessage = new Message();
        model.addAttribute("newMessage", newMessage);
        model.addAttribute("initialMessage", initialMessage);
        return "replyMessage";
    }

    /*
     * Creates and posts a reply to a particular message a user has received.
     */
    @RequestMapping(value="/replyMessage/{id}", method = RequestMethod.POST)
    public String sendReply(@PathVariable("id") long id, @Valid Message newMessage, Model model, HttpSession session) {
        User sessionUser = (User) session.getAttribute("LoggedInUser");
        model.addAttribute("loggedIn", sessionUser);
        if (sessionUser == null) {
            return "please-log-in";
        }
        // The message the user is replying to
        Message initialMessage = messageService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid message ID"));
        model.addAttribute("initialMessage", initialMessage);
        // The model fetches the messageBody string the user inserted
        String newMessageBody = newMessage.messageBody;
        // New messages are created with this messageBody and relevant information from the original message.
        newMessage = new Message();
        newMessage.setMessageBody(newMessageBody);
        newMessage.setBook(initialMessage.getBook());
        User current = userService.findByUsername(sessionUser.getUsername());
        newMessage.setSender(current);
        newMessage.setReceiver(initialMessage.getSender());
        //messageService.save(initialMessage);
        messageService.save(newMessage);
        return "redirect:/myMessages";
    }


}
