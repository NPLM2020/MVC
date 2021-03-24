package ru.gb.mvc.database;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gb.mvc.domain.Product;

import java.util.List;
import java.util.Optional;


@Repository
public interface ProductDAO extends JpaRepository<Product, Long> {
    Optional<List<Product>> findByPriceGreaterThan(float minPrice);
    Optional<List<Product>> findByPriceLessThan(float maxPrice);
    Optional<List<Product>> findByPriceBetween(float minPrice, float maxPrice);
}
