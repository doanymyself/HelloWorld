package demo.helloworld.framework;

import android.app.Application;

import org.xutils.x;

//┏┓　　　┏┓
//┏┛┻━━━┛┻┓
//┃　　　　　　　┃ 　
//┃　　　━　　　┃
//┃　┳┛　┗┳　┃
//┃　　　　　　　┃
//┃　　　┻　　　┃
//┃　　　　　　　┃
//┗━┓　　　┏━┛
//   ┃　　　┃   神兽保佑　　　　　　　　
//   ┃　　　┃   代码无BUG！
//   ┃　　　┗━━━┓
//   ┃　　　　　　　┣┓
//   ┃　　　　　　　┏┛
//   ┗┓┓┏━┳┓┏┛
//     ┃┫┫　┃┫┫
//     ┗┻┛　┗┻┛

/**
 * ********************************************************
 * <p/>
 * ********************************************************
 * Created by wangdong on 16/8/3.
 */
public class MyApplication extends Application {
    public static MyApplication myApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        // TODO Auto-generated method stub
        myApplication = this;
        x.Ext.init(this);//初始化xUtils
        x.Ext.setDebug(true); // 是否输出debug日志
    }

    public static MyApplication getMyApplication() {
        return myApplication;
    }

}
