package demo.helloworld.components.set;

import android.os.Bundle;

import demo.helloworld.R;
import demo.helloworld.framework.BaseActivity;

/**
 * ********************************************************
 * <p>
 * ********************************************************
 * Created by wangdong on 16/8/15.
 */
public class SettingActivity extends BaseActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContenierView(4);
        setBackType(2);
        SettingFragment fragment = new SettingFragment();
        mFragmentManager.beginTransaction()
                .replace(R.id.login_fl_continer, fragment)
                .addToBackStack("SettingFragment").commit();
    }

    //    @BindView(R.id.view)
//    View view;
//    @BindView(R.id.img_picasso)
//    ImageView mImgPicasso;
//    @BindView(R.id.img_glide)
//    ImageView mImgGlide;
//
//    private PopupWindow pop_takephoto;// 选择头像的popwindow
//    private final int PIC_FROM_CAMERA = 1;// 选择拍照
//    private final int PIC_FROM＿LOCALPHOTO = 0;// 选择本地相册
//    private final int SAVE_IMAGE = 2;// 裁剪后保存图片
//    private View takephotoView; // pop_takephoto的view
//    private Button publishtopic_popup_takephpto;// pop中选择拍照
//    private Button publishtopic_popup_choicepic;// pop中选择相册
//    private Button publishtopic_popup_canceltake;// pop中取消
//    private Uri photoUri;// 图片uri
//    public String photoname = "";// 当前图片名称
//    public String photopath = "";
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_setting);
//        ButterKnife.bind(this);
//
//        initView();
//    }
//
//    private void initView() {
//        initpop_takephoto();
//        mImgPicasso.setOnClickListener(this);
//        mImgGlide.setOnClickListener(this);
//    }
//
//    @Override
//    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.publishtopic_popup_takephpto:
//                doHandlerPhoto(PIC_FROM_CAMERA);
//                break;
//            case R.id.publishtopic_popup_choicepic:
//                doHandlerPhoto(PIC_FROM＿LOCALPHOTO);
//                break;
//            case R.id.publishtopic_popup_canceltake:
//                pop_takephoto.dismiss();
//                break;
//            case R.id.img_glide: // 提交个人设置
//                uploadPics();
//                break;
//            case R.id.img_picasso:
//                // TODO Auto-generated method stub
//                pop_takephoto.showAtLocation(view, Gravity.BOTTOM
//                        | Gravity.CENTER_HORIZONTAL, 0, 0);
//                photoname = CommonUtils.getFileNameByDate() + ".jpeg";
//                break;
//        }
//    }
//
//    /**
//     * 上传用户头像
//     *
//     * @author zhangyl
//     */
//    private void uploadPics() {
//        if (!photopath.equals("") && !photoname.equals("")) { // 修改头像
//            String url = "http://yunlive.applinzi.com/upload.php";
//            String path = "/storage/emulated/0/ncihealth/Image/20160912121301540.jpeg";
//            Map<String, Object> map = new HashMap<>();
//            File file = new File(path);
//            map.put("myFile", file);
//            ServiceEngin.UpLoadFile(url, map, new EnginCallBack<String>(this) {
//                @Override
//                public void onSuccess(String result) {
//                    super.onSuccess(result);
//                    LogUtil.d(result);
//                }
//
//                @Override
//                public void onError(Throwable ex, boolean isOnCallback) {
//                    super.onError(ex, isOnCallback);
//                    LogUtil.d(ex.toString());
//                }
//            });
//
////            ServiceEngin.upLoadFile(photoname, photopath, new EnginCallBack<String>(this) {
////                @Override
////                public void onSuccess(String result) {
////                    super.onSuccess(result);
////                    Log.d("---------->", result);
////                }
////
////                @Override
////                public void onError(Throwable ex, boolean isOnCallback) {
////                    super.onError(ex, isOnCallback);
////                    Log.d("---------->", ex.toString());
////                }
////            });
//        }
//    }
//
//    /**
//     * 创建pop_takephoto方法
//     */
//    private void initpop_takephoto() {
//        // 加载PopupWindow的布局文件
//        takephotoView = LayoutInflater.from(this).inflate(
//                R.layout.popup_takepicture, null);
//        pop_takephoto = new PopupWindow(takephotoView,
//                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//        pop_takephoto.setFocusable(true);// 获取popup中控件焦点 ,设置PopupWindow可触摸
//        pop_takephoto.setBackgroundDrawable(new ColorDrawable(0xb0000000));
//        pop_takephoto.setOutsideTouchable(true);
//        pop_takephoto.setAnimationStyle(R.style.popupwindow_anim);
//        publishtopic_popup_takephpto = (Button) takephotoView
//                .findViewById(R.id.publishtopic_popup_takephpto);
//        publishtopic_popup_choicepic = (Button) takephotoView
//                .findViewById(R.id.publishtopic_popup_choicepic);
//        publishtopic_popup_canceltake = (Button) takephotoView
//                .findViewById(R.id.publishtopic_popup_canceltake);
//        publishtopic_popup_takephpto.setOnClickListener(this);
//        publishtopic_popup_choicepic.setOnClickListener(this);
//        publishtopic_popup_canceltake.setOnClickListener(this);
//
//    }
//
//    /*
//     * 根据不同方式选择图片 ,0-本地相册选择，1为拍照
//     */
//    private void doHandlerPhoto(int type) {
//        try {
//            // 保存裁剪后的图片文件
//            File pictureFileDir = new File(
//                    Environment.getExternalStorageDirectory(),
//                    "/ncihealth/Image");
//            if (!pictureFileDir.exists()) {
//                pictureFileDir.mkdirs();
//            }
//            File picFile = new File(pictureFileDir, photoname);
//            if (!picFile.exists()) {
//                picFile.createNewFile();
//            }
//            photoUri = Uri.fromFile(picFile);
//            if (type == PIC_FROM_CAMERA) {
//                Intent cameraIntent = new Intent(
//                        MediaStore.ACTION_IMAGE_CAPTURE);
//                cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
//                startActivityForResult(cameraIntent, PIC_FROM_CAMERA);
//                pop_takephoto.dismiss();
//            } else {
//                Intent photoIntent = new Intent(
//                        Intent.ACTION_PICK,
//                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//                startActivityForResult(photoIntent, PIC_FROM＿LOCALPHOTO);
//                pop_takephoto.dismiss();
//            }
//        } catch (Exception e) {
//            Log.i("HandlerPicError", "处理图片出现错误");
//        }
//
//    }
//
//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        if (resultCode == Activity.RESULT_OK) {
//            switch (requestCode) {
//                case PIC_FROM_CAMERA: // 拍照
//                    try {
//                        cropImageUriByTakePhoto(photoUri);
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                    break;
//                case PIC_FROM＿LOCALPHOTO:
//                    try {
//                        Uri pUri = data.getData();
//                        cropImageUriByTakePhoto(pUri);
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                    break;
//                case SAVE_IMAGE:
//                    try {
//                        if (photoUri != null) {
//                            photopath = CommonUtils.getRealFilePath(this,
//                                    photoUri);
//                            Drawable d = Drawable.createFromPath(photopath);
//                            // set_setheadimg.setBackgroundDrawable(d);自定义的圆形头像,不可以用xutils显示,也不可以用background设置
//                            mImgPicasso.setImageDrawable(d);
//                        }
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                    break;
//            }
//
//        } else {// 将当前图片名称和base都设置为"",防止添加多次或者添加不同名称的重复图片
//            photoname = "";
//        }
//    }
//
//    /**
//     * 启动裁剪
//     *
//     * @param uri
//     */
//    private void cropImageUriByTakePhoto(Uri uri) {
//        Intent intent = new Intent("com.android.camera.action.CROP");
//        intent.setDataAndType(uri, "image/*");
//        // 设置公用参数
//        intent.putExtra("crop", "true");
//        intent.putExtra("aspectX", 1);
//        intent.putExtra("aspectY", 1);
//        intent.putExtra("outputX", 600);
//        intent.putExtra("outputY", 600);
//        intent.putExtra("noFaceDetection", true); // no face detection
//        intent.putExtra("scale", true);
//        intent.putExtra("return-data", false);
//        intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
//        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
//
//        startActivityForResult(intent, SAVE_IMAGE);
//    }

}
