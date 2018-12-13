package com.example.cherish.salehouse_kotlin.frame;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple {@link Fragment} subclass.
 */
public abstract class BaseFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(layoutResId(), container, false);
        findViews(view);
        init(savedInstanceState);
        initComplete();

        return view;
    }

    protected abstract void init(@Nullable Bundle savedInstanceState);

    protected abstract void findViews(View view);

    public abstract int layoutResId();

    /**
     * 初始化完成
     */
    protected abstract void initComplete();

    public void startActivity(Class<?> cls) {
        startActivity(new Intent(getActivity(), cls));
    }

}
