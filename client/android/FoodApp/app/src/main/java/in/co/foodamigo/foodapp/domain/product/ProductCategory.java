package in.co.foodamigo.foodapp.domain.product;

import java.io.Serializable;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class ProductCategory extends RealmObject implements Serializable {

    private static final long serialVersionUID = 1;

    @PrimaryKey
    private long id;
    private String name;
    private RealmList<ProductCategory> subCategories;
    private RealmList<Product> products;

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

    public RealmList<Product> getProducts() {
        return products;
    }

    public void setProducts(RealmList<Product> products) {
        this.products = products;
    }

    public RealmList<ProductCategory> getSubCategories() {
        return subCategories;
    }

    public void setSubCategories(RealmList<ProductCategory> subCategories) {
        this.subCategories = subCategories;
    }

}