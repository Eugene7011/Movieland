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
@AutoConfigureMockMvc(addFilters = false)
class MovieControllerITest extends AbstractBaseITest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DataSet(value = "datasets/dataset_movies.yml")
//    @ExpectedDataSet("datasets/dataset_movies.yml")
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