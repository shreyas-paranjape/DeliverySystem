package in.co.foodamigo.foodapp.module.order.model;

import java.util.List;
import java.util.Random;

import in.co.foodamigo.foodapp.infra.persist.RealmManager;
import in.co.foodamigo.foodapp.module.order.view.model.OrderViewModel;

public class OrderRepository {

    public static Order createOrderAndSave(OrderViewModel orderViewModel) {
        Order order = new Order();
        order.setId(new Random().nextLong());
        order.getOrderItems().addAll(orderViewModel.getOrderItems());
        order.setTotal(orderViewModel.total.get());
        RealmManager.persist(order);
        return order;
    }

    private static void calculateTotal(Order order) {
        final List<OrderItem> orderItems = order.getOrderItems();
        double total = 0;
        for (OrderItem orderItem : orderItems) {
            total += orderItem.getCharge();
        }
        order.setTotal(total);
    }
}
