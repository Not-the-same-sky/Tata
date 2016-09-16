package ui;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.ImageView;

import com.example.tata.R;

import Utils.FileUtils;
import base.BaseActivity;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by 不一样的天空 on 2016/9/14.
 */
public class ShowBgActivity extends BaseActivity {
    private int drawableId;
    @Bind(R.id.show_bg)
    ImageView imageView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.showbg_main);
        ButterKnife.bind(this);
        drawableId = getBundle().getInt("drawableId", R.mipmap.love);
        imageView.setBackgroundResource(drawableId);
    }

    @OnClick(R.id.inform_bg)
    public void informChooseBg() {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), drawableId);
        FileUtils.saveBitmapToFile(bitmap, "chatBg.jpg");
        Log.d("hello", "onCreate: " + getPackageName());
        toast("聊天背景设置成功");
        finish();
    }
}
