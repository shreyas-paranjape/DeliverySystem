package in.co.foodamigo.foodapp.module.order.model;

import in.co.foodamigo.foodapp.module.catalogue.model.Product;

import java.io.Serializable;

import io.realm.RealmObject;

public class OrderItem extends RealmObject implements Serializable {

    private static final long serialVersionUID = 1;

    private Order order;
    private Product product;
    private int quantity;
    private double charge;

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getCharge() {
        return charge;
    }

    public void setCharge(double charge) {
        this.charge = charge;
    }
}
