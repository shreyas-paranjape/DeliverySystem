package in.co.foodamigo.foodapp.module.order.model.service;

import in.co.foodamigo.foodapp.module.order.model.entity.Order;

public interface OrderService {

    boolean saveOrder(Order order);

    boolean cancelOrder(Order order);
}