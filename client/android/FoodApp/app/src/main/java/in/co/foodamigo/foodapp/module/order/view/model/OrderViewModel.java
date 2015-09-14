package in.co.foodamigo.foodapp.module.order.view.model;

import android.databinding.ObservableDouble;
import android.databinding.ObservableInt;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import in.co.foodamigo.foodapp.module.order.model.OrderItem;

public class OrderViewModel {

    public final ObservableDouble total = new ObservableDouble();
    public final ObservableInt quantity = new ObservableInt();

    private final List<OrderItem> orderItems = new ArrayList<>();

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

   /* public double getTotal() {
        return total.get();
    }

     //@Bindable
    public void setTotal(double total) {
        this.total.set(total);
        //notifyPropertyChanged(BR.total);
    }


    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    //@Bindable
    public int getQuantity() {
        return quantity.get();
    }

    public void setQuantity(int quantity) {
        this.quantity.set(quantity);
        //notifyPropertyChanged(BR.quantity);
    }*/

    @Override
    public String toString() {
        return "OrderViewModel{" +
                "total=" + total.get() +
                ", quantity=" + quantity.get() +
                ", orderItems=" + Arrays.toString(orderItems.toArray()) +
                '}';
    }
}
