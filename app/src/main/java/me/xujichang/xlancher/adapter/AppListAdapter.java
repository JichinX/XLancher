package me.xujichang.xlancher.adapter;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.xujichang.utils.simple.SimpleViewHolder;

import java.util.ArrayList;

import me.xujichang.xlancher.R;

/**
 * Created by xjc on 2017/10/10.
 */

public class AppListAdapter extends RecyclerView.Adapter<AppListAdapter.APPViewHolder> {
    private ArrayList<ResolveInfo> resolveInfos;
    private Context context;

    public AppListAdapter(Context context, ArrayList<ResolveInfo> apps) {
        resolveInfos = apps;
        this.context = context;
    }

    @Override
    public APPViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(), R.layout.item_app_label, null);
        return new APPViewHolder(view);
    }

    @Override
    public void onBindViewHolder(APPViewHolder holder, int position) {
        holder.bindData(resolveInfos.get(position));
    }

    @Override
    public int getItemCount() {
        return resolveInfos.size();
    }


    class APPViewHolder extends SimpleViewHolder<ResolveInfo> {
        private ImageView ivApplicationLabel;
        private TextView tvApplicationName;

        public APPViewHolder(View itemView) {
            super(itemView);
            initView(itemView);
        }

        private void initView(View itemView) {
            ivApplicationLabel = itemView.findViewById(R.id.iv_application_label);
            tvApplicationName = itemView.findViewById(R.id.tv_application_name);
        }

        @Override
        protected void bindData(ResolveInfo data) {
            PackageManager manager = context.getPackageManager();
            tvApplicationName.setText(data.loadLabel(manager));
            ivApplicationLabel.setImageDrawable(data.loadIcon(manager));
        }
    }
}
