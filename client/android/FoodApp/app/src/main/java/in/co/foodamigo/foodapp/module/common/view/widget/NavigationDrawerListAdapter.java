package in.co.foodamigo.foodapp.module.common.view.widget;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import in.co.foodamigo.foodapp.R;
import in.co.foodamigo.foodapp.module.common.model.Item;

public class NavigationDrawerListAdapter extends ArrayAdapter<Item> {

    protected final LayoutInflater inflater;

    public NavigationDrawerListAdapter(Context context, int resource, List<Item> objects) {
        super(context, resource, objects);
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_drawer_link, parent, false);
        }
        TextView txtListChild = (TextView) convertView.findViewById(R.id.tv_drawer_link);
        txtListChild.setText(((Item) getItem(position)).getName());
        return convertView;
    }
}
