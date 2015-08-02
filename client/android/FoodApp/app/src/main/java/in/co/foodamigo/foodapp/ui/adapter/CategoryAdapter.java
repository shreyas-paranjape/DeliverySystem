package in.co.foodamigo.foodapp.ui.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;

import in.co.foodamigo.foodapp.databinding.GroupCategoryBinding;
import in.co.foodamigo.foodapp.databinding.ItemProductBinding;
import in.co.foodamigo.foodapp.domain.product.Product;
import in.co.foodamigo.foodapp.domain.product.ProductCategory;
import in.co.foodamigo.foodapp.util.IOUtil;
import io.realm.Realm;
import io.realm.RealmQuery;

public class CategoryAdapter
        extends AbstractExpandableListAdapter<ProductCategory, Product> {
    private Bitmap bm;


    public CategoryAdapter(Context context) {
        super(context);
        RealmQuery<ProductCategory> query = Realm.getDefaultInstance().where(ProductCategory.class);
        query.isNotNull("subCategories");
        groupList = query.findAll();
        for (ProductCategory category : groupList) {
            childMapping.put(category, category.getProducts());
        }
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupCategoryBinding binding = GroupCategoryBinding.inflate(inflater);
        binding.setProductCategory((ProductCategory) getGroup(groupPosition));
        convertView = binding.getRoot();
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ItemProductBinding binding = ItemProductBinding.inflate(inflater);
        if (bm == null) {
            bm = IOUtil.getBitmapFromAsset(context, "food.jpg");
        }
        binding.imgProduct.setImageBitmap(bm);
        convertView = binding.getRoot();
        binding.setProduct((Product) getChild(groupPosition, childPosition));
        return convertView;
    }

}

/*RealmQuery<ProductCategory> allCat =
                Realm.getDefaultInstance().where(ProductCategory.class);
        RealmResults<ProductCategory> leafQueryResult = allCat.findAll();
        for (ProductCategory category : leafQueryResult) {
            if (category.getProducts() != null &&
                    (category.getProducts().size() > 0)) {
                childMapping.put(category, category.getProducts());
            }
            groupList.add(category);
        }*/

        /*RealmQuery<ProductCategory> leafQuery = Realm.getDefaultInstance().where(ProductCategory.class);
        leafQuery.isNotNull("parent");
        this.groupList = leafQuery.findAll();
        this.childMapping = new HashMap<>();
        for (ProductCategory category : groupList) {
            RealmQuery<Product> productsForCategory = Realm.getDefaultInstance().where(Product.class);
            productsForCategory.equalTo("productCategory.id", category.getId());
            childMapping.put(category, productsForCategory.findAll());
        }*/
