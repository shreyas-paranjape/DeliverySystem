package in.co.foodamigo.monitoringapp.module.order.view.widget;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import in.co.foodamigo.monitoringapp.databinding.ItemOrderBinding;
import in.co.foodamigo.monitoringapp.module.order.model.Order;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder> {

    protected final Context context;
    protected final LayoutInflater inflater;
    protected final List<Order> orderList;

    public OrderAdapter(Context context, List<Order> orderList) {
        this.context = context;
        this.inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.orderList = orderList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(ItemOrderBinding.inflate(inflater));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.productCardView.setOrder(orderList.get(position));
    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ItemOrderBinding productCardView;

        public ViewHolder(ItemOrderBinding productCardView) {
            super(productCardView.getRoot());
            this.productCardView = productCardView;
        }
    }
}
