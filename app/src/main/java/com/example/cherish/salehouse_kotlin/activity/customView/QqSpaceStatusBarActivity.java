package com.example.cherish.salehouse_kotlin.activity.customView;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.example.baselibrary.base.BaseActivity;
import com.example.cherish.salehouse_kotlin.R;
import com.example.cherish.salehouse_kotlin.utils.StatusBarUtils;

public class QqSpaceStatusBarActivity extends BaseActivity {

    private LinearLayout mToolBar;
    private ScrollView mScrollView;
    private int mHeight;
    private ImageView mImageView;

    @Override
    public int getContentViewId() {
        return R.layout.activity_qq_space_status_bar;
    }

    @Override
    protected void findViews() {
        new StatusBarUtils().setStatusTranslate(this);
        mScrollView = findViewById(R.id.scrollView);
        mToolBar = findViewById(R.id.tool_bar);
        mImageView = findViewById(R.id.imageView);
        //  new StatusBarUtils().setStatusBarColors(this,getResources().getColor(R.color
        // .holo_blue));
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        mToolBar.setAlpha(0);
        mImageView.post(new Runnable() {
            @Override
            public void run() {
                mHeight = mImageView.getMeasuredHeight();
            }
        });
        mScrollView.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver
                .OnScrollChangedListener() {

            @Override
            public void onScrollChanged() {
                float scrollY = mScrollView.getScrollY();
                Log.e("onScrollChange", "scrollY:" + scrollY);
                float alpha=(float) scrollY/(mHeight-mToolBar.getMeasuredHeight());
                if(alpha<0){
                    alpha=0;
                }else if(alpha>1){
                    alpha=1;
                }
                mToolBar.setAlpha(alpha);


            }
        });
    }


}
