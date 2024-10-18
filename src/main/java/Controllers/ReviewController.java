package Controllers;

import com.team18.MBC.core.Review;
import com.team18.MBC.core.ReviewService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/reviews")
public class ReviewController {
    private ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping
    public ResponseEntity<List<Review>> getAllReviews() {
        List<Review> reviews = reviewService.getAllReviews();
        return ResponseEntity.ok(reviews); // 200 OK
    }
    @GetMapping("/{id}")
    public ResponseEntity<Review> getReviewById(@PathVariable Long id) {
        Review review = reviewService.getReviewsById(id);
        if (review != null) {
            return ResponseEntity.ok(review);

        } else {
            return ResponseEntity.notFound().build(); // 404 Not Found
        }
    }
    @GetMapping("/movie/{movieId}")
    public String getReviewsForMovie(@PathVariable Long movieId, Model model) {
        List<Review> reviews = reviewService.getReviewsByMovieId(movieId);
        model.addAttribute("reviews", reviews);
        return "movie-details";
    }

    @GetMapping("/tvshow/{tvShowId}")
    public String getReviewsForTvShow(@PathVariable Long tvShowId, Model model) {
        List<Review> reviews = reviewService.getReviewsByMovieId(tvShowId);
        model.addAttribute("reviews", reviews);
        return "movie-details";
    }

}
