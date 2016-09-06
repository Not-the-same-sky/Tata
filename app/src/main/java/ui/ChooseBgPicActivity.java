package ui;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;

import com.example.tata.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import Adapter.ChooseBgPicAdapter;
import Utils.FileUtils;
import base.BaseActivity;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnItemClick;
import butterknife.OnItemLongClick;

/**
 * Created by 不一样的天空 on 2016/9/2.
 */
public class ChooseBgPicActivity extends BaseActivity {
    @Bind(R.id.gv_choosepic)
    GridView gridView;
   private ChooseBgPicAdapter chooseBgPicAdapter;
   private int [] images;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choosebgpic_main);
        ButterKnife.bind(this);
        initImages();
        initListener();
        chooseBgPicAdapter=new ChooseBgPicAdapter(this,images);
        gridView.setAdapter(chooseBgPicAdapter);
    }
    private void initListener(){
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                toast("click" + position);
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), images[position]);
                FileUtils.saveBitmapToFile(bitmap,"chatBg.jpg");
        }
    });
       gridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
           @Override
           public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
               toast("LongClick"+position);
               return false;
           }
       });
    }
   public void initImages(){
        images= new int[]{R.drawable.chatbg_1,R.drawable.chatbg_2,R.drawable.chatbg_3,
                R.drawable.chatbg_4,R.drawable.chatbg_5,R.drawable.chatbg_6,
                R.drawable.chatbg_7,R.drawable.chatbg_8,R.drawable.chatbg_9,};
    }
}
