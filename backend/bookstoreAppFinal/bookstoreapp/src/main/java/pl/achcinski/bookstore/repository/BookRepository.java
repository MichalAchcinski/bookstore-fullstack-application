package pl.achcinski.bookstore.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestParam;
import pl.achcinski.bookstore.model.Book;

@CrossOrigin("http://localhost:4200")
@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

    Page<Book> findByCategoryId(@RequestParam("id") int id, Pageable pageable);

    Page<Book> findByTitleContaining(@RequestParam("title") String title, Pageable pageable);

}
