package Controllers;

import com.team18.MBC.core.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Controller
@RequestMapping("/tvshows")
public class TvShowController {
    private MovieService movieService;
    private ReviewService reviewService;
    private ReviewRepository reviewRepository;

    public TvShowController(MovieService movieService, ReviewService reviewService, ReviewRepository reviewRepository) {
        this.movieService = movieService;
        this.reviewService = reviewService;
        this.reviewRepository = reviewRepository;
    }
    @GetMapping
    public String getAllTvShows(Model model) {
        List<Movie> tvShows = movieService.getAllTvShows();
        model.addAttribute("movies", tvShows);
        model.addAttribute("contextPath", "tvshows");
        model.addAttribute("contentTitle", "TV Shows");
        return "movies";
    }

    @GetMapping("/{id}")
    public String getTvShowById(@PathVariable Long id, Model model, HttpSession session) {
        Movie tvShow = movieService.getTvShowById(id);
        if (tvShow != null) {
            model.addAttribute("movie", tvShow);
            model.addAttribute("contextPath", "tvshows");

            List<Review> reviews = reviewRepository.findByMovieId(id);
            double averageRating = reviewService.getAverageRatingForMovie(id);
            model.addAttribute("reviews", reviews);
            model.addAttribute("averageRating", averageRating);

            User loggedInUser = (User) session.getAttribute("LoggedInUser");
            boolean userHasReviewed = false;
            if (loggedInUser != null) {
                userHasReviewed = reviews.stream()
                        .anyMatch(review -> review.getUser().equals(loggedInUser));
            }
            model.addAttribute("userHasReviewed", userHasReviewed);

            return "movie-details";
        } else {
            return "404";
        }
    }


    @GetMapping("/categories")
    public String getTvShowCategories(Model model) {
        List<Movie> tvShows = movieService.getAllTvShows();

        Set<String> uniqueGenres = new HashSet<>();
        for (Movie movie : tvShows) {
            String[] genres = movie.getGenre().split(", ");
            uniqueGenres.addAll(Arrays.asList(genres));
        }

        // Add the unique genres to the model
        model.addAttribute("categories", uniqueGenres);
        return "tvShowCategories";
    }

    @GetMapping("/categories/{category}")
    public String getTvShowsBySpecificCategory(@PathVariable String category, Model model) {
        List<Movie> filteredTvShows = movieService.getTvShowsByGenre(category);
        model.addAttribute("tvShows", filteredTvShows);
        return "tvShowCategoriesSpecific";
    }


}
