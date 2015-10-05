package in.co.foodamigo.foodapp.module.order.model.repository;

import in.co.foodamigo.foodapp.module.order.model.entity.Order;
import in.co.foodamigo.foodapp.module.profile.model.entity.Customer;

import java.util.List;

public class OrderRepositoryImpl implements OrderRepository {

    @Override
    public List<Order> getPastOrders(Customer customer) {
        return null;
    }

    @Override
    public Order getCurrentOrder() {
        return null;
    }
}
