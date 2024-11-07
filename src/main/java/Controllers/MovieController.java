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

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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


    @GetMapping("/categories")
    public String getMovieCategories(Model model) {
        List<Movie> movies = movieService.getAllMovies();

        Set<String> uniqueGenres = new HashSet<>();
        for (Movie movie : movies) {
            String[] genres = movie.getGenre().split(", ");
            uniqueGenres.addAll(Arrays.asList(genres));
        }

        // Add the unique genres to the model
        model.addAttribute("categories", uniqueGenres);
        return "movieCategories";
    }

    @GetMapping("/categories/{category}")
    public String getMoviesBySpecificCategory(@PathVariable String category, Model model) {
        List<Movie> filteredMovies = movieService.getMoviesByGenre(category);
        model.addAttribute("movies", filteredMovies);
        return "movieCategoriesSpecific";
    }


}
