package ui;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.ImageView;

import com.example.tata.R;

import java.io.File;

import Utils.FileUtils;
import Utils.ImageUtils;
import base.BaseActivity;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by 不一样的天空 on 2016/9/5.
 */
public class SetAvatarActivity extends BaseActivity {
    @Bind(R.id.iv_show_avatar)
    ImageView imageView;

    private final int SELECTFROMGALLERY = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setavatar_main);
        ButterKnife.bind(this);
        Bitmap bitmap = FileUtils.getChatAvatarFromFile();
        if (bitmap != null) {
            imageView.setImageBitmap(bitmap);
        }
    }

    @OnClick(R.id.fromCamera)
    public void chooseAvatarFromCamera() {
        toast("从拍照中选择");
    }

    @OnClick(R.id.fromPicture)
    public void chooseAvatarFromPicture() {
        toast("从相册中选择");
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);//该方式使用imageUri.getPath()来获取选择图片路径
        startActivityForResult(intent, SELECTFROMGALLERY);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == SELECTFROMGALLERY && data != null) {
                Uri imageUri = data.getData();
                String[] filePathColumn = {MediaStore.Images.Media.DATA};
                Cursor   cursor = getContentResolver().query(imageUri,
                        filePathColumn, null, null, null);
                if (cursor!=null) {
                    cursor.moveToFirst();
                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                    String picturePath = cursor.getString(columnIndex);
                    Log.d("cursor is not null", "onActivityResult: "+picturePath);
                    Bitmap bitmap = ImageUtils.compressBitmapFromFile(picturePath, 500, 500);
                    imageView.setImageBitmap(bitmap);
                    FileUtils.saveBitmapToFile(bitmap, "chatAvatar.jpg");
                } else {
                    File file = new File(imageUri.getPath());
                    Log.d("cursor is null", "onActivityResult: "+file.getAbsolutePath()+"");
                    if (!file.exists()) {
                        return;
                    }
                    Bitmap bitmap = ImageUtils.compressBitmapFromFile(file.getAbsolutePath(), 150, 150);
                    imageView.setImageBitmap(bitmap);
                    FileUtils.saveBitmapToFile(bitmap, "chatAvatar.jpg");
                }
            }
        }
    }
}
