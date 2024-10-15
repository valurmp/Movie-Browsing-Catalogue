package Controllers;

import com.team18.MBC.core.Movie;
import com.team18.MBC.core.MovieService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/tvshows")
public class TvShowController {
    private MovieService movieService;

    public TvShowController(MovieService movieService) {
        this.movieService = movieService;
    }
    @GetMapping
    public ResponseEntity<List<Movie>> getAllTvShows() {
        List<Movie> tvShows = movieService.getAllTvShows();
        return ResponseEntity.ok(tvShows);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Movie> getTvShowById(@PathVariable Long id) {
        Movie tvShow = movieService.getTvShowById(id);
        return tvShow != null ? ResponseEntity.ok(tvShow) : ResponseEntity.notFound().build();
    }
}
