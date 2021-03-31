package pl.achcinski.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;
import pl.achcinski.bookstore.model.book.Book;
import pl.achcinski.bookstore.model.CartItem;

import java.util.List;
import java.util.Optional;

@CrossOrigin("http://localhost:4200")
@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Integer> {

    List<CartItem> findByCustomerId(String customerId);

    List<CartItem> findByBook(Book book);

    CartItem findByCustomerIdAndBook(String customerId, Book book);

    Optional<CartItem> findById(Integer id);

    CartItem save(CartItem entity);

    void delete(CartItem entity);

}
