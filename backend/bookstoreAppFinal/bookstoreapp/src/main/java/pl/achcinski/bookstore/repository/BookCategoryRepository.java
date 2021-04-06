package pl.achcinski.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;
import pl.achcinski.bookstore.model.BookCategory;

@CrossOrigin("http://localhost:4200")
@Repository
public interface BookCategoryRepository extends JpaRepository<BookCategory, Integer> {
}
