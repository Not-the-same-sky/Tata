package Utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.ByteBuffer;

/**
 * Created by 不一样的天空 on 2016/9/3.
 */
public class ImageUtils {
    private static String TAG="ImageUtils";
    public static InputStream bitmapToInputStream(Bitmap bitmap){
        int size=bitmap.getHeight()*bitmap.getRowBytes();
        ByteBuffer buffer=ByteBuffer.allocate(size);
        bitmap.copyPixelsFromBuffer(buffer);
        return new ByteArrayInputStream(buffer.array());
    }
    public static Bitmap compressBitmapFromResource(Context context,int resId,int reqWidth,int reqHeight){
        final BitmapFactory.Options options=new BitmapFactory.Options();
        options.inJustDecodeBounds=true;
        BitmapFactory.decodeResource(context.getResources(),resId,options);
        final int width=options.outWidth;
        final int height=options.outHeight;
        Log.d(TAG, "compressBitmapFromResource: height="+height+" width="+width);
        int inSampleSize=1;
        if (height>reqHeight || width>reqWidth){
            final int heightRatio=Math.round((float)height/(float)reqHeight);
            final int widthRatio=Math.round((float)width/(float)reqWidth);
            inSampleSize=heightRatio<widthRatio?heightRatio:widthRatio;
        }
        Log.d(TAG, "compressBitmapFromResource: inSampleSize="+inSampleSize);
        options.inSampleSize=inSampleSize;
        options.inJustDecodeBounds=false;
        return BitmapFactory.decodeResource(context.getResources(),resId,options);
    }
    public static Bitmap drawableToBitmap(Drawable drawable){
        Bitmap bitmap;
        if (drawable instanceof BitmapDrawable){
            bitmap=((BitmapDrawable)drawable).getBitmap();
            if (bitmap!=null)
                return bitmap;
        }
        int width=drawable.getIntrinsicWidth();
        width=width > 0 ? width : 1;
        int height=drawable.getIntrinsicHeight();
        height=height > 0 ? height : 1;
        bitmap=Bitmap.createBitmap(width,height, Bitmap.Config.ARGB_8888);
        Canvas canvas=new Canvas(bitmap);
        drawable.setBounds(0,0,canvas.getWidth(),canvas.getHeight());
        drawable.draw(canvas);
        return bitmap;
    }
    public static Drawable bitmapToDrawable(Context context,Bitmap bitmap){
        Drawable drawable=new BitmapDrawable(context.getResources(),bitmap);
        return drawable;
    }
}
