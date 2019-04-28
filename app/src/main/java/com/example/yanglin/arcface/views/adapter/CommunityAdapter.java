package com.example.yanglin.arcface.views.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yanglin.arcface.R;
import com.example.yanglin.arcface.models.Community;
import com.example.yanglin.arcface.models.Info;
import com.example.yanglin.arcface.models.Record;

import org.w3c.dom.Text;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by yanglin on 18-3-20.
 */

public class CommunityAdapter extends RecyclerView.Adapter<CommunityViewHolder> implements View.OnClickListener{
    private OnItemClickListener mOnItemClickListener = null;


    public static interface OnItemClickListener {
        void onItemClick(View view , int position);
    }

    protected Context context;
    protected List<Community.DataBean.DatasBean> communityList;
    public CommunityAdapter(Context context, List<Community.DataBean.DatasBean> communityList){
        this.context = context;
        this.communityList = communityList;
    }
    /**
     *创建viewhodel, 添加底层布局页
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public CommunityViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.community_list_item, null);
        CommunityViewHolder vn = new CommunityViewHolder(view);
        view.setOnClickListener(this);
        return vn;
    }

    /**
     * 绑定数据到hodel
     * @param holder
     * @param position //当前item下标
     */
    @Override
    public void onBindViewHolder(CommunityViewHolder holder, int position) {
        Community.DataBean.DatasBean community = communityList.get(position); // 获取menu item
        // 数据传入

        //将position保存在itemView的Tag中，以便点击时进行获取
        holder.itemView.setTag(community);
        holder.infoTitle.setText(community.getTitle());
        String category = community.getCategory();
        switch (category) {
            case "普通文章":
                holder.infoImage.setImageResource(R.mipmap.article1);
                break;
            case "小区动态":
                holder.infoImage.setImageResource(R.mipmap.article2);
                break;
            case "失误招领":
                holder.infoImage.setImageResource(R.mipmap.article3);
                break;
            default:
                holder.infoImage.setImageResource(R.mipmap.article1);
                break;
        }
        if(category.isEmpty()) {
            holder.infoType.setText("普通文章");
        } else holder.infoType.setText(category);
        holder.infoTag1.setText(community.getTag());
    }

    public void replace( List<Community.DataBean.DatasBean> list){
        this.communityList.clear();
        this.communityList.addAll(list);
        notifyDataSetChanged();
    }

    public void addNew( List<Community.DataBean.DatasBean> list){
        this.communityList.addAll(list);
        notifyDataSetChanged();
    }

    /**
     * 获取当前item数量
     * @return
     */
    @Override
    public int getItemCount() {
        return communityList == null ? 0 : communityList.size();
    }

    @Override
    public void onClick(View view) {
        if(mOnItemClickListener != null){
            Community.DataBean.DatasBean article = (Community.DataBean.DatasBean)view.getTag();
            mOnItemClickListener.onItemClick(view, article.getId());
        }
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }
}

// 自定义viewhodel
class CommunityViewHolder extends RecyclerView.ViewHolder{

    @BindView(R.id.commmunity_image)
    ImageView infoImage;
    @BindView(R.id.commmunity_title)
    TextView infoTitle;
    @BindView(R.id.article_type)
    TextView infoType;
    @BindView(R.id.article_tags1)
    TextView infoTag1;
    @BindView(R.id.article_tags2)
    TextView getInfoTag2;
    public CommunityViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
