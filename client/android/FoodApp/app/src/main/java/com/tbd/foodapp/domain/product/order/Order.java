package com.tbd.foodapp.domain.product.order;

import com.orm.SugarRecord;
import com.orm.dsl.Table;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;

public class Order extends RealmObject implements Serializable {

    private static final long serialVersionUID = 1;

    private long id;
    private long number;
    private RealmList<OrderItem> orderItems;
    private double tax;
    private double total;
    private String status;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public RealmList<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(RealmList<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    public double getTax() {
        return tax;
    }

    public void setTax(double tax) {
        this.tax = tax;
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
}
