package Controllers;

import com.team18.MBC.core.Movie;
import com.team18.MBC.core.MovieService;
import com.team18.MBC.core.Review;
import com.team18.MBC.core.ReviewService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/movies")
public class MovieController {
    private MovieService movieService;
    private ReviewService reviewService;

    public MovieController(MovieService movieService, ReviewService reviewService) {
        this.movieService = movieService;
        this.reviewService = reviewService;
    }

    @GetMapping
    public String getAllMovies(Model model) {
        List<Movie> movies = movieService.getAllMovies();
        model.addAttribute("movies", movies);
        model.addAttribute("contextPath", "movies");
        model.addAttribute("contentTitle", "Movies");
        return "movies";
    }
    @GetMapping("/{id}")
    public String getMovieById(@PathVariable Long id, Model model) {
        Movie movie = movieService.getMovieById(id);
        if (movie != null) {
            model.addAttribute("movie", movie);
            model.addAttribute("contextPath", "movies");

            List<Review> reviews = reviewService.getReviewsByMovieId(id);
            double averageRating = reviewService.getAverageRatingForMovie(id);
            model.addAttribute("reviews", reviews);
            model.addAttribute("averageRating", averageRating);

            return "movie-details";
        } else {
            return "404";
        }
    }

}
