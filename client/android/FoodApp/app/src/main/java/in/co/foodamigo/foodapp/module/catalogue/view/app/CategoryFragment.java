package in.co.foodamigo.foodapp.module.catalogue.view.app;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.parceler.Parcels;

import in.co.foodamigo.foodapp.R;
import in.co.foodamigo.foodapp.module.catalogue.model.ProductCategory;
import in.co.foodamigo.foodapp.module.catalogue.view.widget.CategoryAdapter;

public class CategoryFragment extends Fragment {

    private ProductCategory parent;

    @Override
    public void setArguments(Bundle args) {
        super.setArguments(args);
        parent = Parcels.unwrap(args.getParcelable("parent"));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_category, container, false);
        initListView(rootView);
        return rootView;
    }

    private void initListView(View rootView) {
        RecyclerView productsRecyclerView = (RecyclerView) rootView.findViewById(R.id.rv_category);
        productsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        productsRecyclerView.setAdapter(new CategoryAdapter(getActivity(), parent));
    }
}
