package com.haji.hajidesktop.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.haji.hajidesktop.MainActivity;
import com.haji.hajidesktop.R;

import java.util.List;

public class MyAdapter extends  RecyclerView.Adapter<MyAdapter.VH>{

    public Context context;
    private List<ResolveInfo> mApps;
//    private StartApp st;
    private MainActivity mainActivity;

    public MyAdapter(Context context, List<ResolveInfo> mApps,MainActivity mainActivity){ // 可以在这里设置参数来实现数据传输
        this.context = context;
        this.mApps = mApps;
        this.mainActivity=mainActivity;
    }

    public static class VH extends RecyclerView.ViewHolder{
        // 设置 控件对应的变量
        // 例如：
        // public ImageView imgv_book_pic;
        public ImageView ivIcon;
        public TextView tvName;
        public VH(View itemView){
            super(itemView);
            // 在此处可以注册 item.xml 文件中的控件
            // 例如：
            ivIcon=itemView.findViewById(R.id.ivIcon);
            tvName=itemView.findViewById(R.id.tvName);

        }
    }

    @NonNull
    @Override
    public MyAdapter.VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_app,parent,false); // 此处修改item.xml 文件
        return new VH(v);
    }


    @Override
    public void onBindViewHolder(@NonNull MyAdapter.VH holder, @SuppressLint("RecyclerView") int position) {
        ResolveInfo resolveInfo = mApps.get(position);
        Drawable drawable = resolveInfo.activityInfo.loadIcon(context.getPackageManager());
        CharSequence charSequence = resolveInfo.loadLabel(context.getPackageManager());

        String packageName = resolveInfo.activityInfo.packageName;
        String appName = resolveInfo.activityInfo.name;

        // 在此处设置每个 item 中对应的控件
        //例如:
        holder.ivIcon.setImageDrawable(drawable);
        holder.tvName.setText(charSequence);
        holder.ivIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.Start(packageName,appName);
            }
        });

//        holder.ivIcon.setOnClickListener(v -> {
//            mMyAppsAdapterSetOnClickListener.OnClickListener(position);
//        });
        //...


    }
//    public interface StartApp {
//        void Start(String packageName,String appName);
//    }

    @Override
    public int getItemCount() {
        // 实际显示的 item 的数量
        return mApps.size(); // 例如 return 3 就是显示3个 item (一般可以返回数组的大小)
    }

}