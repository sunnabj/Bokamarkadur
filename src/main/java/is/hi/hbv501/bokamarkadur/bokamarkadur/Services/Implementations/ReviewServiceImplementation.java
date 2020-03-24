package is.hi.hbv501.bokamarkadur.bokamarkadur.Services.Implementations;

import is.hi.hbv501.bokamarkadur.bokamarkadur.Entities.Review;
import is.hi.hbv501.bokamarkadur.bokamarkadur.Entities.User;
import is.hi.hbv501.bokamarkadur.bokamarkadur.Repositories.ReviewRepository;
import is.hi.hbv501.bokamarkadur.bokamarkadur.Services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImplementation implements ReviewService {

    ReviewRepository repository;


    @Autowired
    public ReviewServiceImplementation(ReviewRepository reviewRepository){this.repository = reviewRepository;}

    @Override
    public Review save(Review review) {
        return repository.save(review);
    }

    @Override
    public void delete(Review review) {
        repository.delete(review);
    }

    @Override
    public List<Review> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Review> findById(long id) {
        return repository.findById(id);
    }

    @Override
    public List<Review> findByUser(User user) { return repository.findByUser(user); };

    @Override
    public List<Review> findByReviewer(User reviewer) { return repository.findByReviewer(reviewer); };

}
