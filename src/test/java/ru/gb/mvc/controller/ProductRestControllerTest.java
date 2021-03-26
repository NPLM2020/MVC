package ru.gb.mvc.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.gb.mvc.domain.Product;

@SpringBootTest
class ProductRestControllerTest {

    @Autowired
    private ProductRestController productRestController;

    @Test
    void getProduct() {
        System.out.println(productRestController.getProduct(1));
    }

    @Test
    void getProducts() {
        System.out.println(productRestController.getProducts());
    }

    @Test
    void saveProduct() {
        Product product = new Product("SpringJPA", 80000f);
        //productRestController.saveProduct(product);
    }

    @Test
    void deleteProduct() {
        productRestController.deleteProduct(25);
    }

    @Test
    void getProductsByMinPrice() {
        System.out.println(productRestController.getProductsByMinPrice(70000f));
    }

    @Test
    void getProductsByMaxPrice() {
        System.out.println(productRestController.getProductsByMaxPrice(80000f));
    }

    @Test
    void getProductsByPriceBetween() {
        System.out.println(productRestController.getProductsByPriceBetween(999f, 80000f));
    }
}