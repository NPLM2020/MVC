package ru.gb.mvc.market;

import ru.gb.mvc.domain.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    List<Product> getProductList();
    Optional<Product> getProductById(int id);
}
