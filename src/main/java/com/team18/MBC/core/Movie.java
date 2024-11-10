package com.team18.MBC.core;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "movies")
public class Movie {

    @OneToMany(mappedBy = "movie")
    private List<WatchlistItems> watchlistItems;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String genre;
    private String director;

    @Column(name = "RELEASEYEAR")
    private int releaseYear;

    private String description;

    private String type;

    private String CoverImage;

    public Movie() {
    }

    public Movie(String title, String genre, String director, int releaseYear, String description, String type, String coverImage) {
        this.title = title;
        this.genre = genre;
        this.director = director;
        this.releaseYear = releaseYear;
        this.description = description;
        this.type = type;
        this.CoverImage = coverImage;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCoverImage() {
        return CoverImage;
    }

    public void setCoverImage(String coverImage) {
        CoverImage = coverImage;
    }


    public static class MovieRating {
        private String title;
        private String genre;
        private String director;
        private int releaseYear;
        private String description;
        private double rating;

        public MovieRating(String title, String genre, String director, int releaseYear, String description, double rating) {
            this.title = title;
            this.genre = genre;
            this.director = director;
            this.releaseYear = releaseYear;
            this.description = description;
            this.rating = rating;
        }

        // Getters and setters for MovieRating
        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getGenre() {
            return genre;
        }

        public void setGenre(String genre) {
            this.genre = genre;
        }

        public String getDirector() {
            return director;
        }

        public void setDirector(String director) {
            this.director = director;
        }

        public int getReleaseYear() {
            return releaseYear;
        }

        public void setReleaseYear(int releaseYear) {
            this.releaseYear = releaseYear;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public double getRating() {
            return rating;
        }

        public void setRating(double rating) {
            this.rating = rating;
        }
    }
}
