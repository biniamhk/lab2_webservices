package se.webservices.lab2;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import se.webservices.lab2.dtos.BookDto;

import java.sql.Date;
import java.util.Objects;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class Lab2ApplicationTests {
    @LocalServerPort
    int port;

    @Autowired
    TestRestTemplate testClient;

    @Test
    void contextLoads() {

        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", "application/xml");
        var result = testClient.getForEntity("http://localhost:" + port + "/books/", BookDto[].class);


        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(result.getBody().length).isGreaterThan(0);

    }


    @Test
    void postSomethingToService() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", "application/xml");
        //testClient.exchange("localhost:8080/person", HttpMethod.GET, new HttpEntity<>(headers), PersonDto[].class);
        BookDto booksDto = new BookDto("9876543219877", "TestTitel6", "TestSpråk6", 0.0, Date.valueOf("2021-01-10"));
        var result = testClient.postForEntity("http://localhost:" + port + "/books/", booksDto, BookDto.class);
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.CREATED);

        String resultISBN13 = Objects.requireNonNull(result.getBody()).getISBN13();
        var verifyPostQuery = testClient.getForEntity("http://localhost:" + port + "/books/" + resultISBN13 + "/", BookDto.class);
        assertThat(verifyPostQuery.getStatusCode()).isEqualTo(HttpStatus.OK);
    }


    @Test
    void DeleteSomethingToService() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", "application/xml");
        //testClient.exchange("localhost:8080/person", HttpMethod.GET, new HttpEntity<>(headers), PersonDto[].class);
        BookDto booksDto = new BookDto("1111111111111", "Test11", "Testspråk11", 1.0, Date.valueOf("2020-01-10"));
        testClient.postForEntity("http://localhost:" + port + "/books/", booksDto, BookDto.class);

        testClient.delete("http://localhost:" + port + "/books/" + "1111111111111" + "/", booksDto, BookDto.class);

        var verifyDeleteQuery = testClient.getForEntity("http://localhost:" + port + "/books/" + "1111111111111" + "/", BookDto.class);
        assertThat(verifyDeleteQuery.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);

    }


    @Test
    void updateSomethingToService() {

        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", "application/json");
        BookDto booksDto = new BookDto("2222222222222", "Test22", "Testspråk22", 22.0, Date.valueOf("2012-01-01"));

        var result = testClient.postForEntity("http://localhost:" + port + "/books/", booksDto, BookDto.class);
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.CREATED);

        testClient.patchForObject("http://localhost:" + port + "/books" + "/2222222222222", new BookDto("2222222222222",
                "UpdatedTitelTest22", "Testspråk22", 22.0, Date.valueOf("2012-01-01")), BookDto.class);
        var verfiyUpdate = testClient.getForEntity("http://localhost:" + port + "/books/" + "2222222222222"
                + "/", BookDto.class);
        assertThat(verfiyUpdate.getStatusCode()).isEqualTo(HttpStatus.OK);
    }


    @Test
    void updateAndVerify() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", "application/xml");
        BookDto booksDto = new BookDto("4444444444444", "TestTitel44", "TestSpråk44", 4.0, Date.valueOf("2014-04-04"));
        var result = testClient.postForEntity("http://localhost:" + port + "/books/", booksDto, BookDto.class);
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        String resultISBN13 = Objects.requireNonNull(result.getBody()).getISBN13();
        testClient.put("http://localhost:" + port + "/books" + "/4444444444444", new BookDto("/4444444444444", "TestPatchTite55", "TestSpråk5", 5.0, Date.valueOf("2015-05-15")), BookDto.class);

        var verifyPutResult = testClient.getForEntity("http://localhost:" + port + "/books/" + "4444444444444", BookDto.class);
        assertThat(verifyPutResult.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
}