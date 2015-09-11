package in.co.foodamigo.foodapp.module.order.model;

import java.util.List;
import java.util.Random;

import in.co.foodamigo.foodapp.infra.persist.RealmManager;
import in.co.foodamigo.foodapp.module.catalogue.model.Product;
import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmQuery;

public class OrderManagerImpl implements OrderManager {

    private static final String TAG = OrderManager.class.getName();
    private final Order order;

    public OrderManagerImpl() {
        order = new Order();
        order.setId(new Random().nextLong());
    }

    @Override
    public void modifyItem(Product product, int quantity) {
        OrderItem orderItem = getItemForProduct(product);
        if (orderItem == null) {
            orderItem = new OrderItem();
            orderItem.setProduct(product);
            orderItem.setOrder(order);
            order.getOrderItems().add(orderItem);
        }
        changeProductQuantity(orderItem, quantity);
    }

    private OrderItem getItemForProduct(Product product) {
        final RealmList<OrderItem> orderItems = order.getOrderItems();
        for (OrderItem item : orderItems) {
            if (item.getProduct().getId() == product.getId()) {
                return item;
            }
        }
        return null;
    }

    @Override
    public int cartSize() {
        final RealmList<OrderItem> orderItems = order.getOrderItems();
        int size = 0;
        for (OrderItem item : orderItems) {
            size += item.getQuantity();
        }
        return size;
    }

    @Override
    public Order getOrder() {
        return order;
    }

    @Override
    public Order getOrderById(long order_id) {
        RealmQuery<Order> getOrder = Realm.getDefaultInstance().where(Order.class);
        getOrder.equalTo("id", order_id);
        return getOrder.findFirst();
    }

    private void changeProductQuantity(OrderItem productOrderItem, int quantity) {
        productOrderItem.setQuantity(productOrderItem.getQuantity() + quantity);
        productOrderItem.setCharge(
                productOrderItem.getProduct().getRate() * productOrderItem.getQuantity());
    }

    @Override
    public List<Order> getOrders() {
        RealmQuery<Order> orders = Realm.getDefaultInstance().where(Order.class);
        return orders.findAll();
    }

    @Override
    public void placeOrder() {
        RealmManager.persist(order);
    }
}
