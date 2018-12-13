package com.example.baselibrary.dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.util.SparseArray;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.example.baselibrary.R;


/**
 * Created by cherish
 */

class AlertCommonController {
    private final CommonDialog mDialog;
    private final Window mWindow;
    CommonDialogHelper mHelper;

    public AlertCommonController(CommonDialog dialog, Window window) {
        mDialog = dialog;
        mWindow = window;
        mHelper = new CommonDialogHelper();
    }

    public void setViewLayoutResId(Context context, int layoutResId) {
        mHelper.setViewLayoutResId(context, layoutResId);
    }

    private void setContentView(View view) {
        mHelper.setView(view);
    }

    private void setText(int viewId, CharSequence charSequence) {
        mHelper.setText(viewId, charSequence);
    }

    private void setOnClickListener(int viewId, View.OnClickListener listener) {
        mHelper.setOnClickListener(viewId, listener);
    }

    private View getView() {
        return mHelper.getView();
    }

    /**
     * 获取Dialog
     *
     * @return
     */
    public CommonDialog getDialog() {
        return mDialog;
    }

    /**
     * 获取Dialog的Window
     *
     * @return
     */
    public Window getWindow() {
        return mWindow;
    }

    public static class AlertParams {

        public Context mContext;
        //是否取消点击
        public boolean mCancelable;
        //布局
        public View mView;
        //布局id
        public int mViewLayoutResId;
        //事件监听
        public DialogInterface.OnCancelListener mOnCancelListener;
        public DialogInterface.OnDismissListener mOnDismissListener;
        public DialogInterface.OnKeyListener mOnKeyListener;
        // 存放字体的修改
        public SparseArray<CharSequence> textArray;
        // 存放点击事件
        public SparseArray<View.OnClickListener> clickArray;
        //设置全屏
        public boolean mFullScreen;
        //设置位置
        public int mGravity = Gravity.CENTER;
        //添加默认动画
        public int mAnimations = R.style.dialog_scale_anim;
        public int mWidth = ViewGroup.LayoutParams.WRAP_CONTENT;
        public int mHeight = ViewGroup.LayoutParams.WRAP_CONTENT;


        public AlertParams(Context context) {
            mContext = context;
            textArray = new SparseArray<>();
            clickArray = new SparseArray<>();

        }


        public void apply(AlertCommonController alert) {
            if (alert != null) {

                if (mView != null) {
                    alert.setContentView(mView);
                } else if (mViewLayoutResId != 0) {
                    alert.setViewLayoutResId(mContext, mViewLayoutResId);
                }
                if (alert.mHelper == null) {
                    throw new IllegalArgumentException("请设置setView()布局");
                }
                //给Dialog 设置布局
                alert.getDialog().setContentView(alert.getView());
                Window window = alert.getWindow();
                WindowManager.LayoutParams params = window.getAttributes();
                window.setGravity(mGravity);
                window.setWindowAnimations(mAnimations);
                if (mFullScreen) {
                    mWidth=ViewGroup.LayoutParams.MATCH_PARENT;
                    mHeight=ViewGroup.LayoutParams.WRAP_CONTENT;

                }
                params.width=mWidth;
                params.height = mHeight;
                window.setAttributes(params);
                for (int i = 0; i < textArray.size(); i++) {
                    alert.setText(textArray.keyAt(i), textArray.valueAt(i));
                }
                for (int i = 0; i < clickArray.size(); i++) {
                    alert.setOnClickListener(clickArray.keyAt(i), clickArray.valueAt(i));
                }

            }

        }

        public void setOnCancelListener(DialogInterface.OnCancelListener onCancelListener) {
            this.mOnCancelListener = onCancelListener;
        }

        public void setOnDismissListener(DialogInterface.OnDismissListener onDismissListener) {
            this.mOnDismissListener = onDismissListener;

        }

        public void setOnKeyListener(DialogInterface.OnKeyListener onKeyListener) {
            this.mOnKeyListener = onKeyListener;

        }

    }


}
