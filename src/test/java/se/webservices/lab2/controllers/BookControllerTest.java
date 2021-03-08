package se.webservices.lab2.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;


import java.sql.Date;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BookControllerTest {


    @Test
    void callingOneWithValidIdReturnsOnePerson() {
        BookController bookController = new BookController(new BookServiceTest());
        var book = bookController.getOneBook("1111111111111");
        assertThat(book.getISBN13()).isEqualTo("1111111111111");
        assertThat(book.getTitel()).isEqualTo("Test");
        assertThat(book.getPris()).isEqualTo(100.0);
        assertThat(book.getLanguage()).isEqualTo("svenska");
        assertThat(book.getUtgivningsdatum()).isEqualTo(Date.valueOf("2021-01-01"));



    }

    @Test
    void callingOneWithInvalidIdThrowsExceptionStatus404() {
        BookController bookController=new BookController(new BookServiceTest());
        var expected= assertThrows(ResponseStatusException.class,()->bookController.getOneBook("2222222222222"));
        assertThat(expected.getStatus()).isEqualTo(HttpStatus.NOT_FOUND);
    }
}
