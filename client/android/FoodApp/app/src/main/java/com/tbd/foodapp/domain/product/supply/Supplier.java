package com.tbd.foodapp.domain.product.supply;

import com.orm.SugarRecord;
import com.orm.dsl.Column;
import com.orm.dsl.Table;
import com.tbd.foodapp.domain.common.Address;
import com.tbd.foodapp.domain.product.Product;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import io.realm.RealmObject;

public class Supplier extends RealmObject implements Serializable {

    private static final long serialVersionUID = 1;

    private long id;
    private String name;
    private Address address;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
