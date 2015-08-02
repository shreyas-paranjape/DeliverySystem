package in.co.foodamigo.foodapp.ui.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

import in.co.foodamigo.foodapp.R;

@SuppressLint("InflateParams")
public class NavigationDrawerAdapter extends AbstractExpandableListAdapter<String, String> {

    public NavigationDrawerAdapter(Context context, List<String> groupList,
                                   Map<String, List<String>> childMapping) {
        super(context, groupList, childMapping);
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.group_drawer, null);
        }
        TextView lblListHeader = (TextView) convertView.findViewById(R.id.tv_drawer_header);
        lblListHeader.setTypeface(null, Typeface.BOLD);
        lblListHeader.setText((String) getGroup(groupPosition));
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView,
                             ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_drawer_link, null);
        }
        TextView txtListChild = (TextView) convertView.findViewById(R.id.tv_drawer_link);
        txtListChild.setText((String) getChild(groupPosition, childPosition));
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
