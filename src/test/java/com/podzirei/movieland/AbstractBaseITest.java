package com.podzirei.movieland;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.shaded.org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

@SpringBootTest
@Testcontainers
//@ActiveProfiles("test")
public class AbstractBaseITest {

    private static final MySQLContainer<?> MY_SQL_CONTAINER;

    @Autowired
    protected ObjectMapper objectMapper;

    static {
        MY_SQL_CONTAINER =
                new MySQLContainer<>("mysql:8.0.24")
                        .withDatabaseName("test")
                        .withUsername("test")
                        .withPassword("test");
        MY_SQL_CONTAINER.start();
    }

    @DynamicPropertySource
    public static void overrideProps(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", MY_SQL_CONTAINER::getJdbcUrl);
        registry.add("spring.datasource.username", MY_SQL_CONTAINER::getUsername);
        registry.add("spring.datasource.password", MY_SQL_CONTAINER::getPassword);
    }

    protected String getResponseAsString(String jsonPath) {
        URL resource = getClass().getClassLoader().getResource(jsonPath);
        try {
            return FileUtils.readFileToString(new File(resource != null ? resource.toURI() : null), StandardCharsets.UTF_8);
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException("Unable to find file: " + jsonPath);
        }
    }
}
