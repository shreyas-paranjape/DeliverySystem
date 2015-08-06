package in.co.foodamigo.foodapp.module.catalogue;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import in.co.foodamigo.foodapp.databinding.GroupCategoryBinding;
import in.co.foodamigo.foodapp.databinding.ItemProductBinding;
import in.co.foodamigo.foodapp.domain.product.Product;
import in.co.foodamigo.foodapp.domain.product.ProductCategory;
import in.co.foodamigo.foodapp.component.adapter.AbstractExpandableListAdapter;

public class CategoryAdapter
        extends AbstractExpandableListAdapter<ProductCategory, Product> {

    public CategoryAdapter(Context context, ProductCategory parent) {
        super(context);
        if (parent != null) {
            groupList = parent.getSubCategories();
            for (ProductCategory category : groupList) {
                childMapping.put(category, category.getProducts());
            }
        }
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        GroupCategoryBinding binding = GroupCategoryBinding.inflate(inflater);
        binding.setProductCategory((ProductCategory) getGroup(groupPosition));
        convertView = binding.getRoot();
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        ItemProductBinding binding = ItemProductBinding.inflate(inflater);
        // binding.imgProduct.setImageBitmap(bm);
        convertView = binding.getRoot();
        binding.setProduct((Product) getChild(groupPosition, childPosition));
        return convertView;
    }

}