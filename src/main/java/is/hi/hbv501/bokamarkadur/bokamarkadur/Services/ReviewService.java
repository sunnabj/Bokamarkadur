package is.hi.hbv501.bokamarkadur.bokamarkadur.Services;

import is.hi.hbv501.bokamarkadur.bokamarkadur.Entities.Book;
import is.hi.hbv501.bokamarkadur.bokamarkadur.Entities.Review;
import is.hi.hbv501.bokamarkadur.bokamarkadur.Entities.User;

import java.util.List;
import java.util.Optional;

public interface ReviewService {


    /*
     * These functions represent the queries explained in the BookRepository,
     * and serve as a layer between the controller and the database.
     */
    Review save(Review review);
    void delete(Review review);
    List<Review> findAll();
    Optional<Review> findById(long id);
    List<Review> findByReviewer(User reviewer);
    List<Review> findByUser(User user);

}
