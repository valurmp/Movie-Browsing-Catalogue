package com.team18.MBC.Services;

import com.team18.MBC.Repositories.MovieRepository;
import com.team18.MBC.Repositories.WatchlistRepository;
import com.team18.MBC.core.Movie;
import com.team18.MBC.core.User;
import com.team18.MBC.core.Watchlist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WatchlistService {

    private final WatchlistRepository watchlistRepository;
    private final MovieRepository movieRepository;

    @Autowired
    public WatchlistService(WatchlistRepository watchlistRepository, MovieRepository movieRepository) {
        this.watchlistRepository = watchlistRepository;
        this.movieRepository = movieRepository;
    }

    public List<Watchlist> findAll() {
        return watchlistRepository.findAll();
    }

    public Watchlist createWatchlist(String name, User user) {
        Watchlist watchlist = new Watchlist(name, user);
        return watchlistRepository.save(watchlist);
    }

    public List<Watchlist> getWatchlistsByUserId(Long userId) {
        return watchlistRepository.findByUserId(userId);
    }

    public List<Movie> getMoviesInWatchlist(Long watchlistId) {
        return watchlistRepository.findMoviesByWatchlistId(watchlistId);
    }

    public Watchlist addMovieToWatchlist(Long watchlistId, Long movieId) {
        Optional<Watchlist> optionalWatchlist = watchlistRepository.findById(watchlistId);
        Optional<Movie> optionalMovie = movieRepository.findById(movieId);

        Watchlist watchlist = optionalWatchlist.get();
        Movie movie = optionalMovie.get();
        watchlist.addMovie(movie);
        return watchlistRepository.save(watchlist);
    }

    public Watchlist removeMovieFromWatchlist(Long watchlistId, Long movieId) {
        Optional<Watchlist> optionalWatchlist = watchlistRepository.findById(watchlistId);
        Optional<Movie> optionalMovie = movieRepository.findById(movieId);

        Watchlist watchlist = optionalWatchlist.get();
        Movie movie = optionalMovie.get();
        watchlist.removeMovie(movie);
        return watchlistRepository.save(watchlist);
    }

    public Optional<Watchlist> getWatchlistById(Long watchlistId) {
        return watchlistRepository.findById(watchlistId);
    }

    public Watchlist saveWatchlist(Watchlist watchlist) {
        // Perform any additional business logic here if necessary (e.g., validation)

        // Save the watchlist to the database
        return watchlistRepository.save(watchlist);
    }

    public Watchlist findById(Long id) {
        return watchlistRepository.findById(id).orElse(null);
    }

    public void delete(Long id) {
        watchlistRepository.deleteById(id);
    }
    

}
