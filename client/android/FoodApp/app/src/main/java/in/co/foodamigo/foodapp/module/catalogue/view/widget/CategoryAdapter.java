package in.co.foodamigo.foodapp.module.catalogue.view.widget;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import de.greenrobot.event.EventBus;
import in.co.foodamigo.foodapp.databinding.ItemProductBinding;
import in.co.foodamigo.foodapp.module.catalogue.model.Product;
import in.co.foodamigo.foodapp.module.catalogue.model.ProductCategory;
import in.co.foodamigo.foodapp.module.order.view.app.CartFragment;

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
                .load(product.getDish_url())
                .into(viewHolder.productCardView.imgProduct, new Callback.EmptyCallback() {
                    @Override
                    public void onError() {
                        Log.d(TAG, "Could not load image");
                    }
                });


        viewHolder.productCardView.btnProductAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(
                        new CartFragment.ModifyCartEvent(product, CartFragment.CartAction.ADD));
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