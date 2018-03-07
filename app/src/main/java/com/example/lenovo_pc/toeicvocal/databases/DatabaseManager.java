package com.example.lenovo_pc.toeicvocal.databases;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.lenovo_pc.toeicvocal.databases.models.CategoryModel;
import com.example.lenovo_pc.toeicvocal.databases.models.TopicModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Lenovo-PC on 3/1/2018.
 */

public class DatabaseManager {
    private static final String TABLE_TOPIC = "tbl_topic";
    private static final String TABLE_WORD = "tbl_word";
    private SQLiteDatabase sqLiteDatabase;
    private AssetsHelper assetsHelper;
    private static DatabaseManager databaseManager;

    public static DatabaseManager getInstance(Context context) {
        if (databaseManager == null) {
            databaseManager = new DatabaseManager(context);
        }
        return databaseManager;
    }

    public DatabaseManager(Context context) {
        assetsHelper = new AssetsHelper(context);
    }

    public List<TopicModel> getListTopic() {
        sqLiteDatabase = assetsHelper.getReadableDatabase();
        List<TopicModel> topicModels = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from " + TABLE_TOPIC, null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String imageUrl = cursor.getString(3);
            String category = cursor.getString(4);
            String color = cursor.getString(5);
            String lastTime = cursor.getString(6);

            TopicModel topicModel = new TopicModel(id, name, imageUrl, category, color, lastTime);
            topicModels.add(topicModel);
            cursor.moveToNext();
        }
        Log.d("abc", "getListTopic: " + topicModels);
        return topicModels;
    }

    public List<CategoryModel> getListCategory(List<TopicModel> topicModelList) {
        List<CategoryModel> categoryModelList = new ArrayList<>();
        for (int i = 0; i < topicModelList.size(); i = i + 5) {
            CategoryModel categoryModel = new CategoryModel(
                    topicModelList.get(i).category,
                    topicModelList.get(i).color);
            categoryModelList.add(categoryModel);
        }

        return categoryModelList;
    }

    public HashMap<String, List<TopicModel>> getHashMapTopic(List<TopicModel> topicModelList, List<CategoryModel> categoryModelList) {
        HashMap<String, List<TopicModel>> hashMap = new HashMap<>();
        for (int i = 0; i < categoryModelList.size(); i++) {
            int positionTopic = i * 5;

            hashMap.put(categoryModelList.get(i).name,
                    topicModelList.subList(positionTopic, positionTopic + 5));
        }
        return hashMap;
    }
}
