package com.example.yanglin.arcface.views.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yanglin.arcface.R;
import com.example.yanglin.arcface.models.Record;
import com.example.yanglin.arcface.models.Visitor;
import com.example.yanglin.arcface.views.VisitorActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by yanglin on 18-3-20.
 */

public class VisitorAdapter extends RecyclerView.Adapter<VisitorViewHolder> implements View.OnClickListener{
    private OnItemClickListener mOnItemClickListener = null;


    public static interface OnItemClickListener {
        void onItemClick(View view , int position);
    }

    protected Context context;
    protected List<Visitor> VisitorList;
    public VisitorAdapter(Context context, List<Visitor> VisitorList){
        this.context = context;
        this.VisitorList = VisitorList;
    }
    /**
     *创建viewhodel, 添加底层布局页
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public VisitorViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.visitor_list_item, null);
        VisitorViewHolder vn = new VisitorViewHolder(view);
        view.setOnClickListener(this);
        return vn;
    }


    /**
     * 绑定数据到hodel
     * @param holder
     * @param position //当前item下标
     */
    @Override
    public void onBindViewHolder(VisitorViewHolder holder, int position) {
        Visitor visitor = VisitorList.get(position); // 获取menu item
        // 数据传入

        //将position保存在itemView的Tag中，以便点击时进行获取
        holder.itemView.setTag(position);
        holder.name.setText("访客: "+visitor.getName());
        holder.phone.setText(visitor.getPhone());
        holder.reason.setText(visitor.getReason());
        holder.yearM.setText("12月24");
        holder.time.setText("20:45");
        if(visitor.getType() == 0) {
            holder.statusButton.setText("审核");
        }
        else holder.statusButton.setText("已审核");
    }

    /**
     * 获取当前item数量
     * @return
     */
    @Override
    public int getItemCount() {
        return VisitorList == null ? 0 : VisitorList.size();
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
    public int getType(int index) {
        Visitor visitor = VisitorList.get(index);
        return visitor.getType();
    }
}

// 自定义viewhodel
class VisitorViewHolder extends RecyclerView.ViewHolder{

    @BindView(R.id.apply_year_month)
    TextView yearM;
    @BindView(R.id.apply_time)
    TextView time;
    @BindView(R.id.apply_name)
    TextView name;
    @BindView(R.id.apply_phone)
    TextView phone;
    @BindView(R.id.apply_reason)
    TextView reason;
    @BindView(R.id.apply_status_button)
    Button statusButton;
    public VisitorViewHolder(final View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);

        statusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String type = statusButton.getText().toString();
                if(type!= null && type.equals("审核")) {
                    int i = getAdapterPosition();
                    Toast.makeText(itemView.getContext(), String.valueOf(i), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
