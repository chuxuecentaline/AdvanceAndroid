package com.example.cherish.salehouse_kotlin.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.cherish.salehouse_kotlin.R;
import com.example.cherish.salehouse_kotlin.adapter.MultiAdapter;
import com.example.cherish.salehouse_kotlin.frame.NewsFragment;

import java.util.List;

/**
 * FIXME: cherish 请添加描述
 * Created by cherish
 */

public class MultiTextView extends LinearLayout {
    public MultiTextView(Context context) {
        this(context, null);
    }

    public MultiTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MultiTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }


  public  void addAdapter(MultiAdapter adapter){
        removeAllViews();
      int itemCount = adapter.getItemCount();
      for (int i = 0; i < itemCount; i++) {
          addView(adapter.getView(i));
      }
  }
}
