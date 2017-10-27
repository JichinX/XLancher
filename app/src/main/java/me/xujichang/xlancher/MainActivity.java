package me.xujichang.xlancher;

import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.xujichang.utils.tool.LogTool;

import java.util.ArrayList;
import java.util.List;

import me.xujichang.xlancher.adapter.AppListAdapter;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rvAppList;
    private ArrayList<ResolveInfo> apps;
    private AppListAdapter appListAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        apps = new ArrayList<>();
        appListAdapter = new AppListAdapter(this, apps);
        initView();
        getApplicationsInSystem();
    }

    private void getApplicationsInSystem() {
        Intent resolveIntent = new Intent(Intent.ACTION_MAIN, null);
        resolveIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        List<ResolveInfo> systemApps = getPackageManager().queryIntentActivities(resolveIntent, 0);
        if (systemApps != null) {
            for (ResolveInfo resolveInfo : systemApps) {
                LogTool.d(resolveInfo.resolvePackageName);
            }
            apps.clear();
            apps.addAll(systemApps);
        }
        appListAdapter.notifyDataSetChanged();
    }

    private void initView() {
        rvAppList = findViewById(R.id.rv_app_list);
        rvAppList.setLayoutManager(new GridLayoutManager(this, 4));
        rvAppList.setAdapter(appListAdapter);
    }
}
