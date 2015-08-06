package in.co.foodamigo.foodapp.module.catalogue.domain;

import org.parceler.Parcel;
import org.parceler.ParcelPropertyConverter;

import in.co.foodamigo.foodapp.module.common.entity.Supplier;
import in.co.foodamigo.foodapp.module.catalogue.infra.SuppListParcelConverter;
import io.realm.ProductRealmProxy;
import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

@Parcel(implementations = {ProductRealmProxy.class},
        value = Parcel.Serialization.BEAN,
        analyze = {Product.class})
public class Product extends RealmObject {

    @PrimaryKey
    private long id;

    private String name;
    private double rate;
    private RealmList<Supplier> productSuppliers;
    private ProductCategory productCategory;

    public Product() {
    }

    public Product(String name) {
        this.name = name;
    }

    public ProductCategory getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(ProductCategory productCategory) {
        this.productCategory = productCategory;
    }

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

    @ParcelPropertyConverter(SuppListParcelConverter.class)
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
