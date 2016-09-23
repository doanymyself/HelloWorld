package demo.helloworld.http.OKHttp;

import android.app.ProgressDialog;
import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zhy.http.okhttp.callback.StringCallback;

import org.xutils.common.util.LogUtil;
import org.xutils.ex.HttpException;

import demo.helloworld.widgets.CommonPrograssDialog;
import okhttp3.Call;
import okhttp3.Request;

/**
 * ********************************************************
 * <p>
 * ********************************************************
 * Created by wangdong on 16/9/8.
 */
public class OKEnginCallBack extends StringCallback {
    private CommonPrograssDialog pd;
    private Context context;

    public OKEnginCallBack(Context context) {
        this.context = context;
    }


    @Override
    public void onResponse(String response, int id) {
        LogUtil.d(response);
        canclDialog();
    }


    @Override
    public void onError(Call call, Exception e, int id) {
        LogUtil.d(e.toString());
        canclDialog();
    }

    @Override
    public void onBefore(Request request, int id) {
        super.onBefore(request, id);
        if (pd == null && context != null) {
            pd = CommonPrograssDialog.getInstance(context);
            pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            pd.setCancelable(false);
            pd.show();
        }
    }

    @Override
    public void inProgress(float progress, long total, int id) {
        super.inProgress(progress, total, id);
    }

    @Override
    public void onAfter(int id) {
        super.onAfter(id);
    }


    /**
     * 取消请求对话框
     */
    public void canclDialog() {
        if (pd != null) {
            pd.dismiss();
            pd = null;
        }
    }
}
