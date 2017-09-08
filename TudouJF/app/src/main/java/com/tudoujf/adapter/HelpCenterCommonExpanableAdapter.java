package com.tudoujf.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.tudoujf.R;
import com.tudoujf.bean.SafetyControlActBean;

import java.util.List;

/**
 * * ====================================================================
 * name:            HelpCenterCommonExpanableAdapter
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：            2017/9/13
 * description：  HelpCenterCommonActivity帮助中心通用activity中的ExpandableList的adapter
 * history：
 * * ====================================================================
 */

public class HelpCenterCommonExpanableAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<SafetyControlActBean> groupList;

    public HelpCenterCommonExpanableAdapter(Context context, List<SafetyControlActBean> groupList) {
        this.context = context;
        this.groupList = groupList;
    }

    /**
     * 获取组的个数
     */
    @Override
    public int getGroupCount() {
        return groupList.size();
    }

    /**
     * 获取指定组中的子元素个数
     */
    @Override
    public int getChildrenCount(int groupPosition) {
        return 1;
    }

    /**
     * 获取指定组中的数据
     */
    @Override
    public Object getGroup(int groupPosition) {
        return groupList.get(groupPosition);
    }

    /**
     * 获取指定组中的指定子元素数据。
     */
    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return groupList.get(groupPosition);
    }

    /**
     * 获取指定组的ID，这个组ID必须是唯一的
     */
    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    /**
     * 获取指定组中的指定子元素ID
     */
    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    /**
     * 组和子元素是否持有稳定的ID,也就是底层数据的改变不会影响到它们。
     */
    @Override
    public boolean hasStableIds() {
        return true;
    }

    /**
     * 获取显示指定组的视图对象
     *
     * @param groupPosition 组位置
     * @param isExpanded    该组是展开状态还是伸缩状态
     * @param convertView   重用已有的视图对象
     * @param parent        返回的视图对象始终依附于的视图组
     * @return     组控件的视图
     * @see android.widget.ExpandableListAdapter#getGroupView(int, boolean, View,
     * ViewGroup)
     */

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupHolder groupHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.lvitem_helpcentercommonact, parent,false);
            groupHolder = new GroupHolder();
            groupHolder.textView1 = (TextView) convertView.findViewById(R.id.tv_lvitem_helpcentercommon_titile);
            groupHolder.textView2 = (TextView) convertView.findViewById(R.id.tv_lvitem_helpcentercommon_arrow);
            convertView.setTag(groupHolder);
        } else {
            groupHolder = (GroupHolder) convertView.getTag();
        }
        if (!isExpanded){
            groupHolder.textView2.setBackgroundResource(R.drawable.xvector_downarrowlarge);
        }else {
            groupHolder.textView2.setBackgroundResource(R.drawable.xvector_uparrowlarge);

        }
        groupHolder.textView1.setText(groupList.get(groupPosition).getTitle());
        return convertView;
    }
    /**
     *
     * 是否选中指定位置上的子元素。
     *
     * @param groupPosition      组的位置
     * @param childPosition    子view的位置
     * @return    子view视图
     * @see android.widget.ExpandableListAdapter#isChildSelectable(int, int)
     */
    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ItemHolder itemHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.lvitem_safetycontrol_item, parent,false);
            itemHolder = new ItemHolder();
            itemHolder.txt = (TextView) convertView.findViewById(R.id.tv_lvitem2_safetycontrol2);
            convertView.setTag(itemHolder);
        } else {
            itemHolder = (ItemHolder) convertView.getTag();
        }
        itemHolder.txt.setText(groupList.get(groupPosition).getContent());
        return convertView;
    }

    /**
     * 是否选中指定位置上的子元素。
     *
     * @param groupPosition   组的位置
     * @param childPosition  子view的位置
     * @return   ""
     * @see android.widget.ExpandableListAdapter#isChildSelectable(int, int)
     */
    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    class GroupHolder {
        public TextView textView1,textView2;
    }

    class ItemHolder {

        public TextView txt;
    }
}
