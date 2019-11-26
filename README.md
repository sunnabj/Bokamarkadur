
# Project Information #

## How to run ##

When the project is fetched, it might be necessary to do a git pull command following git clone.

```bash
git clone https://github.com/sunnabj/Bokamarkadur
git pull
```

The project should be uploaded to IntelliJ or some other Java-compatible IDE.
A postgres database should be set up by downloading PgAdmin and creating an account.
The username and password for PgAdmin should be put in the project's application.properties file as follows:

```bash
spring.datasource.username=insertYourUsername
spring.datasource.password=insertYourPassword
```

## Organization and functions ##

This program is a web application that provides the functions of a book exchange market.
The program is written in Java with the Spring framework and uses thymeleaf for HTML.

### Homepage ###

* Shows the 10 books most recently added to the website
* Shows a list of subjects from which books on the site are currently available.
  * Each subject in the list works as a link that when pushed shows a page with information on all books in the relevant subject.

### Navigation bar ###

* The Book market's logo redirects to the homepage
* View all books returns a page with a list of every book.
* About us returns a page about the project and it's authors.
* The search function is in the middle. A user can choose to search for books for sale or for requested books, and when enter is pressed, a list of books where the title or the author include the string inserted to the search box.
* When user is not logged in:
  * Login returns a page where the user inserts his username and password and logs in if both are correct.
  * Sign up returns a form where you can create a new account which you can then log into. Password needs to be at least 8 letters and username cannot match another username in the database.
* When user is logged in - a dropdown menu appears:
  * Add a new book: The logged in user can choose to add a book for sale or request a book. The user fills out a form with relevant information and can upload a photo.
  * My Books: The logged in user can look at the books he has added to the site and delete them if he wishes.
  * Update Profile: The logged in user can update information about himself which other users can see.
  * My Messages: The user can view messages he has sent and messages he has received, and reply to those messages.
  * Logout: Logs the current logged in user out.
  
### Request/Offer a book ###

* Where lists of books appear (front page, all books, all books from particular subject) it is possible to press view book.
* This returns information about that particular book, where the name of the user that added it to the site can be pressed and his information viewed.
* There is also a button which says "Request/Offer book." If the user is logged in, he is redirected to a page with a message box, where he can send a message to the user that owns the book or is requesting it, regarding the book.
* This message appears in the received messages of that user, and the user can reply to this message. The reply message shows up in the received message list of the user that sent the original message.
  * This way, users can communicate about certain books.



## Designers ##

* _Baldvin Blær Oddsson_
    * Email: bbo1@hi.is
* _Hieu Van Phan_
    * Email: hvp5@hi.is
* _Sunna Björnsdóttir_
    * Email: sub4@hi.is
* _Þórdís Pétursdóttir_
    * Email: thp44@hi.is


