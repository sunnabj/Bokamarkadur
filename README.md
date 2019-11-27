
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

### View recently added books ###

* The ten books most recently added to the website are displayed on the front page. 

### View currently available book subjects ###

* A list of subjects that belong to books currently available on the site is displayed on the front page.
  * Each subject in the list works as a link that when pushed shows a page with information on all books belonging to the relevant subject.

### View all books ###

* A link in the navigation bar redirects the user to a page with a list of all available books on the site.
    * All relevant information on each book are displayed, that is title, author, price, whether it's for sale or requested, the user who added it, and more.

### View a specific book ###

* When a user is viewing a list of books, he can click a link to view the book, with it's information isolated on a specific page.

### View a specific user ###

* The user who added any particular book is shown with the book information. When the username is clicked, information about that user are displayed.

### Search for a book ###

* In the navigation bar is a search window. A user can choose to search for books for sale or requested books.
    * The inserted string is compared with all book titles and all authors and the matching results are displayed on a new page.

### About Us ###

* A user can click "About Us" in the navigation bar and is then directed to a page with information about the project and its authors.

### Sign up and login ###

* A user can create a new account and login by pushing the relevant buttons in the navigation bar.
* A dropdown menu appears in the menubar when a user logs in, with these following functions, which are only available when a user is logged in.

#### Add a new book ####

* The logged in user chooses whether he wants to add a book for sale or request a book, and then inserts relevant information about the book into a form, such as title, price and an optional picture.

#### View My Books ####

* The logged in user can view a list of the books he has added to the site.

#### Update Profile Information ####

* The logged in user can update his profile information, that is a text describing himself.

#### Send a message regarding a particular book ####

* When the logged in user views a book, as described above, he can now push a request/offer book button.
    * This directs him to a page with a message box. He can write a message and send it to the user who added that particular book to the site.
    * This is the only possible way to start a conversation with another user.

#### View my messages ####

* The logged in user can view his sent and received messages.
* If he has any received messages, he can click "Reply", which redirects him to a page with a message box, where he inserts a message and sends to the person who sent him the original message.


## Designers ##

* _Baldvin Blær Oddsson_
    * Email: bbo1@hi.is
* _Hieu Van Phan_
    * Email: hvp5@hi.is
* _Sunna Björnsdóttir_
    * Email: sub4@hi.is
* _Þórdís Pétursdóttir_
    * Email: thp44@hi.is


