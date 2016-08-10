package demo.helloworld.framework;

import android.app.Application;

import org.xutils.x;

/**
 * ********************************************************
 * <p/>
 * ********************************************************
 * Created by wangdong on 16/8/3.
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        x.Ext.setDebug(true);
    }

}
