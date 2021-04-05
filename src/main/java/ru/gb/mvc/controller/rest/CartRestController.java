package ru.gb.mvc.controller.rest;

import org.springframework.web.bind.annotation.*;
import ru.gb.mvc.database.ProductDAO;
import ru.gb.mvc.domain.Product;

import java.util.*;

@RestController
@RequestMapping(RestConstants.ROOT + "/users/{userId}/cart")
public class CartRestController {

    private final ProductDAO productDAO;
    private HashMap<Long, List<Product>> cartsRepository;

    public CartRestController(ProductDAO productDAO,
                              HashMap<Long, List<Product>> cartsRepository) {
        this.productDAO = productDAO;
        this.cartsRepository = cartsRepository;
    }

    @GetMapping
    public List<Product> getUserCart(@PathVariable long userId) {
        return cartsRepository.get(userId);
    }


    @PostMapping("/products/{productId}")
    public void addProductToCart(@PathVariable long userId, @PathVariable long productId) {
        if (cartsRepository.get(userId) == null) {
            cartsRepository.put(userId, new ArrayList<>());
        }
        if (productDAO.existsById(productId)) {
            cartsRepository.get(userId).add(productDAO.findById(productId).get());
        }

    }

    @DeleteMapping("/products/{productId}")
    public void deleteProductFromCart(@PathVariable long userId, @PathVariable long productId) {
        Optional<Product> product = cartsRepository.get(userId).stream().
                filter(p -> p.getId() == productId).findFirst();
        product.ifPresent(value -> cartsRepository.get(userId).remove(value));
    }

}
