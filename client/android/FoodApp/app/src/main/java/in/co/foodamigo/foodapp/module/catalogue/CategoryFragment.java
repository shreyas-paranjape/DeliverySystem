package in.co.foodamigo.foodapp.module.catalogue;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import org.parceler.Parcels;

import in.co.foodamigo.foodapp.R;
import in.co.foodamigo.foodapp.domain.product.ProductCategory;

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
        final ExpandableListView listView =
                (ExpandableListView) rootView.findViewById(R.id.elv_category);
        listView.setAdapter(new CategoryAdapter(getActivity(), parent));
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
