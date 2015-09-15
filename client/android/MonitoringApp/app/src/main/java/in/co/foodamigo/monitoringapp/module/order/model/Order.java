package in.co.foodamigo.monitoringapp.module.order.model;

import org.parceler.Parcel;
import org.parceler.ParcelPropertyConverter;

import java.util.Date;

import in.co.foodamigo.monitoringapp.module.order.infra.util.OrderItemParcelConverter;
import io.realm.OrderRealmProxy;
import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

@Parcel(implementations = {OrderRealmProxy.class},
        value = Parcel.Serialization.BEAN,
        analyze = {Order.class})
public class Order extends RealmObject {

    @PrimaryKey
    private long id;

    private long number;
    private RealmList<OrderItem> orderItems = new RealmList<>();
    private double total;
    private String status = "NEW";
    private Date orderDate = new Date();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public RealmList<OrderItem> getOrderItems() {
        return orderItems;
    }

    @ParcelPropertyConverter(OrderItemParcelConverter.class)
    public void setOrderItems(RealmList<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }
}
