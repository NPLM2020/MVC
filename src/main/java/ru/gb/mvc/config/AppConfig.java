package ru.gb.mvc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import ru.gb.mvc.database.CommonEntityManager;
import ru.gb.mvc.domain.Product;
import ru.gb.mvc.market.Cart;
import ru.gb.mvc.market.CartImpl;
import ru.gb.mvc.market.ProductRepository;
import ru.gb.mvc.market.ProductRepositoryImpl;

import javax.persistence.EntityManager;
import java.util.List;

@Configuration
public class AppConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
    }

    @Bean
    public SpringResourceTemplateResolver templateResolver() {
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        templateResolver.setPrefix("/WEB-INF/templates/");
        templateResolver.setSuffix(".html");
        return templateResolver;
    }

    @Bean
    public SpringTemplateEngine templateEngine() {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver());
        return templateEngine;
    }

    @Bean
    public ThymeleafViewResolver thymeleafViewResolver() {
        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
        viewResolver.setCharacterEncoding("UTF-8");
        viewResolver.setTemplateEngine(templateEngine());
        return viewResolver;
    }

    @Bean
    public List<Product> productList() {
        return List.of(
                new Product("Gold", 999),
                new Product("Silver", 888),
                new Product("Bronze", 777),
                new Product("Wood", 666),
                new Product("Paper", 555)
        );
    }

    @Bean
    @Scope("prototype")
    public Cart cart(ProductRepository productRepository) {
        return new CartImpl(productRepository) ;
    }

    @Bean
    public ProductRepository productRepository(List<Product> productList) {
        return new ProductRepositoryImpl(productList);
    }

    @Bean
    public EntityManager entityManager() {
        return new CommonEntityManager().getEm();
    }

}
