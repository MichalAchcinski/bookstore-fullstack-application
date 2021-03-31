package pl.achcinski.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;
import pl.achcinski.bookstore.model.ShippingInformation;

import java.util.List;
import java.util.Optional;

@CrossOrigin("http://localhost:4200")
@Repository
public interface ShippingInformationRepository extends JpaRepository<ShippingInformation, Integer> {

    List<ShippingInformation> findAll();

    Optional<ShippingInformation> findById(Integer id);

    Optional<ShippingInformation> findByCustomerId(String customerId);

    boolean existsById(Integer id);

    boolean existsByCustomerId(String customerId);

    ShippingInformation save(ShippingInformation entity);

    void delete(ShippingInformation entity);

}
