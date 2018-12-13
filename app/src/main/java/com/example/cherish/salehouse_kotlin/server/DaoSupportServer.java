package com.example.cherish.salehouse_kotlin.server;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;

import com.example.baselibrary.IntentExtra;
import com.example.baselibrary.db.DaoSupportFactory;
import com.example.baselibrary.db.IDaoSupport;
import com.example.cherish.salehouse_kotlin.activity.wheel.DaoActicity;
import com.example.cherish.salehouse_kotlin.bean.Person;

import java.util.ArrayList;
import java.util.List;

/**
 * 开启一个服务
 * Created by cherish
 */

public class DaoSupportServer extends IntentService {
    private LocalBroadcastManager mBroadcastManager = null;
    private long startTime;
    private IDaoSupport<Person> mDao;
    private DaoSupportFactory mFactory;

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     */
    public DaoSupportServer() {
        super("DaoSupportServer");
        startTime = System.currentTimeMillis();
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        mFactory = DaoSupportFactory.getInstance(this);
        mBroadcastManager = LocalBroadcastManager.getInstance(this);
        mDao = mFactory.getDao(Person.class);
        final ArrayList<Person> list = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            Person person = new Person("宣布" + i, 12, System.currentTimeMillis(), true);
            // 像启动 Service 那样启动 IntentService
            list.add(person);

        }

        mDao.insert(list);
        //  mDao.remove();
        //  mDao.update(new Person("信息港", 12, System.currentTimeMillis(), false),"name=?",new
        //  String[]{"宣布0"});
        //  mDao.remove("name=?",new String[]{"信息港"});
        //  Person people = mDao.queryById("name=?",new String[]{"信息港"});
        //  List<Person> personList = mDao.query();
        //  System.out.println("people--->"+personList.toString());
        sendServiceStatus("服务结束", System.currentTimeMillis() - startTime);

    }


    /**
     * 发送服务状态信息
     *
     * @param tip
     * @param time
     */
    private void sendServiceStatus(String tip, long time) {
        Intent intent = new Intent(DaoActicity.ACTION_BROADCAST);
        intent.putExtra("status", tip);
        intent.putExtra("time", time);
        mBroadcastManager.sendBroadcast(intent);
    }
}
