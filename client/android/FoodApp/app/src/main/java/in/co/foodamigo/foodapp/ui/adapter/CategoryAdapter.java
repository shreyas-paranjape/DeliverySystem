package in.co.foodamigo.foodapp.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import in.co.foodamigo.foodapp.databinding.GroupCategoryBinding;
import in.co.foodamigo.foodapp.databinding.ItemProductBinding;
import in.co.foodamigo.foodapp.domain.product.Product;
import in.co.foodamigo.foodapp.domain.product.ProductCategory;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

public class CategoryAdapter
        extends AbstractExpandableListAdapter<ProductCategory, Product> {


    public CategoryAdapter(Context context) {
        super(context);
        RealmQuery<ProductCategory> allCat =
                Realm.getDefaultInstance().where(ProductCategory.class);
        RealmResults<ProductCategory> leafQueryResult = allCat.findAll();
        for (ProductCategory category : leafQueryResult) {
            if (category.getProducts() != null &&
                    (category.getProducts().size() > 0)) {
                childMapping.put(category, category.getProducts());
            }
            groupList.add(category);
        }

        /*RealmQuery<ProductCategory> leafQuery = Realm.getDefaultInstance().where(ProductCategory.class);
        leafQuery.isNotNull("parent");
        RealmResults<ProductCategory> leafQueryResult = leafQuery.findAll();
        this.groupList = leafQueryResult.subList(0, leafQueryResult.size());
        this.childMapping = new HashMap<>();
        for (ProductCategory category : groupList) {
            childMapping.put(category, category.getProducts());
        }*/
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
        convertView = binding.getRoot();
        binding.setProduct((Product) getChild(groupPosition, childPosition));
        return convertView;
    }

}
