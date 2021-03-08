package se.webservices.lab2.servies;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import se.webservices.lab2.dtos.BookDto;
import se.webservices.lab2.dtos.BookTitel;
import se.webservices.lab2.entities.Book;
import se.webservices.lab2.mappers.BookMapper;
import se.webservices.lab2.repositories.BookRepository;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private BookRepository bookRepository;
    private final BookMapper bookMapper;


    public BookServiceImpl(BookRepository bookRepository, BookMapper bookMapper) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
    }


    @Override
    public List<BookDto> getAllBooks() {
        return bookMapper.mapp(bookRepository.findAll());
    }


    @Override
    public Optional<BookDto> getOneBook(String isbn13) {
        return bookMapper.mapp(bookRepository.findById(isbn13));
    }


    @Override
    public BookDto createBook(BookDto bookDto) {
        if (bookDto.getTitel().isEmpty())
            throw new RuntimeException();
        return bookMapper.mapp(bookRepository.save(bookMapper.mapp(bookDto)));
    }


    @Override
    public void delete(String isbn13) {
        bookRepository.deleteById(isbn13);

    }


    @Override
    public BookDto replace(String isbn13, BookDto bookDto) {
        Optional<Book> book = bookRepository.findById(isbn13);

        if (book.isPresent()) {
            Book updatedBook = book.get();
            updatedBook.setTitel(bookDto.getTitel());
            updatedBook.setPris(bookDto.getPris());
            return bookMapper.mapp(bookRepository.save(updatedBook));
        } else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ISBN : " + isbn13 + " not found");
    }


    @Override
    public BookDto update(String isbn13, BookTitel bookTitel) {
        Optional<Book> book = bookRepository.findById(isbn13);
        if (book.isPresent()) {
            Book updatebook = book.get();
            if (bookTitel.titel != null)
                updatebook.setTitel(bookTitel.titel);
            return bookMapper.mapp(bookRepository.save(updatebook));

        } else throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ISBN : " + isbn13 + " not found");
    }


    @Override
    public List<BookDto> searchWithParam(String search) {

        return bookMapper.mapp(bookRepository.findAllByISBN13OrTitelOrLanguage(search, search, search));

    }


    @Override
    public ResponseEntity<List<BookDto>> searchBooks(Specification<BookDto> specification) {
        return new ResponseEntity(bookRepository.findAll(Specification.where(specification)), HttpStatus.OK);
    }


/*
    @Override
    public List<BookDto> findByFirstAlphabet(String character) {
        List<Book> book=bookRepository.findByTitel(character);
        if(book.isEmpty()){
            throw new RuntimeException();
        }
        return bookMapper.mapp(book.stream().filter((t)->t.getTitel().startsWith(character)).collect(Collectors.toList()));
    }*/
}
