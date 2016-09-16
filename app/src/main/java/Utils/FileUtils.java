package Utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * Created by 不一样的天空 on 2016/9/3.
 */
public class FileUtils {
    private static final String TAG = "FileUtils";
    private static final String SUCCESS = "保存成功";
    private static final String FAIL = "保存失败";
    private static final String ROOTDIR = getRootDir();
    private static final String APPNAME = "Tata";
    private static String APPDIR = "";
    private static boolean isReadyInitAppDir = false;

    public static boolean saveBitmapToFile(Bitmap bitmap, String fileName) {
        if (!isReadyInitAppDir) {
            APPDIR = mkdir(APPNAME);
        }
        File file = new File(APPDIR + File.separator + fileName);
        try {
            FileOutputStream out = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
            out.flush();
            Log.d(TAG, SUCCESS + "->" + "路径:" + file.toString());
            out.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            Log.d(TAG, FAIL + ":" + e.getMessage());
            return false;
        }
    }

    public static String getRootDir() {
        return Environment.getExternalStorageDirectory().toString();
    }

    public static String mkdir(String dirName) {
        File file = new File(ROOTDIR + File.separator + dirName);
        if (!file.exists()) {
            file.mkdir();
        }
        APPDIR = file.toString();
        isReadyInitAppDir = true;
        return APPDIR;
    }

    public static Bitmap getBgBitmapFromFile() {
        File file = new File(mkdir(APPNAME) + File.separator + "chatBg.jpg");
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            Bitmap bitmap = BitmapFactory.decodeStream(fileInputStream);
            return bitmap;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Drawable getBgDrawableFromFile(Context context) {
        File file = new File(mkdir(APPNAME) + File.separator + "chatBg.jpg");
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            Bitmap bitmap = BitmapFactory.decodeStream(fileInputStream);
            Drawable drawable = new BitmapDrawable(context.getResources(), bitmap);
            return drawable;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Bitmap getChatAvatarFromFile() {
        File file = new File(mkdir(APPNAME) + File.separator + "chatAvatar.jpg");
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            Bitmap bitmap = BitmapFactory.decodeStream(fileInputStream);
            return bitmap;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
