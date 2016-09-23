package demo.helloworld.components.news;

import android.os.Bundle;

import demo.helloworld.R;
import demo.helloworld.framework.BaseActivity;

/**
 * ********************************************************
 * <p>
 * ********************************************************
 * Created by wangdong on 16/8/16.
 */
public class NewsActivity extends BaseActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContenierView(4);
        setBackType(2);
        NewsFragment fragment = new NewsFragment();
        mFragmentManager.beginTransaction()
                .replace(R.id.login_fl_continer, fragment)
                .addToBackStack("NewsFragment").commit();
    }
}
