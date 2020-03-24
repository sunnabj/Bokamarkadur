package is.hi.hbv501.bokamarkadur.bokamarkadur.Wrappers;

import is.hi.hbv501.bokamarkadur.bokamarkadur.Entities.Review;
import org.springframework.validation.ObjectError;

import java.util.List;

public class GetReviewsResponse extends GenericResponse {


    private List<Review> reviews;

    public GetReviewsResponse(List<Review> reviews) {
        this.reviews = reviews;
    }

    public GetReviewsResponse(List<Review> reviews, String message, List<ObjectError> errors) {
        super(message, errors);
        this.reviews = reviews;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

}
