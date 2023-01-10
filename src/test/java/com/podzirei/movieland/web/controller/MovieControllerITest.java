package com.podzirei.movieland.web.controller;

import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.ExpectedDataSet;
import com.github.database.rider.spring.api.DBRider;
import com.podzirei.movieland.AbstractBaseITest;
import com.podzirei.movieland.entity.Country;
import com.podzirei.movieland.entity.Genre;
import com.podzirei.movieland.entity.Movie;
import com.podzirei.movieland.repository.MovieRepository;
import com.podzirei.movieland.service.MovieService;
import com.podzirei.movieland.web.controller.movie.MovieRequest;
import com.podzirei.movieland.web.controller.movie.MovieResponse;
import com.podzirei.movieland.web.controller.movie.SortDirection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.sql.Date;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DBRider
@AutoConfigureMockMvc(addFilters = false)
class MovieControllerITest extends AbstractBaseITest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DataSet(value = "datasets/yml/dataset_movies.yml",
            cleanAfter = true, skipCleaningFor = "flyway_schema_history")
    @ExpectedDataSet("datasets/yml/dataset_movies.yml")
    @DisplayName("when Get All Movies then Ok Status Returned")
    void whenGetAllMovies_thenOkStatusReturned() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/movie")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .json("""
                                 [{
                                      "id": 1,
                                       "nameRussian": "Побег из Шоушенка",
                                       "nameNative": "The Shawshank Redemption",
                                       "yearOfRelease": "1994",
                                       "rating": 8.89,
                                       "price": 123.45,
                                       "picturePath": "https://images-na.ssl-images-amazon.com/images/M/MV5BODU4MjU4NjIwNl5BMl5BanBnXkFtZTgwMDU2MjEyMDE@._V1._SY209_CR0,0,140,209_.jpg"
                                   }
                                   ]
                                """))
                .andExpect(status().isOk());
    }
}