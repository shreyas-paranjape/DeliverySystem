package in.co.foodamigo.monitoringapp.module.order.view.model;

import android.util.Log;

import java.util.List;

import in.co.foodamigo.monitoringapp.module.catalogue.model.Product;
import in.co.foodamigo.monitoringapp.module.order.model.OrderItem;


public class OrderViewModelManagerImpl {

    private static final String TAG = OrderViewModelManagerImpl.class.getName();
    private final OrderViewModel order = new OrderViewModel();

    public void modifyItem(Product product, int quantity) {
        OrderItem orderItem = getItemForProduct(product);
        if (orderItem == null) {
            orderItem = new OrderItem();
            orderItem.setProduct(product);
            //orderItem.setOrder(order);
            order.getOrderItems().add(orderItem);
        }
        changeProductQuantity(orderItem, quantity);
        changeTotal();
        order.quantity.set(order.quantity.get() + quantity);
        Log.i(TAG, "Order : " + order);
    }


    public int cartSize() {
        final List<OrderItem> orderItems = order.getOrderItems();
        int size = 0;
        for (OrderItem item : orderItems) {
            size += item.getQuantity();
        }
        return size;
    }

    public OrderViewModel getOrder() {
        return order;
    }

    private OrderItem getItemForProduct(Product product) {
        final List<OrderItem> orderItems = order.getOrderItems();
        for (OrderItem item : orderItems) {
            if (item.getProduct().getId() == product.getId()) {
                return item;
            }
        }
        return null;
    }

    private void changeProductQuantity(OrderItem productOrderItem, int quantity) {
        productOrderItem.setQuantity(productOrderItem.getQuantity() + quantity);
        if (productOrderItem.getQuantity() <= 0) {
            order.getOrderItems().remove(productOrderItem);
        } else {
            productOrderItem.setCharge(
                    productOrderItem.getProduct().getRate() * productOrderItem.getQuantity());
        }
    }


    private void changeTotal() {
        final List<OrderItem> orderItems = order.getOrderItems();
        double total = 0;
        for (OrderItem orderItem : orderItems) {
            total += orderItem.getCharge();
        }
        order.total.set(total);
    }

}
