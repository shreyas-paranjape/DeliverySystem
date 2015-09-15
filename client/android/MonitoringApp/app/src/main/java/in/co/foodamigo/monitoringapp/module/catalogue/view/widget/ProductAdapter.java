package in.co.foodamigo.monitoringapp.module.catalogue.view.widget;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import in.co.foodamigo.monitoringapp.databinding.ItemProductBinding;
import in.co.foodamigo.monitoringapp.module.catalogue.model.Product;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    protected final Context context;
    protected final LayoutInflater inflater;
    protected final List<Product> productList;

    public ProductAdapter(Context context, List<Product> productList) {
        this.context = context;
        this.inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.productList = productList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(ItemProductBinding.inflate(inflater));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.productCardView.setProduct(productList.get(position));
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ItemProductBinding productCardView;

        public ViewHolder(ItemProductBinding productCardView) {
            super(productCardView.getRoot());
            this.productCardView = productCardView;
        }
    }
}
