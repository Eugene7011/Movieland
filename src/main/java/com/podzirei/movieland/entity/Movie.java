package com.podzirei.movieland.entity;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "movie")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "movies_id_sequence")
    @SequenceGenerator(name = "movies_id_sequence", sequenceName = "movies_id_sequence")
    @Column(name = "id")
    private int id;

    @Column(name = "name_russian", nullable = false)
    private String nameRussian;

    @Column(name = "name_native", nullable = false)
    private String nameNative;

    @Column(name = "year_of_release", nullable = false)
    private Date yearOfRelease;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "rating", nullable = false)
    private Double rating;

    @Column(name = "price", nullable = false)
    private Double price;

    @Column(name = "picture_path", nullable = false)
    private String picturePath;

    @Column(name = "votes", nullable = false)
    private int votes;

    @ManyToMany
    @JoinTable(
            name = "movie_genre",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id"))
    private Set<Genre> genres = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "movie_country",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "country_id"))
    private Set<Country> countries = new HashSet<>();

    public void addCountry(Country country) {
        countries.add(country);
        country.getMovies().add(this);
    }

    public void removeCountry(Country country) {
        countries.remove(country);
        country.getMovies().remove(this);
    }

    public void addGenre(Genre genre) {
        genres.add(genre);
        genre.getMovies().add(this);
    }

    public void removeGenre(Genre genre) {
        genres.remove(genre);
        genre.getMovies().remove(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Movie)) return false;
        return id != 0 && id == (((Movie) o).getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
