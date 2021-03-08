package se.webservices.lab2.servies;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import se.webservices.lab2.dtos.BookDto;
import se.webservices.lab2.dtos.BookTitel;

import java.util.List;
import java.util.Optional;

public interface BookService {
    List<BookDto> getAllBooks();

    Optional<BookDto> getOneBook(String isbn13);

    BookDto createBook(BookDto bookDto);

    void delete(String isbn13);

    BookDto replace(String isbn13, BookDto bookDto);

    BookDto update(String isbn13, BookTitel bookTitel);

    ResponseEntity<List<BookDto>> searchBooks(Specification<BookDto> specification);

    List<BookDto> searchWithParam(String search);
}
