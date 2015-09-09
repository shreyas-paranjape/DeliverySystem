package in.co.foodamigo.foodapp.module.order.model;

import org.parceler.Parcel;

import in.co.foodamigo.foodapp.module.catalogue.model.Product;
import io.realm.OrderItemRealmProxy;
import io.realm.RealmObject;

@Parcel(implementations = {OrderItemRealmProxy.class},
        value = Parcel.Serialization.BEAN,
        analyze = {OrderItem.class})
public class OrderItem extends RealmObject {

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
