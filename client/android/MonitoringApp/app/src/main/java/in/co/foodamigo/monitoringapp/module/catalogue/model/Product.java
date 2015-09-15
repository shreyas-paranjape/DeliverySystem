package in.co.foodamigo.monitoringapp.module.catalogue.model;

import org.parceler.Parcel;

import io.realm.ProductRealmProxy;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

@Parcel(implementations = {ProductRealmProxy.class},
        value = Parcel.Serialization.BEAN,
        analyze = {Product.class})
public class Product extends RealmObject {

    @PrimaryKey
    private long id;

    private String name;
    private String description;
    private double rate;
    private Supplier productSupplier;
    private String dish_url;

    public Product() {
    }

    public Product(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Supplier getProductSupplier() {
        return productSupplier;
    }

    public void setProductSupplier(Supplier productSupplier) {
        this.productSupplier = productSupplier;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public String getDish_url() {
        return dish_url;
    }

    public void setDish_url(String dish_url) {
        this.dish_url = dish_url;
    }

}
