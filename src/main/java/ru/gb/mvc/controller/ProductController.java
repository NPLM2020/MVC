package ru.gb.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.gb.mvc.database.ProductDAO;
import ru.gb.mvc.domain.Product;
import ru.gb.mvc.services.ObjectHelper;

@Controller
public class ProductController {

    private ProductDAO productDAO;

    public ProductController(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    @GetMapping("/products/{id}/delete")
    public String deleteProduct(@PathVariable long id) {
        productDAO.deleteById(id);
        return "redirect:/";
    }

    @PostMapping("/products")
    public String saveProduct(Product product) {
        productDAO.save(product);
        return "redirect:/";
    }

    @RequestMapping(value="/json")
    public String getJson(Model model) {
        Product product = new Product();
        String message;
        try {
            message = ObjectHelper.toJson(product);
        } catch (Exception e) {
            message = e.getMessage();
        }
        model.addAttribute("json", message);
        return "json";
    }

    @RequestMapping(value="/xml")
    public String getXml(Model model) {
        Product product = new Product();
        String message;
        try {
            message = ObjectHelper.toXml(product);
        } catch (Exception e) {
            message = e.getMessage();
        }
        model.addAttribute("xml", message);
        return "xml";
    }

}

