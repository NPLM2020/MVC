package ru.gb.mvc.database;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ru.gb.mvc.domain.Product;

import java.util.List;
import java.util.Optional;


@Repository
public interface ProductDAO extends PagingAndSortingRepository<Product, Long> {
    Page<Product> findByPriceGreaterThan(Pageable pageable, float minPrice);
    Page<Product> findByPriceLessThan(Pageable pageable, float maxPrice);
    Page<Product> findByPriceBetween(Pageable pageable, float minPrice, float maxPrice);
    Page<Product> findAll(Pageable pageable);
}
