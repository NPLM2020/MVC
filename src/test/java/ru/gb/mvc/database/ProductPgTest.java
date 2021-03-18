package ru.gb.mvc.database;

import org.junit.jupiter.api.*;
import ru.gb.mvc.domain.Product;

import java.util.List;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ProductPgTest {

    private static long id;
    private ProductPg productPg = new ProductPg();

    @Test
    @Order(2)
    void findByIdSuccessfulTest() {
        Product product = productPg.findById(id);
        Assertions.assertEquals(product.getId(), id);
    }

    @Test
    void findByIdUnsuccessfulTest() {
        int badId = -1;
        Assertions.assertThrows(FindInDatabaseException.class, () -> productPg.findById(badId));
    }

    @Test
    @Order(4)
    void findAllSuccessfulTest() {
        List<Product> products = productPg.findAll();
        Assertions.assertTrue((long) products.size() > 0);
    }

    @Test
    @Order(5)
    void deleteById() {
        productPg.findById(id);
        productPg.deleteById(id);
        Assertions.assertThrows(FindInDatabaseException.class, () -> productPg.findById(id));
    }

    @Test
    @Order(1)
    void saveTest() {
        Product product = new Product("testProduct", 999);
        ProductPg productPg = new ProductPg();
        Product newProduct = productPg.saveOrUpdate(product);
        Assertions.assertEquals(newProduct.getName(), "testProduct");
        id = newProduct.getId();
    }

    @Test
    @Order(3)
    void updateTest() {
        ProductPg productPg = new ProductPg();
        Product newProduct = productPg.findById(id);
        newProduct.setName("testProductUpdated");
        Assertions.assertEquals(productPg.saveOrUpdate(newProduct).getName(), newProduct.getName());
    }
}