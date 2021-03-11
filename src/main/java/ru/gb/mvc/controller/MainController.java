package ru.gb.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.gb.mvc.domain.Product;

import java.util.List;

@Controller
public class MainController {

    private final List<Product> productList;

    public MainController(List<Product> productList) {
        this.productList = productList;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("products", productList);
        return "index";
    }
}
