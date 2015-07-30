package in.co.foodamigo.foodapp.domain.product;

import in.co.foodamigo.foodapp.domain.product.supply.Supplier;

import java.io.Serializable;

import io.realm.RealmList;
import io.realm.RealmObject;

public class Product extends RealmObject implements Serializable {

    private long id;
    private String name;
    private double rate;
    private RealmList<Supplier> productSuppliers;

    //private ProductCategory productCategory;
    //public ProductCategory getProductCategory() {
    //return productCategory;
    //}
    //public void setProductCategory(ProductCategory productCategory) {
    //  this.productCategory = productCategory;
    //}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public RealmList<Supplier> getProductSuppliers() {
        return productSuppliers;
    }

    public void setProductSuppliers(RealmList<Supplier> productSuppliers) {
        this.productSuppliers = productSuppliers;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }
}
