package com.podzirei.movieland.repository;

import com.podzirei.movieland.entity.Genre;
import com.podzirei.movieland.entity.Movie;
import com.podzirei.movieland.web.controller.movie.MovieRequest;
import com.podzirei.movieland.web.controller.movie.SortDirection;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Order;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.podzirei.movieland.web.controller.movie.SortDirection.ASC;

//@Primary
@Repository
@RequiredArgsConstructor
public class CustomMovieRepository implements MovieRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Movie> findAll() {
        TypedQuery<Movie> query = entityManager.createQuery("select m.id, m.nameRussian, m.nameNative, m.yearOfRelease," +
                "m.description, m.rating, m.price, m.picturePath, m.votes from Movie m", Movie.class);
        return query.getResultList();
    }

    @Override
    public List<Movie> findAll(MovieRequest movieRequest) {
        SortDirection sortDirectionRating = movieRequest.getRating();
        SortDirection sortDirectionPrice = movieRequest.getPrice();
        SortDirection sortDirection = null;
        String sortParameter = null;

        if (sortDirectionRating != null) {
            sortDirection = sortDirectionRating;
            sortParameter = "rating";
        }
        if (sortDirectionPrice != null) {
            sortDirection = sortDirectionPrice;
            sortParameter = "price";
        }

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Movie> query = criteriaBuilder.createQuery(Movie.class);
        Root<Movie> root = query.from(Movie.class);

        if (sortDirection != null) {
            var sortParameterExpression = root.get(sortParameter);
            Order order = sortDirection == ASC ? criteriaBuilder.asc(sortParameterExpression)
                    : criteriaBuilder.desc(sortParameterExpression);
            query.orderBy(order);
        }

        var genreIdOptional = movieRequest.getGenre();
        if (genreIdOptional != null) {
            Join<Movie, Genre> genre = root.join("genres");

            query.where(
                    criteriaBuilder.equal(genre.get("id"), genreIdOptional)
            );
        }
        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public List<Movie> getRandom(int randomNumber) {
        TypedQuery<Movie> query = entityManager.createQuery("select m from Movie m ORDER BY random()", Movie.class);
        query.setMaxResults(randomNumber);
        return query.getResultList();
    }

}
