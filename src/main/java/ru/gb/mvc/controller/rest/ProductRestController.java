package ru.gb.mvc.controller.rest;

import io.swagger.annotations.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import ru.gb.mvc.database.ProductDAO;
import ru.gb.mvc.domain.Product;
import ru.gb.mvc.controller.exceptions.EntityNotFoundException;

import java.util.Optional;

@RestController
@Api(tags = "Продукты", description = "Методы работы со справочником продуктов")
@RequestMapping(RestConstants.ROOT + "/products")
public class ProductRestController {

    private static final int DEFAULT_PAGE = 1;
    private static final int DEFAULT_PAGE_SIZE = 5;

    private ProductDAO productDAO;


    public ProductRestController(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    @GetMapping
    @ApiOperation("Получить перечень продуктов")
    public Page<Product> getProducts(@RequestParam(value = "page", required = false)
                                     @ApiParam(value = "Страница")
                                             Optional<Integer> page,
                                     @RequestParam(value = "size", required = false)
                                     @ApiParam("Количество записей на странице")
                                             Optional<Integer> size,
                                     @RequestParam(name = "minPrice", required = false)
                                     @ApiParam("Минимальная цена для выборки")
                                             Float minPrice,
                                     @RequestParam(name = "maxPrice", required = false)
                                     @ApiParam("Максимальная цена для выборки")
                                             Float maxPrice) {

        int currentPage = page.orElse(DEFAULT_PAGE);
        int pageSize = size.orElse(DEFAULT_PAGE_SIZE);
        Pageable pageRequest = PageRequest.of(currentPage - 1, pageSize);
        if (maxPrice == null && minPrice == null)
            return productDAO.findAll(pageRequest);
        if (maxPrice == null)
            return productDAO.findByPriceGreaterThan(pageRequest, minPrice);
        if (minPrice == null)
            return productDAO.findByPriceLessThan(pageRequest, maxPrice);
        return productDAO.findByPriceBetween(pageRequest, minPrice, maxPrice);
    }

    @PostMapping
    @ApiOperation("Сохранить новый продукт")
    public void saveProduct(@RequestBody Product product) {
        productDAO.save(product);
    }

    @PutMapping
    @ApiOperation("Обновить продукт")
    public void updateProduct(@RequestBody Product product) {
        if (productDAO.existsById(product.getId()))
            productDAO.save(product);
        else
            throw new EntityNotFoundException(String.format("Product with id %d not found", product.getId()));
    }

    @GetMapping("/{id}")
    @ApiOperation("Получить продукт")
    public Product getProduct(@PathVariable
                              @ApiParam("Идентификатор продукта")
                                      long id) {
        return productDAO.findById(id).orElseThrow(() -> new EntityNotFoundException(
                String.format("Product with id %d not found", id)));
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Удалить продукт")
    public void deleteProduct(@PathVariable
                              @ApiParam("Идентификатор продукта")
                                      long id) {
        productDAO.deleteById(id);
    }

}
