package ru.gb.mvc.market;

import org.springframework.stereotype.Service;
import ru.gb.mvc.database.SaleDAO;
import ru.gb.mvc.domain.Client;
import ru.gb.mvc.domain.Product;
import ru.gb.mvc.domain.Sale;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SalesService {

    private SaleDAO salePg;

    public SalesService(SaleDAO salePg) {
        this.salePg = salePg;
    }

    List<Product> getBoughtProductsByClientId(long clientId) {
        return salePg.getSalesByClientId(clientId).stream().
                map(Sale::getProduct).distinct().collect(Collectors.toList());
    }

    List<Client> getClientsByBoughtProductId(long productId) {
        return salePg.getSalesByProductId(productId).stream().
                map(Sale::getClient).distinct().collect(Collectors.toList());
    }

}
