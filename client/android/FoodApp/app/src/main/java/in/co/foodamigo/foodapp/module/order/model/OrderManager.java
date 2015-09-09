package in.co.foodamigo.foodapp.module.order.model;

import java.util.List;

import in.co.foodamigo.foodapp.module.catalogue.model.Product;

public interface OrderManager {

    void modifyItem(Product product, int quantity);

    int cartSize();

    Order getOrder();

    Order getOrderById(long order_id);

    List<Order> getOrders();

    void placeOrder();

}
