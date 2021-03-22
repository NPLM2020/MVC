package ru.gb.mvc.database;

import ru.gb.mvc.domain.Sale;

import java.util.List;

public interface SaleDAO {
    Sale addSale(Sale sale);
    Sale getSaleById(long id);
    Sale updateSale(Sale sale);
    void deleteSaleById(long id);
    List<Sale> getSalesByClientId(long clientId);
    List<Sale> getSalesByProductId(long productId);
}
