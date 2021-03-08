package se.webservices.lab2.controllers;

import com.sipios.springsearch.anotation.SearchSpec;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import se.webservices.lab2.dtos.BookDto;
import se.webservices.lab2.dtos.BookTitel;
import se.webservices.lab2.servies.BookService;

import java.util.List;

@RestController
public class BookController {
    BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/books")
    public List<BookDto> getAllBooks() {
        return bookService.getAllBooks();
    }


    @GetMapping("/books/{isbn}")
    public BookDto getOneBook(@PathVariable String isbn) {
        return bookService.getOneBook(isbn)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "ISBN : " + isbn + " not found"));
    }


    @PostMapping("/books")
    @ResponseStatus(HttpStatus.CREATED)
    public BookDto createBook(@RequestBody BookDto bookDto) {
        return bookService.createBook(bookDto);
    }


    @DeleteMapping("/books/{isbn}")
    @ResponseStatus(HttpStatus.GONE)
    public void deleteBook(@PathVariable String isbn) {
        bookService.delete(isbn);
    }


    @PutMapping("/books/{isbn}")
    public BookDto replaceBook(@PathVariable String isbn, @RequestBody BookDto bookDto) {
        return bookService.replace(isbn, bookDto);
    }



    @PatchMapping("/books/{isbn}")
    public BookDto update(@RequestBody BookTitel bookTitel, @PathVariable String isbn) {
        return bookService.update(isbn, bookTitel);
    }


    @GetMapping("/books/searchparam/{search}")
    public List<BookDto> searchWithParam(@PathVariable String search) {
        return bookService.searchWithParam(search);

    }


    @GetMapping("/books/search")
    public ResponseEntity<List<BookDto>> searchBooks(@SearchSpec Specification<BookDto> specification) {
        return bookService.searchBooks(specification);
    }


}

