package pl.achcinski.bookstore.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;
import pl.achcinski.bookstore.model.book.Book;

import java.util.List;
import java.util.Optional;

@CrossOrigin("http://localhost:4200")
@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

    List<Book> findAll();

    Optional<Book> findById(Integer id);

    Optional<Book> findByTitle(String title);

    boolean existsById(Integer id);

    Book save(Book entity);

    void delete(Book entity);

    Page<Book> findAll(Pageable page);

}
