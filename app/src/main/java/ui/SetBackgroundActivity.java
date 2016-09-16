package ui;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;

import com.example.tata.R;

import Utils.FileUtils;
import Utils.ImageUtils;
import base.BaseActivity;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by 不一样的天空 on 2016/9/2.
 */
public class SetBackgroundActivity extends BaseActivity {
    private final int CHOOSEBGFROMGALLERY=1;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setbackground_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_choose_bgpic)
    public void displayBgPic() {
        Intent intent = new Intent(this, ChooseBgPicActivity.class);
        startActivity(intent);
    }
    @OnClick(R.id.bg_from_gallery)
    public void chooseBgFromGallery() {
        Intent intent=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);//该方式使用游标来获取选择的图片路径
        startActivityForResult(intent,CHOOSEBGFROMGALLERY);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == CHOOSEBGFROMGALLERY && data != null) {
                Uri imageUri = data.getData();
                String[] filePathColumn = {MediaStore.Images.Media.DATA};
                Cursor cursor = getContentResolver().query(imageUri,
                        filePathColumn, null, null, null);
                if (cursor != null) {
                    cursor.moveToFirst();
                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                    String picturePath = cursor.getString(columnIndex);
                    Log.d("cursor is not null", "onActivityResult: " + picturePath);
                    Bitmap bitmap = ImageUtils.compressBitmapFromFile(picturePath, 800, 900);
                    FileUtils.saveBitmapToFile(bitmap, "chatBg.jpg");
                }
            }
        }
    }
}
