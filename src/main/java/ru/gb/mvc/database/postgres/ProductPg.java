package ru.gb.mvc.database.postgres;

import org.springframework.stereotype.Component;
import ru.gb.mvc.database.FindInDatabaseException;
import ru.gb.mvc.database.ProductDAO;
import ru.gb.mvc.domain.Product;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.List;

@Component
public class ProductPg implements ProductDAO {

    private final EntityManager em;

    public ProductPg(EntityManager em) {
        this.em = em;
    }

    @Override
    public Product findById(long id) {
        Query query = em.createQuery("select p from Product p where p.id = :id");
        query.setParameter("id", id);
        Product product;
        try {
            Object obj = query.getSingleResult();
            product = (Product) obj;
            return product;
        } catch (NoResultException exception) {
            throw new FindInDatabaseException("No data for product with id: " + id, exception);
        }
    }

    @Override
    public List<Product> findAll() {
        Query query = em.createQuery("select p from Product p");
        List<Product> products;
        try {
            products = (List<Product>) query.getResultList();
            return products;
        } catch (NoResultException exception) {
            throw new FindInDatabaseException("No data for products in the database", exception);
        }
    }

    @Override
    public void deleteById(long id) {
        em.getTransaction().begin();
        Query query = em.createQuery("delete from Product p where p.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
        em.getTransaction().commit();
    }

    @Override
    public Product saveOrUpdate(Product product) {
        em.getTransaction().begin();
        try {
            findById(product.getId());
            em.merge(product);
        } catch (FindInDatabaseException exception) {
            em.persist(product);
        } finally {
            em.getTransaction().commit();
        }
        return findById(product.getId());
    }
}
