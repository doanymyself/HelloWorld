package demo.helloworld.http;

import android.content.Context;
import android.content.pm.PackageManager;
import android.telephony.TelephonyManager;

import com.admin.app.data.Constants;
import com.alibaba.fastjson.JSON;

import org.xutils.common.Callback;
import org.xutils.common.Callback.CommonCallback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.Map;

public class ServiceEngin {

    private static String url = Constants.HOST + Constants.REQUST_PORT;

    /**
     * 异步请求方法,请自行在callback中处理返回结果(callback的success中自行解析result)
     *
     * @param bizId       业务场景编码
     * @param serviceName 方法名
     * @param servicePara 参数集合
     * @param context     当前界面上下文
     * @param callback    重写的EngineCallBack回调函数
     */
    public static void Request(final Context context, String bizId,
                               String serviceName, String servicePara, EnginCallBack callback) {
        // 参数拼接
        RequestParams params = new RequestParams(url);
        params.addBodyParameter("keyVer", "v1");
        params.addBodyParameter("appID", "1");
        EnginBean eb = new EnginBean();
        String deviceId = ((TelephonyManager) context
                .getSystemService(Context.TELEPHONY_SERVICE)).getDeviceId();
        eb.setDeviceId(deviceId);
        eb.setClientOS("1");
        eb.setClientVer("" + android.os.Build.VERSION.SDK_INT);//
        try {
            eb.setAppVer("" + context.getPackageManager().getPackageInfo(context.getPackageName(),
                    PackageManager.GET_CONFIGURATIONS).versionCode);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        eb.setBizId(bizId);
        eb.setServiceName(serviceName);
        eb.setServicePara(servicePara);
        String para = JSON.toJSON(eb) + "";
        //Des3加密
        try {
            para = Des3.encode(para);
        } catch (Exception e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        params.addBodyParameter("para", para);
        //发送请求
        x.http().post(params, callback);
    }

    /**
     * 发送get请求
     *
     * @param <T>
     */
    public static <T> void Get(String url, CommonCallback<T> callback) {
        RequestParams params = new RequestParams(url);
        x.http().get(params, callback);
    }

    /**
     * 发送post请求
     *
     * @param <T>
     */
    public static <T> void Post(String url, Map<String, Object> map, Callback.ProgressCallback<T> callback) {
        RequestParams params = new RequestParams(url);
        if (null != map) {
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                params.addParameter(entry.getKey(), entry.getValue());
            }
        }
        x.http().post(params, callback);
    }

    /**
     * 上传文件
     *
     * @param <T>
     */
    public static <T> void UpLoadFile(String url, Map<String, Object> map, CommonCallback<T> callback) {
        RequestParams params = new RequestParams(url);
        if (null != map) {
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                params.addParameter(entry.getKey(), entry.getValue());
            }
        }
        params.setMultipart(true);
        x.http().get(params, callback);
    }

    /**
     * 下载文件
     *
     * @param <T>
     */
    public static <T> void DownLoadFile(String url, String filepath, CommonCallback<T> callback) {
        RequestParams params = new RequestParams(url);
        //设置断点续传
        params.setAutoResume(true);
        params.setSaveFilePath(filepath);
        x.http().get(params, callback);
    }
}
