package in.co.foodamigo.foodapp.module.order.model;

import java.util.Random;

import in.co.foodamigo.foodapp.module.catalogue.model.Product;
import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmQuery;

public class OrderManagerImpl implements OrderManager {

    private static final String TAG = OrderManager.class.getName();
    private final Order order;

    public OrderManagerImpl() {
        Realm.getDefaultInstance().beginTransaction();
        order = Realm.getDefaultInstance().createObject(Order.class);
        order.setId(new Random().nextLong());
        Realm.getDefaultInstance().commitTransaction();
    }

    @Override
    public void modifyItem(Product product, int quantity) {
        Realm.getDefaultInstance().beginTransaction();
        OrderItem orderItem = getItemForProduct(product);
        if (orderItem == null) {
            orderItem = Realm.getDefaultInstance().createObject(OrderItem.class);
            orderItem.setProduct(product);
            orderItem.setOrder(order);
            order.getOrderItems().add(orderItem);
        }
        changeProductQuantity(orderItem, quantity);
        Realm.getDefaultInstance().commitTransaction();
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
}
