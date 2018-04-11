package com.example.yanglin.arcface.views.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yanglin.arcface.R;
import com.example.yanglin.arcface.models.Info;
import com.example.yanglin.arcface.models.Record;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by yanglin on 18-3-20.
 */

public class InfoListAdapter extends RecyclerView.Adapter<InfoViewHolder> implements View.OnClickListener{
    private OnItemClickListener mOnItemClickListener = null;


    public static interface OnItemClickListener {
        void onItemClick(View view , int position);
    }

    protected Context context;
    protected List<Info.DataBean.DatasBean> InfoList;
    public InfoListAdapter(Context context, List<Info.DataBean.DatasBean> InfoList){
        this.context = context;
        this.InfoList = InfoList;
    }
    /**
     *创建viewhodel, 添加底层布局页
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public InfoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.info_list_item, null);
        InfoViewHolder vn = new InfoViewHolder(view);
        view.setOnClickListener(this);
        return vn;
    }

    public void replace( List<Info.DataBean.DatasBean> list){
        this.InfoList.clear();
        this.InfoList.addAll(list);
        notifyDataSetChanged();
    }


    /**
     * 绑定数据到hodel
     * @param holder
     * @param position //当前item下标
     */
    @Override
    public void onBindViewHolder(InfoViewHolder holder, int position) {
        Info.DataBean.DatasBean info = InfoList.get(position); // 获取menu item

        //将position保存在itemView的Tag中，以便点击时进行获取
        holder.itemView.setTag(position);
        holder.infoImage.setImageResource(R.mipmap.clock);
        holder.infoTitle.setText(info.getTitle());
        holder.infoContent.setText(info.getContent());
        holder.infoTime.setText(info.getCreated_at().substring(0, 10));
    }

    /**
     * 获取当前item数量
     * @return
     */
    @Override
    public int getItemCount() {
        return InfoList == null ? 0 : InfoList.size();
    }

    @Override
    public void onClick(View view) {
        if(mOnItemClickListener != null){
            mOnItemClickListener.onItemClick(view, (int)view.getTag());
        }
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }
}

// 自定义viewhodel
class InfoViewHolder extends RecyclerView.ViewHolder{

    @BindView(R.id.info_image)
    ImageView infoImage;
    @BindView(R.id.info_title)
    TextView infoTitle;
    @BindView(R.id.info_content)
    TextView infoContent;
    @BindView(R.id.info_time)
    TextView infoTime;
    public InfoViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
