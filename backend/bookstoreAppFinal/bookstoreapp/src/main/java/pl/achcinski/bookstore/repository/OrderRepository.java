package pl.achcinski.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;
import pl.achcinski.bookstore.model.Order;

import java.util.List;
import java.util.Optional;

@CrossOrigin("http://localhost:4200")
@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    List<Order> findByCustomerId(String customerId);

    Optional<Order> findById(Integer id);

    Order save(Order entity);

    void delete(Order entity);

}
