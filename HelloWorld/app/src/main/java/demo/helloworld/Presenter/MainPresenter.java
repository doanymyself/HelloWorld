package demo.helloworld.Presenter;

import android.os.Handler;
import android.os.Looper;

import demo.helloworld.model.TaskDataSourceImpl;
import demo.helloworld.model.TaskDataSourceTestImpl;
import demo.helloworld.model.TaskManager;

/**
 * ********************************************************
 * <p/>
 * ********************************************************
 * Created by wangdong on 16/8/3.
 */
public class MainPresenter {
    MainView mainView;
    TaskManager taskData;

    public MainPresenter() {
        this.taskData = new TaskManager(new TaskDataSourceImpl());
    }

    public MainPresenter test() {
        this.taskData = new TaskManager(new TaskDataSourceTestImpl());
        return this;
    }

    public MainPresenter addTaskListener(MainView viewListener) {
        this.mainView = viewListener;
        return this;
    }

    public void getString() {
        final Handler mainHandler = new Handler(Looper.getMainLooper());
        new Thread() {
            @Override
            public void run() {
                super.run();
                final String str = taskData.getShowContent();
                mainHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mainView.onShowString(str);
                    }
                });
            }
        }.start();
    }
}
