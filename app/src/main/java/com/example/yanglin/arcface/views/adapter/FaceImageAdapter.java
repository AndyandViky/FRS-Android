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
import com.example.yanglin.arcface.models.FaceImage;
import com.example.yanglin.arcface.models.Info;
import com.example.yanglin.arcface.models.Record;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by yanglin on 18-3-20.
 */

public class FaceImageAdapter extends RecyclerView.Adapter<FaceImageAdapter.FaceImageViewHolder>{
    private OnItemLongClickListener mOnItemLongClickListener= null;

    protected Context context;
    protected List<FaceImage> faceImageList;
    public FaceImageAdapter(Context context, List<FaceImage> faceImageList){
        this.context = context;
        this.faceImageList = faceImageList;
    }
    /**
     *创建viewhodel, 添加底层布局页
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public FaceImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.face_image_item, null);
        FaceImageViewHolder vn = new FaceImageViewHolder(view, mOnItemLongClickListener);
        return vn;
    }

    /**
     * 绑定数据到hodel
     * @param holder
     * @param position //当前item下标
     */
    @Override
    public void onBindViewHolder(FaceImageViewHolder holder, int position) {
        FaceImage faceImage = faceImageList.get(position); // 获取menu item
        // 数据传入

        //将position保存在itemView的Tag中，以便点击时进行获取
        holder.itemView.setTag(position);
        holder.faceImage.setImageResource(faceImage.getImage());
    }

    /**
     * 获取当前item数量
     * @return
     */
    @Override
    public int getItemCount() {
        return faceImageList == null ? 0 : faceImageList.size();
    }

    public static interface OnItemLongClickListener {
        void onItemLongClick(View view , int position);
    }
    public void setOnItemLongClickListener(OnItemLongClickListener listener) {
        this.mOnItemLongClickListener = listener;
    }

    // 自定义viewhodel
    public static class FaceImageViewHolder extends RecyclerView.ViewHolder  implements View.OnLongClickListener{
        private OnItemLongClickListener mOnItemLong = null;
        @BindView(R.id.face_image)
        ImageView faceImage;
        public FaceImageViewHolder(View itemView, OnItemLongClickListener longClickListener) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.mOnItemLong = longClickListener;
            itemView.setOnLongClickListener(this);
        }

        @Override
        public boolean onLongClick(View view) {
            if(mOnItemLong != null){
                mOnItemLong.onItemLongClick(view, getAdapterPosition());
            }
            return true;
        }
    }
}

