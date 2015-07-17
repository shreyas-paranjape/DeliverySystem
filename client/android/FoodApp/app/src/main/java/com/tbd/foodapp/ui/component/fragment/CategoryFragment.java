package com.tbd.foodapp.ui.component.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.tbd.foodapp.R;
import com.tbd.foodapp.ui.adapter.CategoryAdapter;

public class CategoryFragment extends Fragment {

    private ExpandableListView listView;
    private CategoryAdapter categoryAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_category, container, false);
        listView = (ExpandableListView) rootView.findViewById(R.id.elv_category);
        categoryAdapter = new CategoryAdapter(getActivity());
        listView.setAdapter(categoryAdapter);
        return rootView;
    }
}
