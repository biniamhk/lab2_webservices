package se.webservices.lab2.controllers;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import se.webservices.lab2.dtos.BookDto;
import se.webservices.lab2.dtos.BookTitel;
import se.webservices.lab2.servies.BookService;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

public class BookServiceTest implements BookService {
    @Override
    public List<BookDto> getAllBooks() {
        return List.of(new BookDto("1111111111111", "Test", "svenska", 100.0, Date.valueOf("2021-01-01")), new BookDto("1111111111122", "Test2", "svenska2", 200.0, Date.valueOf("2020-01-01")));
    }

    @Override
    public Optional<BookDto> getOneBook(String isbn13) {
        if (isbn13.equals("1111111111111"))
            return Optional.of(new BookDto("1111111111111", "Test", "svenska", 100.0, Date.valueOf("2021-01-01")));
        return Optional.empty();
    }

    @Override
    public BookDto createBook(BookDto bookDto) {
        return null;
    }

    @Override
    public void delete(String isbn13) {

    }

    @Override
    public BookDto replace(String isbn13, BookDto bookDto) {
        return null;
    }

    @Override
    public BookDto update(String isbn13, BookTitel bookTitel) {
        return null;
    }

    @Override
    public ResponseEntity<List<BookDto>> searchBooks(Specification<BookDto> specification) {
        return null;
    }

    @Override
    public List<BookDto> searchWithParam(String search) {
        return null;
    }
}
