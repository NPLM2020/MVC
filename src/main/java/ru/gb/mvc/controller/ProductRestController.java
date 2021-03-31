package ru.gb.mvc.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import ru.gb.mvc.database.ProductDAO;
import ru.gb.mvc.domain.Product;
import ru.gb.mvc.controller.exceptions.EntityNotFoundException;

import java.util.Optional;


@RestController
public class ProductRestController {

    private static final int DEFAULT_PAGE = 1;
    private static final int DEFAULT_PAGE_SIZE = 5;
    private static final String API_PATH = RestConstants.ROOT + "/products";

    private ProductDAO productDAO;


    public ProductRestController(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    @GetMapping(API_PATH)
    public Page<Product> getProducts(@RequestParam(value = "page", required = false) Optional<Integer> page,
                                     @RequestParam(value = "size", required = false) Optional<Integer> size) {

        int currentPage = page.orElse(DEFAULT_PAGE);
        int pageSize = size.orElse(DEFAULT_PAGE_SIZE);
        return productDAO.findAll(PageRequest.of(currentPage - 1, pageSize));
    }

    @GetMapping(value = API_PATH, params = "minPrice")
    public Page<Product> getProductsByMinPrice(@RequestParam(value = "page", required = false) Optional<Integer> page,
                                               @RequestParam(value = "size", required = false) Optional<Integer> size,
                                               @RequestParam(name = "minPrice") float minPrice) {
        int currentPage = page.orElse(DEFAULT_PAGE);
        int pageSize = size.orElse(DEFAULT_PAGE_SIZE);
        return productDAO.findByPriceGreaterThan(PageRequest.of(currentPage - 1, pageSize),
                minPrice);
    }

    @GetMapping(value = API_PATH, params = "maxPrice")
    public Page<Product> getProductsByMaxPrice(@RequestParam(value = "page", required = false) Optional<Integer> page,
                                               @RequestParam(value = "size", required = false) Optional<Integer> size,
                                               @RequestParam(name = "maxPrice") float maxPrice) {
        int currentPage = page.orElse(DEFAULT_PAGE);
        int pageSize = size.orElse(DEFAULT_PAGE_SIZE);
        return productDAO.findByPriceLessThan(PageRequest.of(currentPage - 1, pageSize),
                maxPrice);
    }

    @GetMapping(value = API_PATH, params = {"minPrice", "maxPrice"})
    public Page<Product> getProductsByPriceBetween(@RequestParam(value = "page", required = false) Optional<Integer> page,
                                                   @RequestParam(value = "size", required = false) Optional<Integer> size,
                                                   @RequestParam(name = "minPrice") float minPrice,
                                                   @RequestParam(name = "maxPrice") float maxPrice) {
        int currentPage = page.orElse(DEFAULT_PAGE);
        int pageSize = size.orElse(DEFAULT_PAGE_SIZE);
        return productDAO.findByPriceBetween(PageRequest.of(currentPage - 1, pageSize),
                minPrice, maxPrice);
    }

    @PostMapping(API_PATH)
    public void saveProduct(Product product) {
        productDAO.save(product);
    }

    @PutMapping(API_PATH)
    public void updateProduct(Product product) {
        if (productDAO.existsById(product.getId()))
            productDAO.save(product);
        else
            throw new EntityNotFoundException(String.format("Product with id %d not found", product.getId()));
    }

    @GetMapping(value = API_PATH + "/{id}")
    public Product getProduct(@PathVariable long id) {
        return productDAO.findById(id).orElseThrow(() -> new EntityNotFoundException(
                String.format("Product with id %d not found", id)));
    }

    @DeleteMapping(API_PATH + "/{id}")
    public void deleteProduct(@PathVariable long id) {
        productDAO.deleteById(id);
    }

}
