package com.example.cherish.salehouse_kotlin.activity.chart;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.util.ArrayMap;
import android.util.Log;

import com.example.cherish.salehouse_kotlin.R;
import com.example.cherish.salehouse_kotlin.bean.CharBean;
import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.MPPointF;

import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * 行情Marker
 * Created by cherish
 */

public class XYMarkerView extends MarkerView {
    private final AppCompatTextView tv_sale_average,tv_average,tv_number;
    private Map<Integer,Float> mBarData=new ArrayMap<>();
    private List<CharBean> mLineData;

    public XYMarkerView(Context context) {
        super(context, R.layout.custom_marker_view);
        tv_sale_average = findViewById(R.id.tv_sale_average);
        tv_average = findViewById(R.id.tv_average);
        tv_number = findViewById(R.id.tv_number);
        tv_sale_average.setVisibility(GONE);

    }

    /**
     * 根据均价获取
     *  @param lineData
     * @param barData*/
    public void bindData(List<CharBean> lineData, List<CharBean> barData) {
        mLineData = lineData;
        mBarData.clear();
        if(barData!=null){
            for (CharBean barDatum : barData) {
                mBarData.put(barDatum.x,barDatum.y);
            }
        }else{
            tv_number.setVisibility(GONE);
        }


    }

    @Override
    public void refreshContent(Entry e, Highlight highlight) {
        super.refreshContent(e, highlight);
        Log.i("marker", "Value: " + e.getY());
        int price =  Math.round((e.getY() * 10000));
        tv_average.setText(String.format(Locale.CHINA,
                "天津均价:%d元/平",price));

        for (int i = 0; i < mLineData.size(); i++) {
            CharBean charBean = mLineData.get(i);
            if(charBean.y==e.getY()){
                if(mBarData.containsKey(charBean.x)){
                    float aFloat = mBarData.get(charBean.x);
                    Log.i("marker", "Value: " + aFloat);
                    int num= (int) aFloat;
                    tv_number.setText(String.format(Locale.CHINA,
                            "成交量:%d套",num));
                    tv_number.setVisibility(VISIBLE);
                }
                break;
            }
        }



    }

    @Override
    public MPPointF getOffset() {
        return new MPPointF(-(getWidth() / 2), -getHeight());
    }


}
