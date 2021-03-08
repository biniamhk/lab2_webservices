package se.webservices.lab2.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import se.webservices.lab2.servies.BookService;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@WebMvcTest(BookController.class)
@Import(BookServiceTest.class)
public class MvcTest {
    @Autowired
    private MockMvc mockMvc;

    //@Autowired
    @MockBean
    BookService bookService;


    @Test
    void callingBooksWithUrlShouldReturnAllBookJson() throws Exception {
        var result = mockMvc.perform(MockMvcRequestBuilders.get("/books")
                .accept(MediaType.APPLICATION_JSON)).andReturn();
        assertThat(result.getResponse().getStatus()).isEqualTo(200);


    }

}
