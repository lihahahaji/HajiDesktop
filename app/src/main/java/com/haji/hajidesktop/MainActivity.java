package com.haji.hajidesktop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;

import com.haji.hajidesktop.adapter.MyAdapter;

import java.util.List;

public class MainActivity extends AppCompatActivity{
    private List<ResolveInfo> mMApps;
    private RecyclerView recycleView;

    private MyAdapter mMyAppsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getApps();
        setData();

    }

    private void setData()
    {
        // 初始化 RecyclerView
        recycleView = findViewById(R.id.recycleView);
        if (mMyAppsAdapter == null) {
            mMyAppsAdapter = new MyAdapter(this, mMApps,this);
        }

        recycleView.removeAllViews();
        recycleView.setLayoutManager(new GridLayoutManager(this, 3));
        recycleView.setAdapter(mMyAppsAdapter);
    }

    private void getApps() {
        PackageManager packageManager = getPackageManager();
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        mMApps = packageManager.queryIntentActivities(intent, 0);
    }


    public void Start(String packageName,String appName)
    {
        ComponentName componentName = new ComponentName(packageName, appName);
        Intent intent = new Intent();
        intent.setComponent(componentName);
        startActivity(intent);
    }




}