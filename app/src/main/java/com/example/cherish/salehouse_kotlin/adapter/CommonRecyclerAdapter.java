package com.example.cherish.salehouse_kotlin.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cherish.salehouse_kotlin.R;
import com.example.cherish.salehouse_kotlin.adapter.viewHolder.MultiTypeSupport;
import com.example.cherish.salehouse_kotlin.adapter.viewHolder.ViewHolder;

import java.util.List;

/**
 * 通用的adapter
 * Created by cherish
 * 数据决定采用泛型，布局打算直接构造传递，绑定显示效果回传。
 */

public abstract class CommonRecyclerAdapter<T, H> extends RecyclerView.Adapter<ViewHolder> {
    private H mHead;
    //多布局支持
    private MultiTypeSupport mMultiTypeSupport;
    private Context mContext;
    private LayoutInflater mInflater;

    //数据怎么办？ 利用泛型
    protected List<T> mDatas;

    public CommonRecyclerAdapter(Context context, List<T> datas) {
        this.mContext = context;
        this.mInflater = LayoutInflater.from(mContext);
        this.mDatas = datas;
    }

    /**
     * 多布局支持
     *
     * @return
     */
    public CommonRecyclerAdapter(Context context, H head, List<T> data, MultiTypeSupport<H>
            multiTypeSupport) {
        this(context, data);
        this.mMultiTypeSupport = multiTypeSupport;
        mHead = head;
    }

    /**
     * 根据当前位置获取不同的viewType
     *
     * @param position
     * @return
     */

    @Override
    public int getItemViewType(int position) {
        if (mMultiTypeSupport != null) {
            return mMultiTypeSupport.getLayoutId(mHead, position);
        }
        return super.getItemViewType(position);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //多布局支持
        int mLayoutId = getItemLayout();
        if (mMultiTypeSupport != null) {
            mLayoutId = viewType;
        }
        return new ViewHolder(mInflater.inflate(mLayoutId, parent, false));
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.setOnIntemClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mItemClickListener != null) {
                    mItemClickListener.onItemClick(position);
                }
            }
        });
        if (holder.getItemViewType() == R.layout.item_head) {
            bindHeadData(holder, mHead);
        } else {
            bindData(holder, mDatas.get(position));
        }

    }


    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    /**
     * 布局
     *
     * @return
     */
    public abstract int getItemLayout();

    /**
     * 数据绑定
     *
     * @param holder
     * @param t
     */
    protected abstract void bindData(ViewHolder holder, T t);

    /**
     * 绑定头布局数据
     *
     * @param holder
     * @param head
     */
    protected abstract void bindHeadData(ViewHolder holder, H head);


    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public interface OnLongClickListener {
        void onLongClick(int position);
    }

    /***************
     * 设置条目点击和长按事件
     *********************/
    public OnItemClickListener mItemClickListener;
    public OnLongClickListener mLongClickListener;

    public void setOnItemClickListener(OnItemClickListener itemClickListener) {
        this.mItemClickListener = itemClickListener;
    }

    public void setOnLongClickListener(OnLongClickListener longClickListener) {
        this.mLongClickListener = longClickListener;
    }
}
