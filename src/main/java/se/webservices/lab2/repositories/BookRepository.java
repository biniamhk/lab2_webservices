package se.webservices.lab2.repositories;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import se.webservices.lab2.entities.Book;

import java.util.List;

@RepositoryRestResource
public interface BookRepository extends JpaRepository<Book, String>, JpaSpecificationExecutor {
    List<Book> findAllByISBN13OrTitelOrLanguage(String ISBN13, String titel, String language);
    //List<Book> findAllByUtgivningsdatumOOrPris(String ISBN13, String titel, String language);

   // List<Book> findAll(Specification spec);

}
