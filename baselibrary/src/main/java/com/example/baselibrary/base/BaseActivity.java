package com.example.baselibrary.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.baselibrary.R;
import com.example.baselibrary.toolbar.normal.NormalNavigationBar;


/**
 * 基类
 * Created by cherish
 */

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentViewId());
        findViews();
        init(savedInstanceState);

    }

    public abstract int getContentViewId();

    protected abstract void findViews();

    protected abstract void init(Bundle savedInstanceState);

    public void toast(String tips) {
        Toast.makeText(this, tips, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (android.R.id.home == item.getItemId()) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
