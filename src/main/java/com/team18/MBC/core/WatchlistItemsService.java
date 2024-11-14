package com.team18.MBC.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WatchlistItemsService {

    private final WatchlistItemsRepository watchlistItemsRepository;
    private final WatchlistRepository watchlistRepository;
    private final MovieRepository movieRepository;

    @Autowired
    public WatchlistItemsService(WatchlistItemsRepository watchlistItemsRepository, WatchlistRepository watchlistRepository, MovieRepository movieRepository) {
        this.watchlistItemsRepository = watchlistItemsRepository;
        this.watchlistRepository = watchlistRepository;
        this.movieRepository = movieRepository;
    }

    // To be continued for adding & removing movies later on
    public WatchlistItems addMovieToWatchlist(Long watchlistId, Long movieId) {
        Optional<Watchlist> optionalWatchlist = watchlistRepository.findById(watchlistId);
        Optional<Movie> optionalMovie = movieRepository.findById(movieId);

        Watchlist watchlist = optionalWatchlist.get();
        Movie movie = optionalMovie.get();
        WatchlistItems item = new WatchlistItems(watchlist, movie);
        return watchlistItemsRepository.save(item);
    }

    public void removeMovieFromWatchlist(Long watchlistItemId) {
        watchlistItemsRepository.deleteById(watchlistItemId);
    }

    public List<WatchlistItems> getItemsByWatchlistId(Long watchlistId) {
        return watchlistItemsRepository.findByWatchlistId(watchlistId);
    }

    public void save(WatchlistItems watchlistItem) {
        watchlistItemsRepository.save(watchlistItem);
    }
}
