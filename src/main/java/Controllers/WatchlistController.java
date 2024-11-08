package Controllers;

import com.team18.MBC.core.Movie;
import com.team18.MBC.core.Watchlist;
import com.team18.MBC.core.WatchlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Controller
public class WatchlistController {

    @Autowired
    private WatchlistService watchlistService;
    @GetMapping("/watchlists")
    public String getAllWatchlists(Model model) {
        List<Watchlist> watchlists = watchlistService.findAll();
        model.addAttribute("watchlists", watchlists);
        return "watchlists";
    }

    @GetMapping("/watchlists/{watchlistId}")
    public String getWatchlistItems(@PathVariable Long watchlistId, Model model) {
        List<Movie> watchlistItems = watchlistService.getMoviesInWatchlist(watchlistId);
        Optional<Watchlist> watchlist = watchlistService.getWatchlistById(watchlistId);
        Watchlist unwrappedWatchlist = watchlist.get();
        model.addAttribute("watchlistItems", watchlistItems);
        model.addAttribute("watchlist",unwrappedWatchlist);
        return "watchlist-details";
    }

    @GetMapping("/users/{userId}/watchlists")
    public String getUserWatchlists(@PathVariable Long userId, Model model) {
        List<Watchlist> userWatchlists = watchlistService.getWatchlistsByUserId(userId);
        model.addAttribute("userWatchlists", userWatchlists);
        return "userWatchlists";
    }
}