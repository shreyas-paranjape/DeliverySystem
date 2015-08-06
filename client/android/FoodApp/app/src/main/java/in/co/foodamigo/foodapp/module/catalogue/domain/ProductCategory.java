package in.co.foodamigo.foodapp.module.catalogue.domain;

import org.parceler.Parcel;
import org.parceler.ParcelPropertyConverter;

import in.co.foodamigo.foodapp.module.catalogue.infra.CatListParcelConverter;
import in.co.foodamigo.foodapp.module.catalogue.infra.ProdListParcelConverter;
import io.realm.ProductCategoryRealmProxy;
import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

@Parcel(implementations = {ProductCategoryRealmProxy.class},
        value = Parcel.Serialization.BEAN,
        analyze = {ProductCategory.class})
public class ProductCategory extends RealmObject {


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

    @ParcelPropertyConverter(ProdListParcelConverter.class)
    public void setProducts(RealmList<Product> products) {
        this.products = products;
    }

    public RealmList<ProductCategory> getSubCategories() {
        return subCategories;
    }

    @ParcelPropertyConverter(CatListParcelConverter.class)
    public void setSubCategories(RealmList<ProductCategory> subCategories) {
        this.subCategories = subCategories;
    }

}