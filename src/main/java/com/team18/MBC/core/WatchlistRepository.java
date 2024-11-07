package com.team18.MBC.core;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WatchlistRepository extends JpaRepository<Watchlist, Long> {
    @Query("SELECT w FROM Watchlist w WHERE w.user.id = :userId")
    List<Watchlist> findByUserId(@Param("userId") Long userId);

    @Query("SELECT m FROM Movie m JOIN WatchlistItems wi ON m.id = wi.movie.id WHERE wi.watchlist.id = :watchlistId")
    List<Movie> findMoviesByWatchlistId(@Param("watchlistId") Long watchlistId);
}