package com.olusola.videorental;

import com.olusola.videorental.repository.MovieRepository;
import com.olusola.videorental.service.MovieService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
class MovieRentalApplicationTests {

    @Mock
    private MovieRepository videoRepository;

    @InjectMocks
    private MovieService movieService;

    @Test
    void contextLoads() {
    }

}
