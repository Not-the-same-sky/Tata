package ui;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.tata.R;

import Utils.FileUtils;
import base.BaseActivity;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by 不一样的天空 on 2016/9/5.
 */
public class SetAvatarActivity extends BaseActivity{
    @Bind(R.id.iv_show_avatar)
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setavatar_main);
        ButterKnife.bind(this);
        Bitmap bitmap= FileUtils.getChatAvatarFromFile();
        if (bitmap!=null){
            imageView.setImageBitmap(bitmap);
        }
    }
    @OnClick(R.id.fromCamera)
    public void chooseAvatarFromCamera(){
        toast("从拍照中选择");
    }
    @OnClick(R.id.fromPicture)
    public void chooseAvatarFromPicture(){
        toast("从相册中选择");
    }
}
