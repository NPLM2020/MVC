package ru.gb.mvc.market;


import ru.gb.mvc.domain.Product;

import java.util.List;

public interface Cart {
    void addProduct(int id);
    void deleteProduct(int id);
    void clear();
    List<Product> getProducts();
}
