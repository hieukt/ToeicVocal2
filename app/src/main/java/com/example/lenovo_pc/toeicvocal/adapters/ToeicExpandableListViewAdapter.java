package com.example.lenovo_pc.toeicvocal.adapters;

import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.lenovo_pc.toeicvocal.R;
import com.example.lenovo_pc.toeicvocal.databases.models.CategoryModel;
import com.example.lenovo_pc.toeicvocal.databases.models.TopicModel;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Lenovo-PC on 3/5/2018.
 */

public class ToeicExpandableListViewAdapter extends BaseExpandableListAdapter {

    List<CategoryModel> categoryModelList;
    HashMap<String, List<TopicModel>> topicModelHashMap;

    public ToeicExpandableListViewAdapter(List<CategoryModel> categoryModelList, HashMap<String, List<TopicModel>> topicModelHashMap) {
        this.categoryModelList = categoryModelList;
        this.topicModelHashMap = topicModelHashMap;
    }

    @Override
    public int getGroupCount() {
        return categoryModelList.size();
    }

    //index cua children trong group
    @Override
    public int getChildrenCount(int i) {
        return topicModelHashMap.get(categoryModelList.get(i).name).size();
    }

    @Override
    public Object getGroup(int i) {
        return categoryModelList.get(i);
    }

    //i: index group, i1: index children
    @Override
    public Object getChild(int i, int i1) {
        return topicModelHashMap.get(categoryModelList.get(i).name).get(i1);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int i, boolean isExpanded, View view, ViewGroup viewGroup) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        view = layoutInflater.inflate(R.layout.item_list_category, viewGroup, false);

        CategoryModel categoryModel = (CategoryModel) getGroup(i);
        TextView tvCategory = view.findViewById(R.id.tv_category);
        TextView tvCategoryDes = view.findViewById(R.id.tv_category_des);
        ImageView ivArrow = view.findViewById(R.id.iv_arrow);
        CardView cvCategory = view.findViewById(R.id.cv_category);

        cvCategory.setCardBackgroundColor(Color.parseColor(categoryModel.color));

        if (isExpanded) {
            ivArrow.setImageResource(R.drawable.ic_arow_up_black_24dp);
        } else {
            ivArrow.setImageResource(R.drawable.ic_arrow_downward_black_24dp);
        }

        tvCategory.setText(categoryModel.name);

        String des = "";
        for (int j = 0; j < 5; j++) {
            des += topicModelHashMap.get(categoryModel.name).get(j).name;
            if (j != 4) {
                des += ", ";
            }
        }
        tvCategoryDes.setText(des);

        return view;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        view = layoutInflater.inflate(R.layout.item_list_topic, viewGroup, false);

        TopicModel topicModel = (TopicModel) getChild(i, i1);

        TextView tvTopic = view.findViewById(R.id.tv_name_topic);
        TextView tvLastTime = view.findViewById(R.id.tv_last_time);
        ProgressBar pbTopic = view.findViewById(R.id.pb_topic);

        tvTopic.setText(topicModel.name);
        if (topicModel.lastTime != null) {
            tvLastTime.setText(topicModel.lastTime);
        }

        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }
}