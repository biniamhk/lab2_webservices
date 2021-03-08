package se.webservices.lab2.mappers;

import org.springframework.stereotype.Component;
import se.webservices.lab2.dtos.BookDto;
import se.webservices.lab2.entities.Book;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class BookMapper {


    public BookDto mapp(Book book) {

        return new BookDto(book.getISBN13(), book.getTitel(), book.getLanguage(), book.getPris(), book.getUtgivningsdatum());
    }

    public Book mapp(BookDto bookDto) {
        return new Book(bookDto.getISBN13(), bookDto.getTitel(), bookDto.getLanguage(), bookDto.getPris(), bookDto.getUtgivningsdatum());
    }

    public Optional<BookDto> mapp(Optional<Book> optionalBook) {
        if (optionalBook.isEmpty())
            return Optional.empty();
        else

        return Optional.of(mapp(optionalBook.get()));

    }

    public List<BookDto> mapp(List<Book> books) {
        return
                books
                        .stream()
                        .map(this::mapp)
                        .collect(Collectors.toList());
    }
}