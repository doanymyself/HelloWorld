package demo.helloworld.components.set;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.zhy.http.okhttp.callback.StringCallback;

import org.xutils.common.util.LogUtil;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import demo.helloworld.R;
import demo.helloworld.framework.BaseFragment;
import demo.helloworld.http.OKHttp.OKEnginCallBack;
import demo.helloworld.http.OKHttp.OKServiceEngin;
import demo.helloworld.http.XUtils.EnginCallBack;
import demo.helloworld.http.XUtils.ServiceEngin;
import demo.helloworld.utils.CommonUtils;
import demo.helloworld.widgets.SelectPicPopupWindow;
import okhttp3.Call;

/**
 * ********************************************************
 * <p>
 * ********************************************************
 * Created by wangdong on 16/9/18.
 */
public class SettingFragment extends BaseFragment implements View.OnClickListener {
    View view;
    @BindView(R.id.img_picasso)
    ImageView mImgPicasso;
    @BindView(R.id.btn_confirm)
    Button btn_confirm;

    private final int PIC_FROM_CAMERA = 1;// 选择拍照
    private final int PIC_FROM＿LOCALPHOTO = 0;// 选择本地相册
    private final int SAVE_IMAGE = 2;// 裁剪后保存图片
    private Uri photoUri;// 图片uri
    public String photoname = "";// 当前图片名称
    public String photopath = "";

    private SelectPicPopupWindow pop_takephoto;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_setting, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    protected void initUIandEvent() {
        super.initUIandEvent();
        pop_takephoto = new SelectPicPopupWindow(getActivity(), takePhotoOnClick);
        mImgPicasso.setOnClickListener(this);
        btn_confirm.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.img_glide: // 提交个人设置
                uploadPics();
                break;
            case R.id.img_picasso:

                photoname = CommonUtils.getFileNameByDate() + ".jpeg";

                pop_takephoto.showAtLocation(view, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
                break;
        }
    }

    /**
     * 上传用户头像
     *
     * @author zhangyl
     */
    private void uploadPics() {
        if (!photopath.equals("") && !photoname.equals("")) { // 修改头像

            Map<String, Object> map = new HashMap<>();
            map.put("phoneNumber", "15901514506");
            ServiceEngin.upLoadFile(photoname, photopath, map, new EnginCallBack<String>(context) {
                @Override
                public void onSuccess(String result) {
                    super.onSuccess(result);
                    LogUtil.d(result);
                }

                @Override
                public void onError(Throwable ex, boolean isOnCallback) {
                    super.onError(ex, isOnCallback);
                    LogUtil.d(ex.toString());
                }
            });

//            OKServiceEngin.upLoadPic(photoname, photopath, map, new OKEnginCallBack(context) {
//                @Override
//                public void onResponse(String response, int id) {
//                    super.onResponse(response, id);
//                }
//
//                @Override
//                public void onError(Call call, Exception e, int id) {
//                    super.onError(call, e, id);
//                }
//            });
        }
    }

    private View.OnClickListener takePhotoOnClick = new View.OnClickListener() {
        public void onClick(View v) {
            pop_takephoto.dismiss();
            switch (v.getId()) {
                case R.id.item_popupwindows_camera:        //点击拍照按钮
                    doHandlerPhoto(PIC_FROM_CAMERA);
                    break;
                case R.id.item_popupwindows_Photo:       //点击从相册中选择按钮
                    doHandlerPhoto(PIC_FROM＿LOCALPHOTO);
                    break;
            }
        }

    };

    /*
     * 根据不同方式选择图片 ,0-本地相册选择，1为拍照
     */
    private void doHandlerPhoto(int type) {
        try {
            // 保存裁剪后的图片文件
            File pictureFileDir = new File(
                    Environment.getExternalStorageDirectory(),
                    "/yunyi/Image");
            if (!pictureFileDir.exists()) {
                pictureFileDir.mkdirs();
            }
            File picFile = new File(pictureFileDir, photoname);
            if (!picFile.exists()) {
                picFile.createNewFile();
            }
            photoUri = Uri.fromFile(picFile);
            if (type == PIC_FROM_CAMERA) {
                Intent cameraIntent = new Intent(
                        MediaStore.ACTION_IMAGE_CAPTURE);
                cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
                startActivityForResult(cameraIntent, PIC_FROM_CAMERA);
                pop_takephoto.dismiss();
            } else {
                Intent photoIntent = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(photoIntent, PIC_FROM＿LOCALPHOTO);
                pop_takephoto.dismiss();
            }
        } catch (Exception e) {
            Log.i("HandlerPicError", "处理图片出现错误");
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case PIC_FROM_CAMERA: // 拍照
                    try {
                        cropImageUriByTakePhoto(photoUri);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case PIC_FROM＿LOCALPHOTO:
                    try {
                        Uri pUri = data.getData();
                        cropImageUriByTakePhoto(pUri);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case SAVE_IMAGE:
                    try {
                        if (photoUri != null) {
                            photopath = CommonUtils.getRealFilePath(context,
                                    photoUri);
                            Drawable d = Drawable.createFromPath(photopath);
                            // set_setheadimg.setBackgroundDrawable(d);自定义的圆形头像,不可以用xutils显示,也不可以用background设置
                            mImgPicasso.setImageDrawable(d);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
            }

        } else {// 将当前图片名称和base都设置为"",防止添加多次或者添加不同名称的重复图片
            photoname = "";
        }
    }

    /**
     * 启动裁剪
     *
     * @param uri
     */
    private void cropImageUriByTakePhoto(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        // 设置公用参数
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", 600);
        intent.putExtra("outputY", 600);
        intent.putExtra("noFaceDetection", true); // no face detection
        intent.putExtra("scale", true);
        intent.putExtra("return-data", false);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());

        startActivityForResult(intent, SAVE_IMAGE);
    }
}
