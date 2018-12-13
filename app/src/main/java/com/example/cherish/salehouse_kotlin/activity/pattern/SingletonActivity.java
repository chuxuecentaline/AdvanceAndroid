package com.example.cherish.salehouse_kotlin.activity.pattern;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.example.baselibrary.base.BaseActivity;
import com.example.baselibrary.dialog.CommonDialog;
import com.example.baselibrary.ioc.BindViewUtils;
import com.example.baselibrary.ioc.onClick;
import com.example.baselibrary.toolbar.normal.NormalNavigationBar;
import com.example.cherish.salehouse_kotlin.R;

/**
 * 单例设计模式
 */
public class SingletonActivity extends BaseActivity {

    @Override
    public int getContentViewId() {
        return R.layout.activity_signleton;
    }

    @Override
    protected void findViews() {
        BindViewUtils.inject(this);
        new NormalNavigationBar.Build(this).setTitle("单例设计模式 ").setRightMenu(R.menu
                .navigation_tab).create();
    }

    @Override
    protected void init(Bundle savedInstanceState) {

    }

    @onClick({R.id.tv_evil, R.id.tv_slacker, R.id.tv_dcl, R.id.tv_static_internal_class, R.id
            .tv_enum, R.id.tv_map,R.id.tv_static_class})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_evil:
                evil();
                break;
            case R.id.tv_slacker:
                slacker();
                break;
            case R.id.tv_dcl:
                dcl();
                break;
            case R.id.tv_static_internal_class:
                internalClass();
                break;
            case R.id.tv_enum:
                enum_model();
                break;
            case R.id.tv_map:
                map();
                break;
            case R.id.tv_static_class:
                staticClass();
                break;

        }

    }

    private void staticClass() {
        String content="public class Singleton {\n" + "    private static Singleton mInstance;\n" + "\n" + "    static {\n" + "        mInstance = new Singleton();\n" + "    }\n" + "\n" + "    private Singleton() {\n" + "\n" + "    }\n" + "\n" + "    public static Singleton getInstance(){\n" + "        return mInstance;\n" + "    }\n" + "}";
        createDialog(content);
    }

    /**
     * 容器模式
     */
    private void map() {
        String content="public class Singleton {\n" + "    private static Map<String,Object> mSingleMap = new HashMap<>();\n" + "\n" + "    static {\n" + "        mSingleMap.put(\"activity_manager\",new Singleton());\n" + "    }\n" + "\n" + "    private Singleton() {\n" + "\n" + "    }\n" + "\n" + "    public static Object getService(String serviceName){\n" + "        return mSingleMap.get(serviceName);\n" + "    }\n" + "}";
        createDialog(content);
    }

    /**
     * 枚举
     */
    private void enum_model() {
        String content="public enum SingletonEnum {\n" + "    INSTANCE;\n" + "    public void whateverMethod() {\n" + "\n" + "    }\n" + "}";
        createDialog(content);
    }

    /**
     * 静态内部类
     */
    private void internalClass() {
        String content="public class Singleton {\n" + "\n" + "    private Singleton() {}\n" + "\n" + "    private static class SingletonInstance {\n" + "        private static volatile Singleton INSTANCE = new Singleton();\n" + "    }\n" + "\n" + "    public static  Singleton getInstance() {\n" + "        return SingletonInstance.INSTANCE;\n" + "    }\n" + "}\n" + "\n" ;
        createDialog(content);
    }

    /**
     * 同步锁
     */
    private void dcl() {
        String content="public class SingletonDcl {\n" + "    \n" + "    private volatile static SingletonDcl mSingleTon;\n" + "\n" + "    private SingletonDcl() {\n" + "    }\n" + "\n" + "    public static SingletonDcl getInstance() {\n" + "        if (mSingleTon == null) {\n" + "            synchronized (SingletonDcl.class) {\n" + "                if (mSingleTon == null) {\n" + "                    mSingleTon = new SingletonDcl();\n" + "                }\n" + "\n" + "            }\n" + "\n" + "        }\n" + "        return mSingleTon;\n" + "    }\n" + "}";
        createDialog(content);
    }

    /**
     * 懒汉模式
     */
    private void slacker() {
        String content ="public class Singleton {\n" + "\n" + "    private static Singleton singleton;\n" + "\n" + "    private Singleton() {}\n" + "\n" + "    public static Singleton getInstance() {\n" + "        if (singleton == null) {\n" + "            singleton = new Singleton();\n" + "        }\n" + "        return singleton;\n" + "    }\n" + "}";
        createDialog(content);

    }

    /**
     * 恶汉模式
     */
    private void evil() {
        String content ="public class Singleton {\n" +
                "   private final static Singleton INSTANCE = new Singleton();\n"  +
                "   private Singleton(){}\n" +
                "   public static Singleton getInstance(){" +
                "   \n"  +
                "   return INSTANCE;\n" +
                "    }\n" +
                "}";
        createDialog(content);

    }

    /**
     * 创建弹弹出框
     * @param content 显示内容
     */
    private void createDialog(String content) {
        View view = View.inflate(this, R.layout.item_content, null);
        new CommonDialog.Builder(this).setView(view).setTitle(R.id.tv_content, content)
                .setGravity(Gravity.BOTTOM).setFullScreen(true)
                .setCancelable(true).addAnimal(R.anim.dialog_from_bottom_anim_in).show();
    }
}
