package com.podzirei.movieland.web.controller;

import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.ExpectedDataSet;
import com.github.database.rider.spring.api.DBRider;
import com.podzirei.movieland.AbstractBaseITest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DBRider
@AutoConfigureMockMvc
class GenreControllerITest extends AbstractBaseITest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DataSet(value = "datasets/movie/dataset_genres.yml", disableConstraints = true)
    @ExpectedDataSet("datasets/movie/dataset_genres.yml")
    @DisplayName("when Get All Genres then Ok Status Returned")
    void whenGetAllGenres_thenOkStatusReturned() throws Exception {
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