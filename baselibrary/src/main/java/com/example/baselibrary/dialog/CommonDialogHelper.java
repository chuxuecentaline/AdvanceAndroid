package com.example.baselibrary.dialog;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import java.lang.ref.WeakReference;

/**
 * 帮助类
 * Created by cherish
 */

class CommonDialogHelper {

    private View mView;
    private SparseArray<WeakReference<View>> mViewIds;

    public CommonDialogHelper() {
        mViewIds = new SparseArray<>();
    }

    public void setView(View view) {

        mView = view;
    }

    public View getView() {
        return mView;
    }

    public void setViewLayoutResId(Context context, int layoutResId) {
        mView = LayoutInflater.from(context).inflate(layoutResId, null);
    }

    public void setText(int viewId, CharSequence charSequence) {
        TextView textView = getViewById(viewId);
        if (textView != null) {
            textView.setText(charSequence);
        }

    }

    public void setOnClickListener(int viewId, final View.OnClickListener listener) {
        View view = getViewById(viewId);
        if (view != null) {
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onClick(mView);
                }
            });
        }
    }

    private <T extends View> T getViewById(int viewId) {
        WeakReference<View> weakReference = mViewIds.get(viewId);
        View view = null;
        if (weakReference != null) {
            view = weakReference.get();
        }
        if (view == null) {
            view = mView.findViewById(viewId);
            mViewIds.put(viewId, new WeakReference<>(view));
        }
        return (T) view;

    }


}
