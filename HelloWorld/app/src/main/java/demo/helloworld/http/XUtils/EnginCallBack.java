package demo.helloworld.http.XUtils;

import android.app.ProgressDialog;
import android.content.Context;

import org.xutils.common.Callback;
import org.xutils.common.util.LogUtil;

import demo.helloworld.widgets.CommonPrograssDialog;

public class EnginCallBack<ResultType> implements Callback.ProgressCallback<ResultType> {
    private CommonPrograssDialog pd;
    private Context context;

    /**
     * 构造函数,为公共进度条重写此回调
     */
    public EnginCallBack(Context context) {
        this.context = context;
    }

    @Override
    public void onSuccess(ResultType result) {
        //TODO 根据公司业务需求，处理相应业务逻辑
        canclDialog();
        LogUtil.e(result.toString());
    }

    @Override
    public void onError(Throwable ex, boolean isOnCallback) {
        //TODO 根据公司业务需求，处理相应业务逻辑
        canclDialog();
    }

    @Override
    public void onCancelled(CancelledException cex) {
        //TODO 根据公司业务需求，处理相应业务逻辑
        canclDialog();
    }

    @Override
    public void onFinished() {
        //TODO 根据公司业务需求，处理相应业务逻辑
        canclDialog();
    }

    @Override
    public void onWaiting() {
        //TODO 根据公司业务需求，处理相应业务逻辑

    }

    @Override
    public void onStarted() {
        if (pd == null && context != null) {
            pd = CommonPrograssDialog.getInstance(context);
            pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            pd.setCancelable(false);
            pd.show();
        }
    }

    @Override
    public void onLoading(long total, long current, boolean isDownloading) {
        //TODO 根据公司业务需求，处理相应业务逻辑
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
