package ru.gb.mvc.database.postgres;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.gb.mvc.database.CommonEntityManager;
import ru.gb.mvc.domain.Sale;

import javax.persistence.EntityManager;
import java.util.List;

class SalePgTest {

    private EntityManager entityManager = new CommonEntityManager().getEm();
    private SalePg salePg = new SalePg(entityManager);

    @Test
    void addSale() {
        ProductPg productPg = new ProductPg(entityManager);
        ClientPg clientPg = new ClientPg(entityManager);
        Sale sale = new Sale(productPg.findById(1L), clientPg.getClientById(2L), 999f);
        salePg.addSale(sale);
    }

    @Test
    void getSaleById() {
        Sale sale = salePg.getSaleById(1);
        Assertions.assertEquals(sale.getId(), 1L);
    }

    @Test
    void updateSale() {
        Sale sale = salePg.getSaleById(3);
        sale.setSalePrice(999.900f);
        salePg.updateSale(sale);
    }

    @Test
    void deleteSaleById() {

    }

    @Test
    void getSalesByClientId() {
        List<Sale> saleList = salePg.getSalesByClientId(2);
    }

    @Test
    void getSalesByProductId() {
        List<Sale> saleList = salePg.getSalesByProductId(1);
    }
}