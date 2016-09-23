package demo.helloworld.framework;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.xutils.common.util.LogUtil;

import demo.helloworld.R;
import demo.helloworld.components.event.EventsActivity;
import demo.helloworld.components.news.NewsActivity;
import demo.helloworld.data.Constants;
import demo.helloworld.widgets.CommonDialog;

/**
 * *********************************************************
 * 基类Activity,可自动继承各变量和界面框架,title栏底栏等
 * *********************************************************
 * <p>
 * Created by wangdong on 2016/5/16.
 */
public class BaseActivity extends AppCompatActivity implements View.OnClickListener {
    private static final int ACTIVITY_RESUME = 0;
    private static final int ACTIVITY_STOP = 1;
    private static final int ACTIVITY_PAUSE = 2;
    private static final int ACTIVITY_DESTROY = 3;

    public int activityState;

    // 是否允许全屏
    private boolean mAllowFullScreen = true;

    protected FragmentManager mFragmentManager;
    protected MyApplication myApplication;
    protected Context context;

    // tab标签栏
    public LinearLayout home_tabs;
    // tab标签按钮
    public Button home_tab1_cb, home_tab2_cb, home_tab3_cb, home_tab4_cb,
            home_tab5_cb;
    // title栏
    public RelativeLayout home_titlebar;
    // title栏左按钮
    public ImageButton btn_left;
    // title标题文字
    public TextView home_titile, leftView;
    // title栏右按钮
    public ImageButton btn_right, btn_right2;
    public int backtype;// 返回方式

    private CommonDialog dialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myApplication = MyApplication.myApplication;
        mFragmentManager = this.getSupportFragmentManager();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        if (mAllowFullScreen) {
            requestWindowFeature(Window.FEATURE_NO_TITLE); // 取消标题
        }
        AppManager.getAppManager().addActivity(this);
    }

    /**
     * @param showtabs 1.显示上不显示下;2.上下都不显示;3.只显示上但没有按钮;4.上下都显示但没有返回按钮;默认都显示
     */
    public void setContenierView(int showtabs) {
        View view = LayoutInflater.from(this).inflate(
                R.layout.activity_base_layout, null);
        setContentView(view);
        context = this;
        home_tabs = (LinearLayout) view.findViewById(R.id.home_tabs);
        home_titlebar = (RelativeLayout) view.findViewById(R.id.home_titlebar);
        btn_left = (ImageButton) view.findViewById(R.id.home_btn_left);
        leftView = (TextView) view.findViewById(R.id.home_btn_left_view);
        home_titile = (TextView) view.findViewById(R.id.home_titile);
        btn_right2 = (ImageButton) view.findViewById(R.id.home_btn_right2);
        btn_right = (ImageButton) view.findViewById(R.id.home_btn_right);
        home_tab1_cb = (Button) view.findViewById(R.id.home_tab1_cb);
        home_tab2_cb = (Button) view.findViewById(R.id.home_tab2_cb);
        home_tab3_cb = (Button) view.findViewById(R.id.home_tab3_cb);
        home_tab4_cb = (Button) view.findViewById(R.id.home_tab4_cb);
        home_tab5_cb = (Button) view.findViewById(R.id.home_tab5_cb);
        if (showtabs == 1) {
            home_titlebar.setVisibility(View.VISIBLE);
            home_tabs.setVisibility(View.GONE);
        } else if (showtabs == 2) {
            home_tabs.setVisibility(View.GONE);
            home_titlebar.setVisibility(View.GONE);
        } else if (showtabs == 3) {
            home_tabs.setVisibility(View.GONE);
            home_titlebar.setVisibility(View.VISIBLE);
            btn_left.setVisibility(View.GONE);
        } else if (showtabs == 4) {
            home_tabs.setVisibility(View.VISIBLE);
            home_titlebar.setVisibility(View.VISIBLE);
            btn_left.setVisibility(View.GONE);
        } else {
            home_tabs.setVisibility(View.VISIBLE);
            home_titlebar.setVisibility(View.VISIBLE);
        }
        home_tab1_cb.setText(Constants.moduleStr[0]);
        home_tab2_cb.setText(Constants.moduleStr[1]);
        home_tab3_cb.setText(Constants.moduleStr[2]);
        home_tab4_cb.setText(Constants.moduleStr[3]);
        home_tab5_cb.setText(Constants.moduleStr[4]);
        home_tab1_cb.setOnClickListener(this);
        home_tab2_cb.setOnClickListener(this);
        home_tab3_cb.setOnClickListener(this);
        home_tab4_cb.setOnClickListener(this);
        home_tab5_cb.setOnClickListener(this);
        btn_left.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.home_tab1_cb:
                if (v.isSelected()) {
                    break;
                }
                Intent intent1 = new Intent(context, EventsActivity.class);
                intent1.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                context.startActivity(intent1);
                break;
            case R.id.home_tab2_cb:
                if (v.isSelected()) {
                    break;
                }
                Intent intent2 = new Intent(context, NewsActivity.class);
                intent2.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                context.startActivity(intent2);
                break;
            case R.id.home_tab3_cb:
                if (v.isSelected()) {
                    break;
                }
                break;
            case R.id.home_tab4_cb:
                if (v.isSelected()) {
                    break;
                }
                break;
            case R.id.home_tab5_cb:
                if (v.isSelected()) {
                    break;
                }

                break;
            case R.id.home_btn_left:
                int num = getSupportFragmentManager().getBackStackEntryCount();
                if (num <= 1) {
                    this.finish();
                } else {
                    getSupportFragmentManager().popBackStack();
                }
                break;
        }
    }

    /**
     * @param type 实体键返回键的实现方式; 0,默认fragment一层一层返回 ;1,关闭当前activity;2,提示对话框退出程序
     */
    public void setBackType(int type) {
        backtype = type;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (backtype == 0) {
                if (getSupportFragmentManager().getBackStackEntryCount() <= 1) {
                    this.finish();
                } else {
                    getSupportFragmentManager().popBackStackImmediate();
                }
            } else if (backtype == 1) {
                this.finish();
            } else if (backtype == 2) {
                dialog = new CommonDialog(context, 2, "确认", "取消",
                        new View.OnClickListener() {

                            @Override
                            public void onClick(View v) {
                                // TODO Auto-generated method stub
                                Intent home = new Intent(Intent.ACTION_MAIN);
                                home.addCategory(Intent.CATEGORY_HOME);
                                startActivity(home);
                                dialog.dismiss();
                                AppManager.getAppManager().AppExit(context);
                            }
                        }, null, null, "确认退出程序?");
                dialog.show();
            }
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

    /*
     * 继承于Activity的设置标题
     */
    @Override
    public void setTitle(CharSequence title) {
        // TODO Auto-generated method stub
        home_titile.setText(title);
        super.setTitle(title);
    }

    @Override
    protected void onStart() {
        super.onStart();
        LogUtil.d("---------onStart ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        activityState = ACTIVITY_RESUME;
        LogUtil.d("---------onResume ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        activityState = ACTIVITY_STOP;
        LogUtil.d("---------onStop ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        activityState = ACTIVITY_PAUSE;
        LogUtil.d("---------onPause ");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        LogUtil.d("---------onRestart ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        activityState = ACTIVITY_DESTROY;
        LogUtil.d("---------onDestroy ");
        AppManager.getAppManager().finishActivity(this);
    }
}
