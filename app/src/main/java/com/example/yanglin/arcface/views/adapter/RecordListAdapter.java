package com.example.yanglin.arcface.views.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yanglin.arcface.R;
import com.example.yanglin.arcface.models.Record;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by yanglin on 18-3-20.
 */

public class RecordListAdapter extends RecyclerView.Adapter<RecordViewHolder> implements View.OnClickListener{
    private OnItemClickListener mOnItemClickListener = null;


    public static interface OnItemClickListener {
        void onItemClick(View view , int position);
    }

    protected Context context;
    protected List<Record.DataBean.DatasBean> RecordList;
    public RecordListAdapter(Context context, List<Record.DataBean.DatasBean> RecordList){
        this.context = context;
        this.RecordList = RecordList;
    }
    /**
     *创建viewhodel, 添加底层布局页
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public RecordViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.record_list_item, null);
        RecordViewHolder vn = new RecordViewHolder(view);
        view.setOnClickListener(this);
        return vn;
    }

    public void replace( List<Record.DataBean.DatasBean> list){
        this.RecordList.clear();
        this.RecordList.addAll(list);
        notifyDataSetChanged();
    }

    public void addNew( List<Record.DataBean.DatasBean> list){
        this.RecordList.addAll(list);
        notifyDataSetChanged();
    }


    /**
     * 绑定数据到hodel
     * @param holder
     * @param position //当前item下标
     */
    @Override
    public void onBindViewHolder(RecordViewHolder holder, int position) {
        Record.DataBean.DatasBean li = RecordList.get(position); // 获取menu item
        // 数据传入

        //将position保存在itemView的Tag中，以便点击时进行获取
        holder.itemView.setTag(position);
        holder.recordPeopleCount.setText(li.getCount()+"人");
        holder.recordTime.setText(li.getCreated_at());

        if(li.getType() == 1) {
            // 摄像头
            holder.recordType.setImageResource(R.mipmap.camera_type);
        } else {
            // 手机
            holder.recordType.setImageResource(R.mipmap.phone_type);
        }
    }

    /**
     * 获取当前item数量
     * @return
     */
    @Override
    public int getItemCount() {
        return RecordList == null ? 0 : RecordList.size();
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
class RecordViewHolder extends RecyclerView.ViewHolder{

    @BindView(R.id.record_type)
    ImageView recordType;
    @BindView(R.id.record_time)
    TextView recordTime;
    @BindView(R.id.record_people_count)
    TextView recordPeopleCount;
    public RecordViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
