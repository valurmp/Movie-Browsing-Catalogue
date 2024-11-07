package com.team18.MBC.core;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface WatchlistItemsRepository extends JpaRepository<WatchlistItems, Long> {
    List<WatchlistItems> findByWatchlistId(Long watchlistId);
}