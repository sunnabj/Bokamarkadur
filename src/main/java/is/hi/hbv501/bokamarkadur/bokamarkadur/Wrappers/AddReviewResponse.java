package is.hi.hbv501.bokamarkadur.bokamarkadur.Wrappers;

import is.hi.hbv501.bokamarkadur.bokamarkadur.Entities.Review;

import java.util.List;

public class AddReviewResponse extends GenericResponse {

    private Review review;

    public AddReviewResponse(){}

    public AddReviewResponse(Review review) {
        this.review = review;
    }

    public AddReviewResponse(String message, List<?> errors) {
        this(null, message, errors);
    }

    public AddReviewResponse(Review review, String message, List<?> errors) {
        super(message, errors);
        this.review = review;
    }

    public Review getReview() {
        return review;
    }

    public void setReview(Review review) {
        this.review = review;
    }

}
