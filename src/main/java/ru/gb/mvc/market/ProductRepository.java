package ru.gb.mvc.market;

import ru.gb.mvc.domain.Product;

import java.util.List;

public interface ProductRepository {
    List<Product> getProductList();
    Product getProductById(int id);
}
