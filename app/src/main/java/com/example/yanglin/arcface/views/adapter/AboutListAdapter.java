package com.example.yanglin.arcface.views.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.yanglin.arcface.R;
import com.example.yanglin.arcface.models.MyList;

import java.text.DecimalFormat;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by yanglin on 18-3-20.
 */

public class AboutListAdapter extends RecyclerView.Adapter<BaseViewHodel> implements View.OnClickListener{
    private OnItemClickListener mOnItemClickListener = null;


    public static interface OnItemClickListener {
        void onItemClick(View view , int position);
    }

    protected Context context;
    protected List<MyList> myLists;
    public AboutListAdapter(Context context, List<MyList> myLists){
        this.context = context;
        this.myLists = myLists;
    }
    /**
     *创建viewhodel, 添加底层布局页
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public BaseViewHodel onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.about_list_item, null);
        BaseViewHodel vn = new BaseViewHodel(view);
        view.setOnClickListener(this);
        return vn;
    }


    /**
     * 绑定数据到hodel
     * @param holder
     * @param position //当前item下标
     */
    @Override
    public void onBindViewHolder(BaseViewHodel holder, int position) {
        MyList li = myLists.get(position); // 获取menu item
        // 数据传入

        //将position保存在itemView的Tag中，以便点击时进行获取
        holder.itemView.setTag(position);

        holder.title.setText(li.getTitlt());
        holder.content.setText(li.getContent());
    }

    /**
     * 获取当前item数量
     * @return
     */
    @Override
    public int getItemCount() {
        return myLists == null ? 0 : myLists.size();
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
class BaseViewHodel extends RecyclerView.ViewHolder{

    @BindView(R.id.about_title)
    TextView title;
    @BindView(R.id.about_content)
    TextView content;
    public BaseViewHodel(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
