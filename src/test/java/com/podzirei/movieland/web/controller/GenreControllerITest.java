package com.podzirei.movieland.web.controller;

import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.ExpectedDataSet;
import com.github.database.rider.spring.api.DBRider;
import com.podzirei.movieland.AbstractBaseITest;
import com.podzirei.movieland.entity.Genre;
import com.podzirei.movieland.repository.GenreRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DBRider
@AutoConfigureMockMvc(addFilters = false)
class GenreControllerITest extends AbstractBaseITest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private GenreRepository genreRepository;

    @Test
    @DataSet("movie/yml/dataset_genres.yml")
    @ExpectedDataSet("datasets/yml/dataset_genres.yml")
    @DisplayName("when Get All Genres then Ok Status Returned")
    void whenGetAllGenres_thenOkStatusReturned() throws Exception {
        when(genreRepository.findAll()).thenReturn(List.of(new Genre(1, "драма"),
                new Genre(2, "криминал")));
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/genre")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .json("""
                                 [{
                                  "id" : 1,
                                  "name" : "драма"
                                 },
                                 {
                                  "id" : 2,
                                  "name" : "криминал"
                                 }]
                                """))
                .andExpect(status().isOk());
    }
}