package in.co.foodamigo.foodapp.module.common.view.widget;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.List;

import in.co.foodamigo.foodapp.R;
import in.co.foodamigo.foodapp.module.common.model.Item;
import in.co.foodamigo.foodapp.module.common.model.ItemGroup;


public class NavigationDrawerAdapter extends BaseExpandableListAdapter {

    private final List<ItemGroup> itemGroups;
    protected final LayoutInflater inflater;

    public NavigationDrawerAdapter(Context context, List<ItemGroup> itemGroups) {
        this.itemGroups = itemGroups;
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.group_drawer, parent, false);
        }
        TextView lblListHeader = (TextView) convertView.findViewById(R.id.tv_drawer_header);
        lblListHeader.setTypeface(null, Typeface.BOLD);
        lblListHeader.setText(((ItemGroup) getGroup(groupPosition)).getName());
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView,
                             ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_drawer_link, parent, false);
        }
        TextView txtListChild = (TextView) convertView.findViewById(R.id.tv_drawer_link);
        txtListChild.setText(((Item) getChild(groupPosition, childPosition)).getName());
        return convertView;
    }

    @Override
    public int getGroupCount() {
        return itemGroups.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return itemGroups.get(groupPosition).getItems().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return itemGroups.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return itemGroups.get(groupPosition).getItems().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
