package in.co.foodamigo.foodapp.ui.component.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import in.co.foodamigo.foodapp.R;
import in.co.foodamigo.foodapp.ui.adapter.CategoryAdapter;

public class CategoryFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_category, container, false);
        initListView(rootView);
        return rootView;
    }

    private void initListView(View rootView) {
        final ExpandableListView listView =
                (ExpandableListView) rootView.findViewById(R.id.elv_category);
        listView.setAdapter(new CategoryAdapter(getActivity()));
        listView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            int previousItem = -1;

            @Override
            public void onGroupExpand(int groupPosition) {
                if (groupPosition != previousItem)
                    listView.collapseGroup(previousItem);
                previousItem = groupPosition;
            }
        });
    }
}
