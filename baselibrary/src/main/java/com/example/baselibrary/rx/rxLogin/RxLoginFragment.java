package com.example.baselibrary.rx.rxLogin;


import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.example.baselibrary.R;

import io.reactivex.subjects.PublishSubject;

/**
 * A fragment with a Google +1 button.
 */
public class RxLoginFragment extends Fragment implements View.OnClickListener {


    private ImageView iv_account, iv_qq, iv_weChat, iv_sina;
    private PublishSubject<String> mSubject = PublishSubject.create();
    private BottomSheetDialog mDialog;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onResume() {
        super.onResume();

    }

    private void initDialog() {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_rx_login, null);
        mDialog = new BottomSheetDialog(getActivity());
        mDialog.setContentView(view);
        mDialog.show();
        initView(view);
    }


    private void initView(View view) {
        iv_account = view.findViewById(R.id.iv_account);
        iv_qq = view.findViewById(R.id.iv_qq);
        iv_weChat = view.findViewById(R.id.iv_weChat);
        iv_sina = view.findViewById(R.id.iv_sina);
        iv_account.setOnClickListener(this);
        iv_qq.setOnClickListener(this);
        iv_weChat.setOnClickListener(this);
        iv_sina.setOnClickListener(this);
    }


    public PublishSubject<String> getSubject() {
        initDialog();
        return mSubject;
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.iv_account) {
            mSubject.onNext("账号密码");
        } else if (i == R.id.iv_qq) {
            mSubject.onNext("QQ");
        } else if (i == R.id.iv_weChat) {
            mSubject.onNext("微信");
        } else if (i == R.id.iv_sina) {
            mSubject.onNext("新浪微博");
        }
        if (mDialog != null) {
            mDialog.dismiss();
        }
    }
}
