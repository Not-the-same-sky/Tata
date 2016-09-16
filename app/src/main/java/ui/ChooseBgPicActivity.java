package ui;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.tata.R;

import Adapter.ChooseBgPicAdapter;
import base.BaseActivity;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by 不一样的天空 on 2016/9/2.
 */
public class ChooseBgPicActivity extends BaseActivity {
    @Bind(R.id.gv_choosepic)
    GridView gridView;
    private ChooseBgPicAdapter chooseBgPicAdapter;
    private int[] images;
    private static final String TAG=ChooseBgPicActivity.class.getSimpleName();
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choosebgpic_main);
        ButterKnife.bind(this);
        initImages();
        initListener();
        chooseBgPicAdapter = new ChooseBgPicAdapter(this, images);
        gridView.setAdapter(chooseBgPicAdapter);
    }

    private void initListener() {
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle bundle=new Bundle();
                bundle.putInt("drawableId",images[position]);
                toast(getPackageName());
                Log.d(TAG, "onItemClick: "+getPackageName());
                startActivity(ShowBgActivity.class,bundle,true);
                finish();
            }
        });
        gridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                toast("LongClick" + position);
                return false;
            }
        });
    }

    public void initImages() {
        images = new int[]{R.drawable.chatbg_1, R.drawable.chatbg_2, R.drawable.chatbg_3,
                R.drawable.chatbg_4, R.drawable.chatbg_5, R.drawable.chatbg_6,
                R.drawable.chatbg_7, R.drawable.chatbg_8, R.drawable.chatbg_9,
                R.drawable.chatbg_10, R.drawable.chatbg_11, R.drawable.chatbg_12,
                R.drawable.chatbg_13, R.drawable.chatbg_14, R.drawable.chatbg_15,
                R.drawable.chatbg_16, R.drawable.chatbg_17, R.drawable.chatbg_18,
                R.drawable.chatbg_19, R.drawable.chatbg_20, R.drawable.chatbg_21};
    }
}
