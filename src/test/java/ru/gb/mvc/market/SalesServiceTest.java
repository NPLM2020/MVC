package ru.gb.mvc.market;

import org.junit.jupiter.api.Test;
import ru.gb.mvc.database.CommonEntityManager;
import ru.gb.mvc.database.postgres.SalePg;
import ru.gb.mvc.domain.Client;
import ru.gb.mvc.domain.Product;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SalesServiceTest {

    private SalesService salesService =
            new SalesService(
                    new SalePg(
                            new CommonEntityManager().getEm()));

    @Test
    void getBoughtProductsByClientId() {
        List<Product> productList = salesService.getBoughtProductsByClientId(1);
    }

    @Test
    void getClientsByBoughtProductId() {
        List<Client> clientList = salesService.getClientsByBoughtProductId(1);
    }
}