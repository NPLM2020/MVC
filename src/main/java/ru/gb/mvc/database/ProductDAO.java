package ru.gb.mvc.database;


import ru.gb.mvc.domain.Product;

import java.util.List;

/**
 * Создайте класс ProductDao и реализуйте в нем логику выполнения CRUD-операций над сущностью Product
 * (Product findById(Long id)(используя jpql), List"Product" findAll()(используя jpql), void deleteById(Long id),
 * Product saveOrUpdate(Product product))
 */
public interface ProductDAO {
    Product findById(long id) throws FindInDatabaseException;
    List<Product> findAll();
    void deleteById(long id);
    Product saveOrUpdate(Product product);
}
