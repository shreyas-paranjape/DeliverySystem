package in.co.foodamigo.foodapp.module.order.model.repository;

import in.co.foodamigo.foodapp.module.order.model.entity.Order;
import in.co.foodamigo.foodapp.module.profile.model.entity.Customer;

import java.util.List;

public interface OrderRepository {

    List<Order> getPastOrders(Customer customer);

    Order getCurrentOrder();

}
