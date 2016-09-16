package Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tata.R;

import java.io.File;

import Utils.FileUtils;
import Utils.ImageUtils;
import Utils.TimeUtil;

/**
 * Created by 不一样的天空 on 2016/8/27.
 */
public class BaseViewHolder {
    public Context context;
    private SparseArray<View> mViews;
    private int position;
    private View mConvertView;

    public View getmConvertView() {
        return mConvertView;
    }

    public BaseViewHolder(Context context, ViewGroup parent, int position, int layoutId) {
        this.context = context;
        this.mViews = new SparseArray<View>();
        this.position = position;
        this.mConvertView = LayoutInflater.from(context).inflate(layoutId, parent, false);
        this.mConvertView.setTag(this);
    }

    public static BaseViewHolder get(Context context, View convertView, ViewGroup parent, int position, int layoutId) {
        if (convertView == null) {
            return new BaseViewHolder(context, parent, position, layoutId);
        } else {
            BaseViewHolder baseViewHolder = (BaseViewHolder) convertView.getTag();
            baseViewHolder.position = position;
            return baseViewHolder;
        }
    }

    public <T extends View> T getView(int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = mConvertView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }

    public BaseViewHolder setText(int viewId, String text) {
        TextView tv = getView(viewId);
        tv.setText(text);
        return this;
    }

    public BaseViewHolder setTime(int viewId, long time) {
        TextView tv = getView(viewId);
        tv.setText(TimeUtil.getChatTime(time));
        return this;
    }

    public void showTime(int viewId, long time, boolean isShow) {
        TextView tv_time = getView(viewId);
        tv_time.setText(TimeUtil.getChatTime(time));
        tv_time.setVisibility(isShow ? View.VISIBLE : View.GONE);
    }

    public BaseViewHolder setImageResource(int viewId, int drawableId) {
        ImageView iv = getView(viewId);
        iv.setBackgroundResource(drawableId);
        return this;
    }

    public BaseViewHolder setImageBitmap(int viewId, Bitmap bitmap) {
        ImageView iv = getView(viewId);
        if (bitmap==null){
            iv.setImageBitmap(BitmapFactory.decodeResource(context.getResources(),R.mipmap.love));
        }else {
            iv.setImageBitmap(bitmap);
        }
        return this;
    }
    public BaseViewHolder setImageByPath(int viewId, String imagePath) {
        ImageView iv = getView(viewId);
        Bitmap bitmap=BitmapFactory.decodeFile(imagePath);
        if (bitmap==null){
            iv.setImageBitmap(BitmapFactory.decodeResource(context.getResources(),R.mipmap.love));
        }else {
            iv.setImageBitmap(bitmap);
        }
        return this;
    }
    public int getPosition() {
        return position;
    }
}

