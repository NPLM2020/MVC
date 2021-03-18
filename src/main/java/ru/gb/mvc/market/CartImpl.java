package ru.gb.mvc.market;

import org.springframework.stereotype.Component;
import ru.gb.mvc.domain.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class CartImpl implements Cart{

    private final ProductRepository productRepository;
    private List<Product> products;

    public CartImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
        this.products = new ArrayList<>();
    }

    @Override
    public void addProduct(int id) {
        products.add(productRepository.getProductById(id).get());
    }

    @Override
    public void deleteProduct(int id) {
        Optional<Product> product = productRepository.getProductById(id);
        if (product.isPresent()) {
            products.remove(product);
        }
    }

    @Override
    public void clear() {
        products.clear();
    }

    @Override
    public List<Product> getProducts() {
        return products;
    }

}
