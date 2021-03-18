package ru.gb.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.gb.mvc.domain.Product;
import ru.gb.mvc.services.ObjectHelper;

@Controller
public class ProductController {

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

