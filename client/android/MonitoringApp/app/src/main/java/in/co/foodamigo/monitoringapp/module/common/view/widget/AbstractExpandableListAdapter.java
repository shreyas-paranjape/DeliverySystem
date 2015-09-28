package in.co.foodamigo.monitoringapp.module.common.view.widget;


import android.content.Context;
import android.view.LayoutInflater;
import android.widget.BaseExpandableListAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractExpandableListAdapter<G, C> extends BaseExpandableListAdapter {

    protected final Context context;
    protected final LayoutInflater inflater;
    protected List<G> groupList;
    protected Map<G, List<C>> childMapping;

    public AbstractExpandableListAdapter(Context context) {
        this.context = context;
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        groupList = new ArrayList<>();
        childMapping = new HashMap<>();
    }

    public AbstractExpandableListAdapter(Context context, List<G> groupList,
                                         Map<G, List<C>> childMapping) {
        this(context);
        this.groupList = groupList;
        this.childMapping = childMapping;
    }

    @Override
    public int getGroupCount() {
        return groupList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return childMapping.get(groupList.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groupList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return childMapping.get(groupList.get(groupPosition)).get(childPosition);
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
        return false;
    }
}
