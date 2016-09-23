package demo.helloworld.http.OKHttp;

import android.content.Context;
import android.os.Environment;
import android.util.Log;
import android.view.View;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.builder.PostFormBuilder;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.File;
import java.util.Map;

import demo.helloworld.data.Constants;
import demo.helloworld.utils.CommonUtils;
import demo.helloworld.utils.NetUtils;
import demo.helloworld.widgets.CommonDialog;


/**
 * ********************************************************
 * 基于鸿洋大神的OkHttpUtils二次封装
 * https://github.com/hongyangAndroid/okhttputils
 * ********************************************************
 * Created by wangdong on 16/9/7.
 */
public class OKServiceEngin {
    private static String url = Constants.REQUST_PORT;
    private static String urlPic = Constants.REQUST_UPLOADPIC;
    private static CommonDialog mDialog;

    /**
     * 异步请求方法,请自行在callback中处理返回结果(callback的success中自行解析result)
     *
     * @param serversName   方法名
     * @param serversParams 参数集合
     * @param callback      重写的EngineCallBack回调函数
     * @param <T>
     */
    public static <T> void Request(final Context context, String serversName, Map<String, Object> serversParams, OKEnginCallBack callback) {
        if (!NetUtils.isConnected(context)) {
            if (mDialog == null) {
                mDialog = new CommonDialog(context, 1, "确定", null,
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                // TODO Auto-generated method stub
                                mDialog.dismiss();
                                NetUtils.openSetting(context);
                            }
                        }, null, null, "请设置网络!");
            }
            mDialog.show();
        } else {
            if (mDialog != null)
                mDialog.dismiss();
            //参数拼接
            PostFormBuilder builder1 = OkHttpUtils
                    .post()//
                    .url(url);
            builder1.addParams("serversName", serversName);
            if (null != serversParams) {
                for (Map.Entry<String, Object> entry : serversParams.entrySet()) {
                    builder1.addParams(entry.getKey(), entry.getValue().toString());
                }
            }
            builder1.build().execute(callback);
        }
    }

    /**
     * 上传头像
     *
     * @param filename
     * @param imagePath
     * @param serversParams
     * @param callback
     * @param <T>
     */
    public static <T> void upLoadPic(String filename, String imagePath, Map<String, Object> serversParams,
                                     OKEnginCallBack callback) {
        String dir = Environment.getExternalStorageDirectory() + "/yunyi/";
        if (!new File(dir).exists())
            new File(dir).mkdirs();
        String tempPath = dir + filename;
        File uploadfile = new File(tempPath);
        CommonUtils.compressBmpToFile(imagePath, tempPath);
        //参数拼接
        PostFormBuilder builder = OkHttpUtils.post();
        builder.addFile("myFile", filename, uploadfile);
        builder.url(urlPic);
        if (null != serversParams) {
            for (Map.Entry<String, Object> entry : serversParams.entrySet()) {
                builder.addParams(entry.getKey(), entry.getValue().toString());
            }
        }
        // 发送请求
        Log.e("上传图片", "上传" + filename + "大小" + uploadfile.length());
        builder.build().execute(callback);
    }

}
