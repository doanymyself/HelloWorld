package demo.helloworld.utils;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * *********************************************************
 * 常用工具类
 * *********************************************************
 * <p>
 * Created by wangdong on 2016/5/16.
 */
public class CommonUtils {
    public static SimpleDateFormat sdf = new SimpleDateFormat(
            "yyyyMMddHHmmssSSS");

    /**
     * 统一文件名命名方式(图片[头像,帖子,病历],音频,视频)
     *
     * @return
     * @author zhangyl
     */
    public static String getFileNameByDate() {
        return sdf.format(new Date());
    }

    /**
     * Try to return the absolute file path from the given Uri
     *
     * @param context
     * @param uri     图片uri
     * @return 图片路径
     */
    public static String getRealFilePath(final Context context, final Uri uri) {
        if (null == uri)
            return null;
        final String scheme = uri.getScheme();
        String data = null;
        if (scheme == null)
            data = uri.getPath();
        else if (ContentResolver.SCHEME_FILE.equals(scheme)) {
            data = uri.getPath();
        } else if (ContentResolver.SCHEME_CONTENT.equals(scheme)) {
            Cursor cursor = context.getContentResolver().query(uri,
                    new String[]{MediaStore.Images.ImageColumns.DATA}, null, null, null);
            if (null != cursor) {
                if (cursor.moveToFirst()) {
                    int index = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                    if (index > -1) {
                        data = cursor.getString(index);
                    }
                }
                cursor.close();
            }
        }
        return data;
    }

    /**
     * 压缩图片至100kb以下
     *
     * @param filePath1 待处理图片,原图
     * @param filePath2 处理完成图片,上传用,用完删除
     * @return
     * @author zhangyl
     */
    public static void compressBmpToFile(String filePath1, String filePath2) {
        // 解码图片
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filePath1, options);
        // 计算压缩比例
        int height = options.outHeight;
        int width = options.outWidth;
        int inSampleSize = 1;
        int reqHeight = 800;
        int reqWidth = 480;
        if (height > reqHeight || width > reqWidth) {
            final int heightRatio = Math.round((float) height
                    / (float) reqHeight);
            final int widthRatio = Math.round((float) width / (float) reqWidth);
            inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
        }
        // 在内存中创建bitmap对象，这个对象按照缩放大小创建的
        options.inSampleSize = calculateInSampleSize(options, 480, 800);
        options.inJustDecodeBounds = false;
        Bitmap bitmap = BitmapFactory.decodeFile(filePath1, options);
        try {
            FileOutputStream fos = new FileOutputStream(filePath2);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 60, fos);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * 计算图片的缩放值
     *
     * @param options   压缩比
     * @param reqWidth  压缩后宽
     * @param reqHeight 压缩后高
     * @return
     * @author zhangyl
     */
    public static int calculateInSampleSize(BitmapFactory.Options options,
                                            int reqWidth, int reqHeight) {
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;
        if (height > reqHeight || width > reqWidth) {
            final int heightRatio = Math.round((float) height
                    / (float) reqHeight);
            final int widthRatio = Math.round((float) width / (float) reqWidth);
            inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
        }
        return inSampleSize;
    }
}
