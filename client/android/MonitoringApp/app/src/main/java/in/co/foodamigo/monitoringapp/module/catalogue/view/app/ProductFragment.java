package in.co.foodamigo.monitoringapp.module.catalogue.view.app;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import in.co.foodamigo.monitoringapp.R;
import in.co.foodamigo.monitoringapp.module.catalogue.model.Product;
import in.co.foodamigo.monitoringapp.module.catalogue.view.widget.ProductAdapter;

public class ProductFragment extends Fragment {

    private List<Product> productList;

    @Override
    public void setArguments(Bundle args) {
        super.setArguments(args);
        productList = new ArrayList<>();
        createDummyProducts();
    }

    private void createDummyProducts() {
        productList.add(getDummyProduct());
        productList.add(getDummyProduct());
        productList.add(getDummyProduct());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        RecyclerView recycler = (RecyclerView) inflater.inflate(
                R.layout.fragment_product, container, false);
        recycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        recycler.setAdapter(new ProductAdapter(getActivity(), productList));
        return recycler;
    }

    public Product getDummyProduct() {
        Product p = new Product();
        p.setName("dummy" + new Random().nextLong());
        return p;
    }
}
