package demo.helloworld.components.home;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.xutils.common.util.LogUtil;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import demo.helloworld.R;
import demo.helloworld.http.XUtils.EnginCallBack;
import demo.helloworld.http.XUtils.ServiceEngin;

@ContentView(R.layout.activity_home)
public class HomeActivity extends AppCompatActivity {

    @ViewInject(R.id.textview)
    private TextView mTextView;
    @ViewInject(R.id.imageview)
    private ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                injectOnClick();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void injectOnClick() {
        String url = "http://yunlive.applinzi.com/upload.php";
        String path = "/storage/emulated/0/ncihealth/Image/20160912121301540.jpeg";
        Map<String, Object> map = new HashMap<>();
        File file = new File(path);
        map.put("myFile", file);
        ServiceEngin.Post(url, map, new EnginCallBack<String>(this) {

            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                LogUtil.d(result);

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                super.onError(ex, isOnCallback);
                LogUtil.d("onError");
            }
        });
    }
}
