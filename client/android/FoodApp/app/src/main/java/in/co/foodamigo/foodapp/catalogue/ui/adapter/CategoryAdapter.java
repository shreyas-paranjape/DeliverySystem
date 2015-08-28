package in.co.foodamigo.foodapp.catalogue.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import in.co.foodamigo.foodapp.catalogue.domain.Product;
import in.co.foodamigo.foodapp.catalogue.domain.ProductCategory;
import in.co.foodamigo.foodapp.databinding.ItemProductBinding;

public class CategoryAdapter
        extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    private static final String TAG = CategoryAdapter.class.getName();
    protected final Context context;
    protected final ProductCategory productCategory;
    protected final LayoutInflater inflater;

    public CategoryAdapter(Context context, ProductCategory productCategory) {
        this.context = context;
        this.productCategory = productCategory;
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(ItemProductBinding.inflate(inflater));
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        final Product product = productCategory.getProducts().get(i);
        viewHolder.productCardView.setProduct(product);

        Picasso.with(context)
                .load("file:///android_asset/food.jpg")
                        //.load(product.getDish_url())
                        //.resize(200, 200)
                        //.centerCrop()
                .into(viewHolder.productCardView.imgProduct, new Callback.EmptyCallback() {
                    @Override
                    public void onError() {
                        Log.d(TAG, "Could not load image");
                    }
                });
        viewHolder.productCardView.btnProductAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return productCategory.getProducts().size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ItemProductBinding productCardView;

        public ViewHolder(ItemProductBinding productCardView) {
            super(productCardView.getRoot());
            this.productCardView = productCardView;
        }
    }
}


/*public CategoryAdapter(Context context, ProductCategory parent) {
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
    }*/