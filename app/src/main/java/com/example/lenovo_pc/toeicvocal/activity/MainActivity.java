package com.example.lenovo_pc.toeicvocal.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;

import com.example.lenovo_pc.toeicvocal.adapters.ToeicExpandableListViewAdapter;
import com.example.lenovo_pc.toeicvocal.databases.DatabaseManager;
import com.example.lenovo_pc.toeicvocal.R;
import com.example.lenovo_pc.toeicvocal.databases.models.CategoryModel;
import com.example.lenovo_pc.toeicvocal.databases.models.TopicModel;

import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ExpandableListView elvToeic;
    ToeicExpandableListViewAdapter toeicExpandableListViewAdapter;
    List<CategoryModel> categoryModelList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        elvToeic = findViewById(R.id.elv_toeic);
        final List<TopicModel> topicModelList = DatabaseManager.getInstance(this).getListTopic();
        categoryModelList = DatabaseManager.getInstance(this)
                .getListCategory(topicModelList);
        HashMap<String, List<TopicModel>> hashMap = DatabaseManager.getInstance(this).getHashMapTopic(
                topicModelList, categoryModelList);
        toeicExpandableListViewAdapter = new ToeicExpandableListViewAdapter(categoryModelList, hashMap);
        elvToeic.setAdapter(toeicExpandableListViewAdapter);

        elvToeic.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i1, long l) {
                TopicModel topicModel = topicModelList.get(i * 5 + i1);
                Intent intent = new Intent(MainActivity.this, StudyActivity.class);
                intent.putExtra("topic", topicModel);
                startActivity(intent);
                return false;
            }
        });
    }
}
