package ru.gb.mvc.database.postgres;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import ru.gb.mvc.database.FindInDatabaseException;
import ru.gb.mvc.database.SaleDAO;
import ru.gb.mvc.domain.Sale;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Component
public class SalePg implements SaleDAO {

    private final EntityManager em;

    public SalePg(EntityManager em) {
        this.em = em;
    }

    @Override
    public Sale addSale(Sale sale) {
        em.getTransaction().begin();
        em.persist(sale);
        em.getTransaction().commit();
        return getSaleById(sale.getId());
    }

    @Override
    public Sale getSaleById(long id) {
        Query query = em.createQuery("select sal from Sale sal where sal.id = :id");
        query.setParameter("id", id);
        return (Sale) query.getSingleResult();
    }

    @Override
    public Sale updateSale(Sale sale) {
        em.getTransaction().begin();
        em.merge(sale);
        em.getTransaction().commit();
        return getSaleById(sale.getId());
    }

    @Override
    public void deleteSaleById(long id) {
        em.getTransaction().begin();
        Query query = em.createQuery("delete from Sale sal where sal.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
        em.getTransaction().commit();
    }

    @Override
    public List<Sale> getSalesByClientId(long clientId) {
        Query query = em.createQuery("select sal from Sale sal where sal.client.id = :id");
        query.setParameter("id", clientId);
        return (List<Sale>) query.getResultList();
    }

    @Override
    public List<Sale> getSalesByProductId(long productId) {
        Query query = em.createQuery("select sal from Sale sal where sal.product.id = :id");
        query.setParameter("id", productId);
        return (List<Sale>) query.getResultList();
    }
}
