package Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.tata.R;

import Utils.ImageUtils;

/**
 * Created by 不一样的天空 on 2016/9/2.
 */
public class ChooseBgPicAdapter extends BaseAdapter {
    private Context context;
    private int[] images;

    public ChooseBgPicAdapter(Context context, int[] images) {
        this.context = context;
        this.images = images;
    }

    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public Object getItem(int position) {
        return images[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final BaseViewHolder baseViewHolder = new BaseViewHolder(context, parent, position, R.layout.choosebgpic_item);
        ((ImageView) baseViewHolder.getView(R.id.iv_item)).setImageBitmap(ImageUtils.compressBitmapFromResource(context, images[position], 140, 140));
        return baseViewHolder.getmConvertView();
    }
}
