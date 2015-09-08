package in.co.foodamigo.foodapp.module.order.model;

import in.co.foodamigo.foodapp.module.catalogue.model.Product;

public interface OrderManager {
    void modifyItem(Product product, int quantity);

    int cartSize();

    Order getOrder();

    Order getOrderById(long order_id);
}
