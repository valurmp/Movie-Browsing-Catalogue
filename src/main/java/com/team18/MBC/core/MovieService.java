package com.team18.MBC.core;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;

@Service
public class MovieService {

    private MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public Movie getMovieById(Long id) {
        Optional<Movie> movie = movieRepository.findByIdAndType(id, "movie");
        return movie.orElse(null);
    }

    public List<Movie> getAllMovies() {
        return movieRepository.findByType("movie");
    }

    //Seperation for TV Shows instead of creating a new TV Show classes.

    public Movie getTvShowById(Long id) {
        Optional<Movie> movie = movieRepository.findByIdAndType(id, "tv_show");
        return movie.orElse(null);
    }

    public List<Movie> getAllTvShows() {
        return movieRepository.findByType("tv_show");
    }


    public List<Movie> getTvShowsByGenre(String genre) {
        return movieRepository.findTVShowByGenreContaining(genre);
    }

    public List<Movie> getMoviesByGenre(String genre) {
        return movieRepository.findMovieByGenreContaining(genre);
    }

    public List<Movie.MovieRating> getTopMovies() {
        List<Object[]> results = movieRepository.getTopMovies();
        List<Movie.MovieRating> topMovies = new ArrayList<>();

        for (Object[] row : results) {
            Movie.MovieRating movieRating = new Movie.MovieRating(
                    (String) row[0],
                    (String) row[1],
                    (String) row[2],
                    (Integer) row[3],
                    (String) row[4],
                    (Double) row[5]
            );
            topMovies.add(movieRating);
        }
        return topMovies;
    }
}
