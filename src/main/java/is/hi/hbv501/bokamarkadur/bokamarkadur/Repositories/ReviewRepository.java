package is.hi.hbv501.bokamarkadur.bokamarkadur.Repositories;

import is.hi.hbv501.bokamarkadur.bokamarkadur.Entities.Review;
import is.hi.hbv501.bokamarkadur.bokamarkadur.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {


    //Inserts a new review into the database
    Review save(Review review);
    //Deletes a review from the database
    void delete(Review review);

    //Retrieves all the reviews in the database
    List<Review> findAll();

    // These functions query reviews by some particular condition.

    List<Review> findByUser(User user);

    Optional<Review> findById(long id);

    List<Review> findByReviewer(User reviewer);



}
