package com.team18.MBC.core;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
}