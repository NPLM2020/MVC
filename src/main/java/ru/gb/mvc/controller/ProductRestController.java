package ru.gb.mvc.controller;

import org.springframework.web.bind.annotation.*;
import ru.gb.mvc.database.ProductDAO;
import ru.gb.mvc.domain.Product;

import java.util.List;


@RestController
public class ProductRestController {

    private ProductDAO productDAO;

    public ProductRestController(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    @GetMapping(value = "/products/{id}")
    public String getProduct(@PathVariable long id) {
        return productDAO.findById(id).toString();
    }

    @GetMapping("/products")
    public String getProducts() {
        return productDAO.findAll().toString();
    }

    @PostMapping("/products")
    public void saveProduct(Product product) {
        productDAO.save(product);
    }

    @GetMapping("/products/delete/{id}")
    public void deleteProduct(@PathVariable long id) {
        productDAO.deleteById(id);
    }

    @GetMapping(value = "/products", params = "minPrice")
    public String getProductsByMinPrice(@RequestParam(name = "minPrice", required = false) float minPrice) {
        return productDAO.findByPriceGreaterThan(minPrice).orElse(List.of()).toString();
    }

    @GetMapping(value = "/products", params = "maxPrice")
    public String getProductsByMaxPrice(@RequestParam(name = "maxPrice", required = false) float maxPrice) {
        return productDAO.findByPriceLessThan(maxPrice).orElse(List.of()).toString();
    }

    @GetMapping(value = "/products", params = {"minPrice", "maxPrice"})
    public String getProductsByPriceBetween(@RequestParam(name = "minPrice", required = false) float minPrice,
                                            @RequestParam(name = "maxPrice", required = false) float maxPrice) {
        return productDAO.findByPriceBetween(minPrice, maxPrice).orElse(List.of()).toString();
    }


}
