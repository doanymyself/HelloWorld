package demo.helloworld.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import demo.helloworld.Presenter.MainPresenter;
import demo.helloworld.Presenter.MainView;
import demo.helloworld.R;

@ContentView(R.layout.activity_main)
public class MainActivity extends AppCompatActivity implements MainView {

    MainPresenter presenter;
    @ViewInject(R.id.text)
    TextView mShowTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
    }

    @Event(value = R.id.btn)
    private void loadDatas(View view) {
        presenter = new MainPresenter().test();
        presenter.addTaskListener(this);
        presenter.getString();
    }

    @Override
    public void onShowString(String str) {
        mShowTxt.setText(str);
    }

}
