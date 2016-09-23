package demo.helloworld.components.event;

import android.os.Bundle;

import butterknife.ButterKnife;
import demo.helloworld.R;
import demo.helloworld.framework.BaseActivity;

/**
 * ********************************************************
 * <p/>
 * ********************************************************
 * Created by wangdong on 16/8/10.
 */
public class EventsActivity extends BaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContenierView(4);
        setBackType(2);
        EventListFragment fragment = new EventListFragment();
        mFragmentManager.beginTransaction()
                .replace(R.id.login_fl_continer, fragment)
                .addToBackStack("MainFragment").commit();
    }

}
