package com.example.cherish.salehouse_kotlin.activity.wheel;

import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import com.example.baselibrary.toolbar.normal.NormalNavigationBar;
import com.example.cherish.salehouse_kotlin.R;
import com.example.baselibrary.base.BaseActivity;
import com.example.baselibrary.dialog.CommonDialog;

public class AlertDialogActivity extends BaseActivity {



    @Override
    public int getContentViewId() {
        return R.layout.activity_alert_dialog;
    }

    @Override
    protected void findViews() {
        new NormalNavigationBar.Build(this).setTitle("Build 模式 Dialog").setRightMenu(R.menu
                .navigation_tab).create();
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        new CommonDialog.Builder(this).setView(R.layout.dialog_share).setTitle(R.id.btn_send,
                "发送").setOnclickListener(R.id.btn_send, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppCompatEditText editView = v.findViewById(R.id.et_content);
                Toast.makeText(AlertDialogActivity.this, "输入" + editView.getText().toString()
                        .trim(), Toast.LENGTH_SHORT).show();

            }
        }).setCancelable(true).setFullScreen(true).setGravity(Gravity.BOTTOM).addAnimal(R.style
                .dialog_from_bottom_anim).show();

    }
}
