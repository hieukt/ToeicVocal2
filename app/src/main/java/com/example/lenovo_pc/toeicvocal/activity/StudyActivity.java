package com.example.lenovo_pc.toeicvocal.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.lenovo_pc.toeicvocal.R;
import com.example.lenovo_pc.toeicvocal.databases.models.TopicModel;

public class StudyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study);

        TopicModel topicModel = (TopicModel) getIntent().getSerializableExtra("topic");
        Log.d("abc", "onCreate: " + topicModel);

    }
}
